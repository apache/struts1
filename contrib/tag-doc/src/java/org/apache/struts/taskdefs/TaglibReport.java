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
import java.util.Set;
import java.util.TreeSet;

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
 * Descriptor (TLD) format, creates a matrix of Tags Vs
 * Attributes for each Tag-Library.
 *
 * @version $Rev$ $Date$
 */
public class TaglibReport extends EnhMatchingTask {
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
	 *    |- overview-frame.html (menu of all tag-libraries)
	 *    |- XXX-report.html (one file per tag-library)
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

					File reportHtml =
						new File(destdir, shortname + "-report.html");
					Source xml = new DOMSource(xmlDoc);
					Source xsl =
						new StreamSource(
							this.getClass().getResourceAsStream(
								"/resources/taglibreport/taglib-report.xsl"));
					Result out = new StreamResult(reportHtml);
					TransformerFactory tf = TransformerFactory.newInstance();
					Transformer t = tf.newTransformer(xsl);
					t.transform(xml, out);
				}
			}

			Source xml = new DOMSource(overviewRoot);
			Source xsl =
				new StreamSource(
					this.getClass().getResourceAsStream(
						"/resources/taglibreport/index.xsl"));
			Result out = new StreamResult(new File(destdir, "index.html"));
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer(xsl);
			t.transform(xml, out);

			xsl =
				new StreamSource(
					this.getClass().getResourceAsStream(
						"/resources/taglibreport/overview-frame.xsl"));
			out = new StreamResult(new File(destdir, "overview-frame.html"));
			t = tf.newTransformer(xsl);
			t.transform(xml, out);

			Util.copyFile(
				"/resources/taglibreport/stylesheet.css",
				new File(destdir, "stylesheet.css"));
			Util.copyFile(
				"/resources/taglibreport/xbPositionableElement.js",
				new File(destdir, "xbPositionableElement.js"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Document handleFile(File file) {
		try {
			Set attrSet = new TreeSet();

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);

			List tags =
				Util.getElements(doc.getDocumentElement(), "body/taglib/tag");
			Iterator iter = tags.iterator();
			while (iter.hasNext()) {
				Element tag = (Element) iter.next();
				List attrList = Util.getElements(tag, "attribute");
				Iterator attrIter = attrList.iterator();
				while (attrIter.hasNext()) {
					Element attribute = (Element) attrIter.next();
					String name = Util.getElementValue(attribute, "name");
					attrSet.add(name);
				}
			}

			Element attributes =
				Util.addChildElement(
					doc.getDocumentElement(),
					"attributes",
					null);
			iter = attrSet.iterator();
			while (iter.hasNext()) {
				String name = (String) iter.next();
				Util.addChildElement(attributes, "attribute", name);
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
				"Usage: java " + TaglibReport.class.getName() + " <file list>");
			System.exit(0);
		}

		File[] files = new File[args.length];
		for (int i = 0; i < files.length; i++)
			files[i] = new File(args[i]);
		TaglibDoc td = new TaglibDoc();
		td.handleFiles(Arrays.asList(files));

	}
}
