package org.example.filemanager.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.filemanager.common.model.IFileType;
import org.example.filemanager.common.model.IItem;
import org.example.filemanager.common.model.basic.AnyItem;
import org.example.filemanager.common.model.basic.AnyFolder;
import org.example.filemanager.common.model.basic.BaseFolder;
import org.example.filemanager.common.model.basic.TextFile;

/**
 * Singleton that resolves item type by item
 * @author daivanov
 *
 */
public class FileTypeResolver {
	
	private Log log = LogFactory.getLog(FileTypeResolver.class);
	
	private static FileTypeResolver instance = new FileTypeResolver();
	protected LinkedList<IFileType> fileTypeChain = new LinkedList<IFileType>();  
	
	protected FileTypeResolver() {
		fileTypeChain.addFirst(new AnyItem());
		fileTypeChain.addFirst(new AnyFolder());
		fileTypeChain.addFirst(new BaseFolder());
		fileTypeChain.addFirst(new TextFile());
		
		log.info("Added default types: "+Arrays.toString(fileTypeChain.toArray()));
		
		//Here we are searching for plugins
		ServiceLoader<IFileType> sloader = ServiceLoader.load(IFileType.class);
		for (Iterator<IFileType> it = sloader.iterator(); it.hasNext();) {
			IFileType type = it.next();
			log.info("Adding plugin file type: "+type.getClass().getName());
			fileTypeChain.addFirst(type);
		}
	}

	
	public static FileTypeResolver getInstance() {
		return instance;
	}
	
	
	public IFileType resolve(IItem f) {
		assert f != null;
		
		for (IFileType type: fileTypeChain) {
			if (type.matches(f)) {
				return type;
			}
		}
		
		/* Can't reach this point, AnyFile and AnyFolder must match to any file */
		assert false;
		return null;
	}
}
