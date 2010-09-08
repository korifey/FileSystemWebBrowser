package org.example.filemanager.common;

import org.example.filemanager.common.model.IItem;
import org.example.filemanager.common.model.basic.AnyFolder;
import org.example.filemanager.common.model.basic.AnyItem;
import org.example.filemanager.common.model.basic.BaseFolder;
import org.example.filemanager.common.model.basic.TextFile;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

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
	public void testBaseFolder() {
		File f = File.listRoots()[0];
		IItem root = FileUtils.createFrom(f, null);
		assertEquals(BaseFolder.class, root.getType().getClass());
	}
	
	@Test
	public void testAnyFile1() {
		File f = new File((File)tmp.getBackingObject(), "111");
		IItem item = FileUtils.createFrom(f, tmp);
		assertEquals(AnyItem.class, item.getType().getClass());
	}
	
	@Test
	public void testAnyFile2() {
		File f = new File((File)tmp.getBackingObject(), ".111");
		IItem item = FileUtils.createFrom(f, tmp);
		assertEquals(AnyItem.class, item.getType().getClass());
	}
	
	@Test
	public void testAnyFile3() {
		File f = new File((File)tmp.getBackingObject(), ".111.txt.");
		IItem item = FileUtils.createFrom(f, tmp);
		assertEquals(AnyItem.class, item.getType().getClass());
	}
	
	@Test
	public void testAnyFolder() {
		File f = new File((File)tmp.getBackingObject(), ".111.txt");
		f.mkdirs();
		IItem item = FileUtils.createFrom(f, tmp);
		assertEquals(AnyFolder.class, item.getType().getClass());
	}
	
	@Test
	public void testTextFile() {
		File f = new File((File)tmp.getBackingObject(), ".222.txt");
		IItem item = FileUtils.createFrom(f, tmp);
		assertEquals(TextFile.class, item.getType().getClass());
	}

}
