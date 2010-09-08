package org.example.filemanager.common.model.arch;

import org.example.filemanager.common.model.IFileAttribute;
import org.example.filemanager.common.model.IFileType;
import org.example.filemanager.common.model.IItem;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;

public class ZippedFileEntry implements IFileType {

	@Override
	public String getDescription(IItem item) {
		return "zipFileEntry.description";
	}

	@Override	
	public String getIconUrl(IItem item) {
		// We can think about how to show file type by extensions here
		// Now we doesn't support filetype in zip-file
		if (isListable(item)) {
			return "anyFolder.icon";
		} else {
			return "anyFile.icon";
		}
	}

	@Override
	public boolean matches(IItem item) {
		return (item.getBackingObject() instanceof ZipEntry || item.getBackingObject() instanceof Collection);
	}
	

	@Override
	public boolean isListable(IItem item) {
		// Collection for backing object is not very good, better to make personal class (like
		// ZipFolder) but for concrete task it's enough
		return item.getBackingObject() instanceof Collection;
	}

	
	@Override
	public IItem[] list(IItem listable) {
		assert listable.getBackingObject() instanceof Collection;
		return ((Collection<IItem>)listable.getBackingObject()).toArray(new IItem[0]);
	}
	
	
	@Override
	public Map<String, Object> getAttributes(IItem item) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		if (item.getBackingObject() instanceof Collection) {
			res.put(IFileAttribute.ATTRIBUTE_ELEMENT_NUMBER,  Integer.toString(((Collection<IItem>)item.getBackingObject()).size()));
		} else {
			ZipEntry entry = (ZipEntry)item.getBackingObject();

			
			if (entry.getCompressedSize() >=0) {
				res.put(ZipEntryAttributes.ATTRIBUTE_COMPRESSED_SIZE, Long.toString(entry.getCompressedSize()));
			}	
			
			if (entry.getSize() >=0) {
				res.put(IFileAttribute.ATTRIBUTE_SIZE, entry.getSize());
			}	
			
			if (entry.getTime() >= 0) {
				res.put(IFileAttribute.ATTRIBUTE_MODIFY_DATE, new Date(entry.getTime()));
			}	
			return res;
		}
		
		return res;
	}

}
