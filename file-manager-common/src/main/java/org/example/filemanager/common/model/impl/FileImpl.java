package org.example.filemanager.common.model.impl;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.example.filemanager.common.FileTypeResolver;
import org.example.filemanager.common.model.IFileAttribute;
import org.example.filemanager.common.model.IFileType;
import org.example.filemanager.common.model.IItem;

/**
 * Common implementation. Should be enough for most purposes. 
 * This class isn't an extension point, see {@link IFileType}
 * @author daivanov
 *
 */
public class FileImpl implements IItem {

	private String name;
	private String fullname;
	
	private IFileType type;
	private Object backingObject;
	
	//evaluated attribute
	private Map<String, String> attributes;
	
	/**
	 * Invariant: every {@link FileImpl} object has a parent except root (e.g. win drive like 'C:\')  
	 */
	private IItem parent;
	
	public FileImpl(String name, String fullname, IItem parent, Object backingObject) {
		this.name = name;
		this.fullname = fullname;
		this.parent = parent;
		this.backingObject = backingObject;
		
		this.type = FileTypeResolver.getInstance().resolve(this);
	}
	
	@Override
	public String getBasename() {
		assert (name != null);
		
		//Make an exception for regular folder
		if (backingObject instanceof File && ((File)backingObject).isDirectory()) return name; 
		
		if (name.lastIndexOf('.') > 0) {
			return name.substring(0, name.lastIndexOf('.'));
		} else {
			return name;
		}
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getFullname() {
		return fullname;
	}
	
	@Override
	public String getExt() {
		assert (name != null);
		
		//Make an exception for regular folder
		if (backingObject instanceof File && ((File)backingObject).isDirectory()) return null;
		
		if (name.lastIndexOf('.') > 0) {
			return name.substring(name.lastIndexOf('.')+1);
		} else {
			return null;
		}
	}


	@Override
	public IItem getParent() {
		return parent;
	}


	@Override
	public IFileType getType() {
		return type;
	}


	@Override
	public Object getBackingObject() {
		return backingObject;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return getType().getAttributes(this);
	}

	
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(name);
		res.append("[");
		res.append(type.getClass().getSimpleName());
		res.append(']');
		return res.toString();
	}

	@Override
	public boolean isListable() {
		return type.isListable(this);
	}

	@Override
	public IItem[] list() {
		return type.list(this);
	}

	@Override
	public String getDescription() {
		return type.getDescription(this);
	}

	@Override
	public String getIconUrl() {
		return type.getIconUrl(this);
	}
	
	
	
	
	
}
