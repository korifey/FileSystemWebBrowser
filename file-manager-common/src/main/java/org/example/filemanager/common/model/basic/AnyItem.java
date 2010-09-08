package org.example.filemanager.common.model.basic;

import java.io.File;
import java.util.Collections;
import java.util.Map;

import org.example.filemanager.common.FileUtils;
import org.example.filemanager.common.model.IFileType;
import org.example.filemanager.common.model.IItem;
import org.example.filemanager.common.model.impl.FileImpl;

public class AnyItem implements IFileType {
	
	@Override
	public String getDescription(IItem f) {
		return "anyFile.description";
	}
	
	@Override
	public String getIconUrl(IItem f) {
		return "anyFile.icon";
	}

	@Override
	public boolean matches(IItem f) {
		//every file matches to it (may be even no java.io.File as backing object)
		return true;
	}

	@Override
	public boolean isListable(IItem f) {
		return false;
	}

	@Override
	public IItem[] list(IItem listable) {
		//It's illegal to invoke this 
		assert false;
		throw new IllegalArgumentException("Can't invoke list() on plain file");
	}
	
	@Override
	public Map<String, Object> getAttributes(IItem item) {
		if (item.getBackingObject() instanceof File) {
			return FileUtils.createAttributeMap((File)item.getBackingObject());
		} else {
			return Collections.EMPTY_MAP;
		}
		
	}

}
