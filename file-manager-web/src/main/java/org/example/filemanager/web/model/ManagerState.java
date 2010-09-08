package org.example.filemanager.web.model;

import org.example.filemanager.common.FileManagerException;
import org.example.filemanager.common.FileUtils;
import org.example.filemanager.common.model.IItem;

import java.util.ArrayList;
import java.util.List;

/**
 * State of left and right panels 
 * @author daivanov
 *
 */
public class ManagerState {
	
	public final static int UNSPECIFIED_INDEX = -1;
	
	/**
	 * Why Sun crew made this useful method protected?
	 * @author daivanov
	 *
	 * @param <T>
	 */
	public static class IntervalRemoveArrayList<T> extends ArrayList<T> {
		@Override
		public void removeRange(int fromIndex, int toIndex) {
			super.removeRange(fromIndex, toIndex);
		}
		
	}
	
	public IntervalRemoveArrayList<TreeFolder> treePanelContent;
	public List<IItem> filePanelContent;
	public int curTreeItemIndex = UNSPECIFIED_INDEX;
	public int curFileItemIndex = UNSPECIFIED_INDEX;
	
	public ManagerState() {
		treePanelContent = new IntervalRemoveArrayList<TreeFolder>();
		for (IItem item: FileUtils.createRoots()) {
			//Sometimes even roots aren't listable!
			if (item.isListable()) {
				treePanelContent.add(new TreeFolder(item, 0));
			}	
		}

		filePanelContent = new ArrayList<IItem>();
	}
	
	
	public int getCurTreeItemIndex() {
		return curTreeItemIndex;
	}

	public void setCurTreeItemIndex(int curTreePanelItemIndex) {
		this.curTreeItemIndex = curTreePanelItemIndex;
	}
	

	public int getCurFileItemIndex() {
		return curFileItemIndex;
	}


	public void setCurFileItemIndex(int curFileItemIndex) {
		this.curFileItemIndex = curFileItemIndex;
	}


	public List<TreeFolder> getTreePanelContent() {
		return treePanelContent;
	}


	public List<IItem> getFilePanelContent() {
		return filePanelContent;
	}
	
	
	//Validators
	public void validateTreeIndex(int idx) {
		if (idx<0 || idx >= treePanelContent.size()) {
			throw new FileManagerException("Tree index ('"+idx+"') is invalid");
		}
	}
	
	private void validateTreeIndex() {
		if (curTreeItemIndex != UNSPECIFIED_INDEX 
				&& (curTreeItemIndex<0 || curTreeItemIndex >= treePanelContent.size())) {
			int idx = curTreeItemIndex;
			curTreeItemIndex = UNSPECIFIED_INDEX;
			throw new FileManagerException("Tree index ('"+idx+"') is invalid");
		}
	}
	
	private void validateFileIndex() {
		if (curFileItemIndex != UNSPECIFIED_INDEX 
				&& (curFileItemIndex<0 || curFileItemIndex >= filePanelContent.size())) {
			int idx = curFileItemIndex;
			curFileItemIndex = UNSPECIFIED_INDEX;			
			throw new FileManagerException("File index ('"+idx+"') is invalid");
		}
	}
	
	
	public TreeFolder getCurrentTreeItem() {
		validateTreeIndex();
		if (curTreeItemIndex == UNSPECIFIED_INDEX) {
			return null;
		} else {
			return treePanelContent.get(curTreeItemIndex);
		}	
	}
	
	public IItem getCurrentFileItem() {
		validateFileIndex();
		if (curFileItemIndex == UNSPECIFIED_INDEX) {
			return null;
		} else {
			return filePanelContent.get(curFileItemIndex);
		}	
	}

	
	//Business logic
	public void expandOrCollapse(int idx) {
		validateTreeIndex(idx);
		TreeFolder tf = treePanelContent.get(idx);
		
		if (tf.isOpened()) {
			int i=idx+1;
			while (i<treePanelContent.size() && treePanelContent.get(i).getOffset() > tf.getOffset()) {
				i++;
			}
			
			if (curTreeItemIndex >idx && curTreeItemIndex <i) {
				curTreeItemIndex = UNSPECIFIED_INDEX;
			}
			
			treePanelContent.removeRange(idx+1, i);
			tf.setOpened(false);
		} else {
			ArrayList<TreeFolder> newElements = new ArrayList<TreeFolder>();
			for (IItem item: tf.getFolder().list()) {
				if (item.isListable()) {
					newElements.add(new TreeFolder(item, tf.getOffset()+1));
				}
			}
			treePanelContent.addAll(idx+1, newElements);
			
			if (curTreeItemIndex >idx) {
				curTreeItemIndex += newElements.size();
			}
			
			tf.setOpened(true);
		}	
	}
	
	//Business logic
	public void openCurrentOnFilePanel() {
		validateFileIndex();
		
		filePanelContent.clear();
		filePanelContent = new ArrayList();
		for (IItem item: getCurrentTreeItem().getFolder().list()) {
			filePanelContent.add(item);
		}
	}
	
	
	public String toString() {
		validateTreeIndex();
		validateFileIndex();
		
		StringBuilder res = new StringBuilder();
		res.append("\nManagerState:(\n");
		res.append(" tree panel: {\n");
		for (TreeFolder f: treePanelContent) {
			res.append(f);
			res.append('\n');
		}
		
		String curTreePath = curTreeItemIndex == UNSPECIFIED_INDEX ? "null" : getCurrentTreeItem().getFolder().getFullname();
		res.append(" }  current="+curTreePath+"\n");
		
		res.append("\n file panel: {\n");
		for (IItem f: filePanelContent) {
			res.append(f);
			res.append('\n');
		}
		
		String curFilePath = curFileItemIndex == UNSPECIFIED_INDEX ? "null" : getCurrentFileItem().getFullname();
		res.append(" }  current="+curFilePath+"\n");
		res.append(")");
		
		return res.toString();
		
	}
	
}
