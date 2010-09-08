package org.example.filemanager.common;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.filemanager.common.model.IFileAttribute;
import org.example.filemanager.common.model.IItem;
import org.example.filemanager.common.model.impl.FileImpl;

/**
 * Handful IO static methods
 * @author daivanov
 *
 */
public class FileUtils {
	
	static Log log = LogFactory.getLog(FileUtils.class);
	
	
	/**
	 * Get rid of '/' or '\' in the end
	 */
	public static String clearTailingSeparator(String str) {
		return str.endsWith(java.io.File.separator) ?
				str.substring(0, str.indexOf(java.io.File.separator))
				: str;
	}
	
	/**
	 * Creates item from IO file
	 * @param f
	 * @param parent
	 * @return
	 */
	public static IItem createFrom(java.io.File f, IItem parent) {
		String name = parent == null ?
				clearTailingSeparator(f.getAbsolutePath())
				: clearTailingSeparator(f.getName());
		return  new FileImpl(name,
				clearTailingSeparator(f.getAbsolutePath()),
				parent,
				f);		
	}
	
	/**
	 * Create initiak (root) items
	 * @return
	 */
	public static IItem[] createRoots() {
		java.io.File[] roots = java.io.File.listRoots();
		
		IItem[] res = new IItem[roots.length];
		for (int i=0; i<roots.length; i++) {			
			res[i] = createFrom(roots[i], null);
			log.info("Root folder loaded:"+res[i].getName());
		}
		
		return res;
	}
	
	/**
	 * Create attributes for usual files and folders
	 * @param f
	 * @return
	 */
	public static Map<String, Object> createAttributeMap(File f) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(IFileAttribute.ATTRIBUTE_MODIFY_DATE, new Date(f.lastModified()));
		if (!f.isDirectory()) {
			res.put(IFileAttribute.ATTRIBUTE_SIZE, f.length());
		}	
		
		if (f.isDirectory()) {
			File[] ff = f.listFiles();
			if (ff != null) {
				res.put(IFileAttribute.ATTRIBUTE_ELEMENT_NUMBER, Integer.toString(ff.length));
			}	
		}
		return res;
		
	}
}
