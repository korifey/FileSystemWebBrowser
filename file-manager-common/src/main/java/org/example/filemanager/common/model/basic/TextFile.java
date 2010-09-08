package org.example.filemanager.common.model.basic;

import org.example.filemanager.common.model.IItem;

import java.io.File;

/**
 * Txt file here is an example of file type that matches by extension 
 * @author daivanov
 *
 */
public class TextFile extends AnyItem {

	@Override
	public String getDescription(IItem f) {
		return "textFile.description";
	}

	@Override
	public String getIconUrl(IItem f) {		
		return "textFile.icon";
	}

	@Override
	public boolean matches(IItem f) {
		return (f.getBackingObject() instanceof File)
				&& !((File)f.getBackingObject()).isDirectory() 
				&& "txt".equals(f.getExt());
	}

}
