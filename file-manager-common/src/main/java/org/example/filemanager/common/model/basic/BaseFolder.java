package org.example.filemanager.common.model.basic;

import org.example.filemanager.common.model.IItem;

//Can be inserted into AnyFolder but I think it should be separate
/**
 * Roots of the foreds (e.g., 'c:', 'd:' drives in Win or '/' in Unix)
 */
public class BaseFolder extends AnyFolder  {
	
	@Override
	public String getDescription(IItem f) {
		return "baseFolder.description";
	}
	
	@Override
	public String getIconUrl(IItem f) {
		return "baseFolder.icon";
	}

	@Override
	public boolean matches(IItem f) {
		return super.matches(f) && f.getParent() == null;
	}

}
