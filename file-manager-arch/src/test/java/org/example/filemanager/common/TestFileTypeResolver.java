package org.example.filemanager.common;

import static org.junit.Assert.*;

import java.io.File;
import java.util.UUID;

import org.example.filemanager.common.model.IItem;
import org.example.filemanager.common.model.arch.RaredFile;
import org.example.filemanager.common.model.arch.ZippedFile;
import org.example.filemanager.common.model.basic.AnyItem;
import org.example.filemanager.common.model.basic.AnyFolder;
import org.example.filemanager.common.model.basic.TextFile;
import org.example.filemanager.common.model.impl.FileImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestFileTypeResolver {
	

	
	private static IItem tmp;
	
	@BeforeClass
	public static void setUp() {
		File f = new File(new File(System.getProperty("java.io.tmpdir")),UUID.randomUUID().toString());
		f.mkdirs();
		tmp = FileUtils.createFrom(f, null);
	}	
	
	@AfterClass
	public static void tearDown() {
		((File)tmp.getBackingObject()).delete();
	}
	
	
	@Test
	public void testZipFile() {
		File f = new File((File)tmp.getBackingObject(), ".111.zip");
		IItem item = FileUtils.createFrom(f, tmp);
		assertEquals(ZippedFile.class, item.getType().getClass());
	}
	
	@Test
	public void testRarFile() {
		File f = new File((File)tmp.getBackingObject(), ".111.rar");
		IItem item = FileUtils.createFrom(f, tmp);
		assertEquals(RaredFile.class, item.getType().getClass());
	}
}
