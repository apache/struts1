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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.tools.ant.types.FileSet;

/**
 * ENHanced-MatchingTask - enhances the MatchingTask by allowing
 * the subclasses to act as a FileSet container as well as allowing
 * the implicit FileSet.
 *
 * @version $Rev$ $Date$
 */

public abstract class EnhMatchingTask extends MatchingTask {
	/** Base directory for implicit FileSet */
	protected File dir;
	/** List of embeded filesets */
	protected List filesets = new ArrayList();

	/**
	 * Set the base directory for the implicit FileSet
	 * @param base directory for implicit FileSet
	 */
	public void setDir(File dir) {
		this.dir = dir;
	}
	/**
	 * Adds an embeded FileSet for this task.
	 * @param the FileSet to add
	 */
	public void addFileset(FileSet fileset) {
		filesets.add(fileset);
	}

	/**
	 * Returns the combined list of Files, from both the
	 * implicit and embeded FileSets.
	 * @return list of File objects
	 */
	protected List getFiles() {
		List files = new ArrayList();
		if (dir != null) {
			fileset.setDir(dir);
			files.addAll(getFiles(fileset));
		}
		Iterator iter = filesets.iterator();
		while (iter.hasNext()) {
			files.addAll(getFiles((FileSet) iter.next()));
		}
		return files;
	}
	private List getFiles(FileSet fs) {
		List files = new ArrayList();
		DirectoryScanner ds = fs.getDirectoryScanner(fs.getProject());
		File dir = ds.getBasedir();
		String[] filenames = ds.getIncludedFiles();
		for (int i = 0; i < filenames.length; i++) {
			File file = new File(dir, filenames[i]);
			files.add(file);
		}
		return files;
	}
}