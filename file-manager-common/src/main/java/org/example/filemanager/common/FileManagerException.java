package org.example.filemanager.common;

public class FileManagerException extends RuntimeException {
	public FileManagerException(String msg) {
		super(msg);
	}
	
	public FileManagerException(String msg, Throwable cause) {
		super(msg, cause);
		
	}
}
