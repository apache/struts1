/*
 * $Id$ 
 *
 * Copyright 1999-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.struts.taskdefs;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.tools.ant.BuildException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Given a list of files compatible with the Tag-Library
 * Descriptor (TLD) format, generates a JavaDoc like
 * documentation for each Tag-Library.
 *
 * @version $Rev$ $Date$
 */
public class TaglibDoc extends EnhMatchingTask {
	private File destdir;
	/**
	 * Sets the destination directory for the generated documents.
	 * @param destdir The destination directory
	 */
	public void setDestdir(File destdir) {
		this.destdir = destdir;
	}
	/**
	 * Called by the Ant runtime. Needs to have the mandatory attribute
	 * 'destdir' set before calling this.
	 */
	public void execute() throws BuildException {
		validate();
		handleFiles(getFiles());
	}
	private void validate() throws BuildException {
		if (destdir == null) {
			throw new BuildException("Need to specify the 'destdir' attribute");
		}
		if (!destdir.exists() || !destdir.isDirectory()) {
			if (!destdir.mkdirs()) {
				throw new BuildException("Could not create the specified destdir");
			}
		}
	}

	/**
	 * Creates the following set of documents:<BR/>
	 * <PRE>
	 *   DestDir
	 *    |- index.html (the frames based index page)
	 *    |- overview-frame.html (list of all tag-libraries)
	 *    |- all-tags.html (list of all tags)
	 *    |- XXX (one directory per tag library)
	 *    |   |- taglib-frame.html (list of all tags in this library)
	 *    |   |- taglib-summary.html (summary of this library)
	 *    |   |- YYY.html (one file per Tag)
	 *    |   |- ...
	 *    |- ...
	 * </PRE>
	 * @param files input files in TLD format.
	 */
	public void handleFiles(List files) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			Element overviewRoot =
				Util.addChildElement(doc, "overview-frame", null);
			doc = db.newDocument();
			Element allTagsRoot = Util.addChildElement(doc, "all-tags", null);

			Iterator iter = files.iterator();
			while (iter.hasNext()) {
				File file = (File) iter.next();
				if (!file.exists() || !file.canRead()) {
					continue;
				}
				Document xmlDoc = handleFile(file);
				if (xmlDoc != null) {
					String shortname =
						Util.getElementValue(
							xmlDoc.getDocumentElement(),
							"body/taglib/shortname");
					Util.addChildElement(overviewRoot, "shortname", shortname);

					List tags =
						Util.getElements(
							xmlDoc.getDocumentElement(),
							"body/taglib/tag");
					Iterator tagIter = tags.iterator();
					while (tagIter.hasNext()) {
						Element tag = (Element) tagIter.next();
						String tagname = Util.getElementValue(tag, "name");
						tag = Util.addChildElement(allTagsRoot, "tag", null);
						Util.addChildElement(tag, "name", tagname);
						Util.addChildElement(tag, "taglib", shortname);
					}
				}
			}

			Source xml = new DOMSource(overviewRoot);
			Source xsl =
				new StreamSource(
					this.getClass().getResourceAsStream(
						"/resources/taglibdoc/index.xsl"));
			Result out = new StreamResult(new File(destdir, "index.html"));
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer(xsl);
			t.transform(xml, out);

			xsl =
				new StreamSource(
					this.getClass().getResourceAsStream(
						"/resources/taglibdoc/overview-frame.xsl"));
			out = new StreamResult(new File(destdir, "overview-frame.html"));
			t = tf.newTransformer(xsl);
			t.transform(xml, out);

			xml = new DOMSource(allTagsRoot);
			xsl =
				new StreamSource(
					this.getClass().getResourceAsStream(
						"/resources/taglibdoc/all-tags.xsl"));
			out = new StreamResult(new File(destdir, "all-tags.html"));
			t = tf.newTransformer(xsl);
			t.transform(xml, out);

			Util.copyFile(
				"/resources/taglibdoc/stylesheet.css",
				new File(destdir, "stylesheet.css"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Document handleFile(File file) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			String shortname =
				Util.getElementValue(
					doc.getDocumentElement(),
					"body/taglib/shortname");

			File dir = new File(destdir, shortname);
			dir.mkdir();

			File taglibFrameHtml = new File(dir, "taglib-frame.html");
			Source xml = new StreamSource(file);
			Source xsl =
				new StreamSource(
					this.getClass().getResourceAsStream(
						"/resources/taglibdoc/taglib-frame.xsl"));
			Result out = new StreamResult(taglibFrameHtml);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer(xsl);
			t.transform(xml, out);

			File taglibSummaryHtml = new File(dir, "taglib-summary.html");
			xml = new StreamSource(file);
			xsl =
				new StreamSource(
					this.getClass().getResourceAsStream(
						"/resources/taglibdoc/taglib-summary.xsl"));
			out = new StreamResult(taglibSummaryHtml);
			t = tf.newTransformer(xsl);
			t.transform(xml, out);

			List tags =
				Util.getElements(doc.getDocumentElement(), "body/taglib/tag");
			Iterator iter = tags.iterator();
			xsl =
				new StreamSource(
					this.getClass().getResourceAsStream(
						"/resources/taglibdoc/tag-frame.xsl"));
			t = tf.newTransformer(xsl);
			while (iter.hasNext()) {
				Element tag = (Element) iter.next();
				Util.addChildElement(tag, "taglib", shortname);
				String tagname = Util.getElementValue(tag, "name");
				File tagHtml = new File(dir, tagname + ".html");
				xml = new DOMSource(tag);
				out = new StreamResult(tagHtml);
				t.transform(xml, out);
			}

			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * MAIN METHOD
	 */
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println(
				"Usage: java " + TaglibDoc.class.getName() + " <file list>");
			System.exit(0);
		}

		File[] files = new File[args.length];
		for (int i = 0; i < files.length; i++)
			files[i] = new File(args[i]);
		TaglibDoc td = new TaglibDoc();
		td.handleFiles(Arrays.asList(files));

	}
}
