package org.example.filemanager.web.model;

import org.example.filemanager.common.model.IItem;

import java.util.Arrays;

/**
 * Folder as it viewed by Web UI
 * @author daivanov
 *
 */
public class TreeFolder {
	private IItem folder;
	
	/**
	 * Level of hierchy from root. E.g. file '/usr/local/bin' would have
	 * level '2', '/usr/local' - 1, '/usr' - 0.
	 */
	private int offset;
	private boolean opened;
	
	public TreeFolder(IItem folder, int offset) {
		this.folder = folder;
		this.offset = offset;
	}
	public IItem getFolder() {
		return folder;
	}
	public int getOffset() {
		return offset;
	}
	
	public boolean isOpened() {
		return opened;
	}
	public boolean getOpened() {
		return opened;
	}
	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	
	
	public String toString() {
		char [] off = new char[2*offset+1];
		Arrays.fill(off, ' ');
		
		StringBuilder res = new StringBuilder(new String(off));
		res.append(' ');
		res.append(opened ? '-' : '+');
		res.append(' ');
		res.append(folder);
		
		return res.toString();
	}
	
	
	
	
	
}
