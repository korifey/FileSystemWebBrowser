package org.example.filemanager.common.model.basic;

import org.example.filemanager.common.FileManagerException;
import org.example.filemanager.common.FileUtils;
import org.example.filemanager.common.model.IFileType;
import org.example.filemanager.common.model.IItem;

import java.io.File;
import java.util.Map;

public class AnyFolder implements IFileType {
	
	@Override
	public String getDescription(IItem f) {
		return "anyFolder.description";
	}
	
	@Override
	public String getIconUrl(IItem f) {		
		return "anyFolder.icon";
	}

	@Override
	public boolean matches(IItem f) {
		return ((f.getBackingObject() instanceof File)
				&& ((File)f.getBackingObject()).isDirectory());
	}

	@Override
	public boolean isListable(IItem f) {
		return true;
	}

	@Override
	public IItem[] list(IItem listable) {
		assert (listable.getBackingObject() instanceof File);
		
		
		File dir = (File)listable.getBackingObject();
		File[] backedFiles = dir.listFiles();
		
		if (backedFiles == null) {
			throw new FileManagerException("Can't list folder '"+listable.getFullname()+"'");
		}
		
		IItem[] res = new IItem[backedFiles.length];
		
		for (int i=0; i<backedFiles.length; i++) {
			File backed = backedFiles[i];
			
			res[i] = FileUtils.createFrom(backed, listable);
		}
		
		return res;
	}

	@Override
	public Map<String, Object> getAttributes(IItem item) {
		assert (item.getBackingObject() instanceof File);
		return FileUtils.createAttributeMap((File)item.getBackingObject());
	}

}
