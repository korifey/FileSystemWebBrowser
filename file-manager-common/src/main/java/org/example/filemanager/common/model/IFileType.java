package org.example.filemanager.common.model;

import java.io.Serializable;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Extension point that encapsulates logic of handling particular file type.
 * To add new file type to system one must implement this class and 
 * use {@link ServiceLoader} technique  
 * @author daivanov
 *
 */
public interface IFileType extends Serializable {
	
	/**
	 * Readable description of file type. Extension for this  must declare property in property file for this key.
	 * @param f
	 * @return
	 */
	public String getDescription(IItem f);
	
	/**
	 * Key of icon url. Extension for this  must declare property in property file for this key.
	 * @param f
	 * @return
	 */
	public String getIconUrl(IItem f);
	
	/**
	 * 
	 * @param f
	 * @return
	 */
	public boolean matches(IItem f);
	
	/**
	 * Whether we can browse this item as folder
	 * @param f
	 * @return
	 */
	public boolean isListable(IItem f);
	
	/**
	 * Folder contents (first level og hierarchy)
	 * @param listable
	 * @return
	 */
	public IItem[] list(IItem listable); 
	
	/**
	 * Attributes of item like specified in {@link IFileAttribute} and maybe
	 * extensions. For extension keys properties must be declared.
	 * 
	 * @param item
	 * @return
	 */
	public Map<String, Object> getAttributes(IItem item); 
}
