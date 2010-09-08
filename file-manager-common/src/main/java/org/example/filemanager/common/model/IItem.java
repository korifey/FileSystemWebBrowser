package org.example.filemanager.common.model;

import java.util.Date;
import java.util.Map;

/**
 * File abstraction. Can denote folder, zip-file, zip-entry, remote files
 * and anything that satisfy this interface
 * @author daivanov
 *
 */
public interface IItem {
	/**
	 * Name of the file without extension.
	 * @return
	 */
	public String getBasename();
	
	/**
	 * File name
	 * @return
	 */
	public String getName();
	
	/**
	 * Fully qualifies path to file with OS-specific separator
	 * @return
	 */
	public String getFullname();
	
	/**
	 * File extension - name substring
	 * @return
	 */
	public String getExt();
	
	/**
	 * Parent if this item in the forest.
	 * @return
	 */
	public IItem getParent();
	
	/**
	 * File type. Can be considered as extended stategy pattern for this object.
	 * @return
	 */
	public IFileType getType();
	
	/**
	 * Real object, that is abstracted by this item. Can be java.io.File java.util.ZipEntry, etc.
	 * @return
	 */
	public Object getBackingObject();
	
	/** Should be delegated to {@link IFileType}*/
	public Map<String, Object> getAttributes();
	/** Should be delegated to {@link IFileType}*/
	public boolean isListable();
	/** Should be delegated to {@link IFileType}*/
	public IItem[] list();
	/** Should be delegated to {@link IFileType}*/
	public String getDescription();
	/** Should be delegated to {@link IFileType}*/
	public String getIconUrl();
}
