package org.example.filemanager.common.model.arch;

import org.example.filemanager.common.FileManagerException;
import org.example.filemanager.common.FileUtils;
import org.example.filemanager.common.model.IFileType;
import org.example.filemanager.common.model.IItem;
import org.example.filemanager.common.model.impl.FileImpl;

import java.io.File;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZippedFile implements IFileType {

	@Override
	public String getDescription(IItem f) {
		return "zipFile.description";
	}

	@Override
	public String getIconUrl(IItem f) {		
		return "zipFile.icon";
	}

	@Override
	public boolean matches(IItem f) {
		return (f.getBackingObject() instanceof File) && ("zip".equals(f.getExt()) || "jar".equals(f.getExt()));
	}

	@Override
	public boolean isListable(IItem f) {
		return true;
	}

	
	@Override
	public IItem[] list(IItem listable) {
		assert listable.getBackingObject() instanceof File;
		
		File file = (File)listable.getBackingObject();
		ZipFile zf = null;
		try {
			zf = new ZipFile(file);
		} catch (Exception e) {
			
			throw new FileManagerException("Can't open ZIP archive: '"+listable+"'", e);
		}
		
		//Here is a trick: if we have to read all zip entries, it's suitable to make a full tree
		//for the zip file.
		
		HashMap<String, IItem> zipContentsMap = new HashMap<String, IItem>();
		ArrayList<IItem> firstLevelItems = new ArrayList<IItem>();
		
		for (Enumeration<? extends ZipEntry> entries = zf.entries(); entries.hasMoreElements();) {
			ZipEntry entry = entries.nextElement();
			String[] splitted = entry.getName().split("/");
			
			//don't think we need string builder here (too many tooString() would be needed)
			String acc = "";
			IItem parent = listable;
			for (int i=0; i<splitted.length; i++) {
				if (i != 0) {
					acc += "/";
				}
				acc += splitted[i];
				
				IItem item = zipContentsMap.get(acc);
				
				//creating new entry if necessary
				if (item == null) {
					item = new FileImpl(splitted[i],
							listable.getFullname() + File.separator+acc.replace("/", File.separator),
							parent,
							
							//if folder, than backing object is collection (yeah, sucks), otherwise ZipEntry
							(i == splitted.length - 1 && !entry.isDirectory())? entry : new ArrayList<IItem>());							
					                                      
					zipContentsMap.put(acc,item);
					
					if (parent != listable) {
						//expanding parent collection
						((Collection<IItem>)parent.getBackingObject()).add(item);
					} else {
						firstLevelItems.add(item);
					}
				}
				parent = item;
				
			}
			
		}
		
		
		return firstLevelItems.toArray(new IItem[0]);
	}
	
	
	@Override
	public Map<String, Object> getAttributes(IItem item) {
		assert (item.getBackingObject() instanceof File);
		return FileUtils.createAttributeMap((File)item.getBackingObject());
	}
	

}
