package org.example.filemanager.common.model.arch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.example.filemanager.common.FileManagerException;
import org.example.filemanager.common.FileUtils;
import org.example.filemanager.common.model.IFileType;
import org.example.filemanager.common.model.IItem;
import org.example.filemanager.common.model.impl.FileImpl;

public class RaredFile implements IFileType {

	@Override
	public String getDescription(IItem f) {
		return "rarFile.description";
	}

	@Override
	public String getIconUrl(IItem f) {		
		return "rarFile.icon";
	}

	@Override
	public boolean matches(IItem f) {
		return (f.getBackingObject() instanceof File) && ("rar".equals(f.getExt()));
	}

	@Override
	public boolean isListable(IItem f) {
		return true;
	}

	
	@Override
	public IItem[] list(IItem listable) {
		throw new FileManagerException("RAR listing isn't supported");
	}
	
	
	@Override
	public Map<String, Object> getAttributes(IItem item) {
		return FileUtils.createAttributeMap((File)item.getBackingObject());
	}

}
