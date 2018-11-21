import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;

import org.junit.Test;

public class DummyShellTest {

	@Test
	public void testEcho() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		DummySchell.echo("Hello World!");

		assertThat(out.toString(), is("Hello World!" + System.lineSeparator()));// printlnの改行部分はlineSeparatorを付ける
	}

	@Test
	public void testEchoWithDoubleQuote() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		DummySchell.echo("\"Hello World!\"");

		assertThat(out.toString(), is("Hello World!" + System.lineSeparator()));// printlnの改行部分はlineSeparatorを付ける
	}

	@Test
	public void testEchoWithSingleQuote() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		DummySchell.echo("'Hello World!'");

		assertThat(out.toString(), is("Hello World!" + System.lineSeparator()));// printlnの改行部分はlineSeparatorを付ける
	}

	@Test
	public void testMakefile() {

		new File("file1.txt").delete();

		DummySchell.touch("file1.txt");

		assertTrue(new File("file1.txt").exists());
	}

	@Test
	public void testMakefiles() {
		new File("file1.txt").delete();
		new File("file2.txt").delete();

		DummySchell.touch("file1.txt file2.txt");

		assertTrue(new File("file1.txt").exists());
		assertTrue(new File("file2.txt").exists());
	}

	@Test
	public void testNullFileName() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		DummySchell.touch("");

		assertThat(out.toString(), is("usage:" + System.lineSeparator()));
	}

	@Test
	public void testUpdatefileTimestamp() throws IOException {

		File file = new File("file1.txt");
		file.createNewFile();

		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2018, 10, 01, 20, 44, 00);
		file.setLastModified(cal.getTimeInMillis());

		DummySchell.touch("file1.txt");

		Long lastModifiedTime = file.lastModified();

		assertThat(lastModifiedTime / 1000, is(Calendar.getInstance().getTime().getTime() / 1000));

	}

	@Test
	public void testUpdatefilesTimestamp() throws IOException {

		File file = new File("file1.txt");
		file.createNewFile();

		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2018, 10, 01, 20, 44, 00);
		file.setLastModified(cal.getTimeInMillis());

		File file2 = new File("file2.txt");
		file2.createNewFile();

		file2.setLastModified(cal.getTimeInMillis());

		DummySchell.touch("file1.txt file2.txt");

		Long lastModifiedTime = file.lastModified();

		assertThat(lastModifiedTime / 1000, is(Calendar.getInstance().getTime().getTime() / 1000));

		Long lastModifiedTime2 = file2.lastModified();

		assertThat(lastModifiedTime2 / 1000, is(Calendar.getInstance().getTime().getTime() / 1000));

	}

	@Test
	public void testDisplayFilesWhenOnlyFileExist() throws IOException {

		deleteFolder(new File("./test"));

		File file1 = new File("./test/file1.txt");
		File file2 = new File("./test/file2.txt");

		file1.createNewFile();
		file2.createNewFile();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		DummySchell.ls("");

		assertThat(out.toString(), is("file1.txt    file2.txt" + System.lineSeparator()));

	}

	private void deleteFolder(File dir) {

		File[] paths = dir.listFiles();
		if (paths == null)
			return;
		for (File path : paths) {
			if (!path.exists())
				continue;
			else if (path.isDirectory()) {
				deleteFolder(path);
				path.delete();
			} else if (path.isFile())
				path.delete();
		}
	}

	@Test
	public void testDisplayFilesWhenOnlyFolder() throws IOException {

		deleteFolder(new File("./test"));

		File file1 = new File("./test/file1.txt");
		File file2 = new File("./test/file2.txt");
		File dir = new File("./test/dir1");

		file1.createNewFile();
		file2.createNewFile();
		dir.mkdir();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		DummySchell.ls("");

		assertThat(out.toString(), is("dir1    file1.txt    file2.txt" + System.lineSeparator()));

	}
}