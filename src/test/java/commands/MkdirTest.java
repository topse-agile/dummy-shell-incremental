package commands;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import utilities.FileUtil;

public class MkdirTest {

	private Command command;
	private ByteArrayOutputStream out;

	@Before
	public void setUp() {
		FileUtil.deleteFolder(new File("./test"));
		this.command = new Mkdir();

		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
	}

	@Test
	public void testMakeDirectory() {

		command.run("dirA");

		assertTrue(new File("./test/dirA").isDirectory());
	}

	@Test
	public void testMakeDirectories() {

		command.run("dirA dirB");

		assertTrue(new File("./test/dirA").isDirectory());
		assertTrue(new File("./test/dirB").isDirectory());
	}

	@Test
	public void testMakeDirectorExist() {

		new File("./test/dirA").mkdir();
		command.run("dirA");

		assertTrue(new File("./test/dirA").isDirectory());
		assertThat(out.toString(), is("mkdir: dirA: File exists" + System.lineSeparator()));
	}

	@Test
	public void testArgumentNotIndicate() {

		command.run("");

		assertThat(out.toString(), is("usage: mkdir [-pv] [-m mode] directory ..." + System.lineSeparator()));
	}

	@Test
	public void testArgumentIs4Spaces() {

		command.run("     ");

		assertThat(out.toString(), is("usage: mkdir [-pv] [-m mode] directory ..." + System.lineSeparator()));
	}

	@Test
	public void testMakeDirectoryRecursive() {

		command.run("-p hoge/fuga");

		assertTrue(new File("./test/hoge").isDirectory());
		assertTrue(new File("./test/hoge/fuga").isDirectory());
		assertFalse(new File("./test/-p").exists());
	}

}
