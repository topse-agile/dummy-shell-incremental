package commands;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class TouchTest {

	private Command command;

	@Before
	public void setUp() {
		this.command = new Touch();
	}

	@Test
	public void testMakefile() {

		new File("./test/file1.txt").delete();

		this.command.run("file1.txt");

		assertTrue(new File("./test/file1.txt").exists());
	}

	@Test
	public void testMakefiles() {
		new File("./test/file1.txt").delete();
		new File("./test/file2.txt").delete();

		this.command.run("file1.txt file2.txt");

		assertTrue(new File("./test/file1.txt").exists());
		assertTrue(new File("./test/file2.txt").exists());
	}

	@Test
	public void testNullFileName() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		this.command.run("");

		assertThat(out.toString(), is("usage:" + System.lineSeparator()));
	}

	@Test
	public void testUpdatefileTimestamp() throws IOException {

		File file = new File("./test/file1.txt");
		file.createNewFile();

		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2018, 10, 01, 20, 44, 00);
		file.setLastModified(cal.getTimeInMillis());

		this.command.run("file1.txt");

		Long lastModifiedTime = file.lastModified();

		assertThat(lastModifiedTime / 1000, is(Calendar.getInstance().getTime().getTime() / 1000));

	}

	@Test
	public void testUpdatefilesTimestamp() throws IOException {

		File file = new File("./test/file1.txt");
		file.createNewFile();

		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2018, 10, 01, 20, 44, 00);
		file.setLastModified(cal.getTimeInMillis());

		File file2 = new File("./test/file2.txt");
		file2.createNewFile();

		file2.setLastModified(cal.getTimeInMillis());

		this.command.run("file1.txt file2.txt");

		Long lastModifiedTime = file.lastModified();

		assertThat(lastModifiedTime / 1000, is(Calendar.getInstance().getTime().getTime() / 1000));

		Long lastModifiedTime2 = file2.lastModified();

		assertThat(lastModifiedTime2 / 1000, is(Calendar.getInstance().getTime().getTime() / 1000));

	}

}
