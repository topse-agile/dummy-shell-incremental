package commands;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class LsTest {

	private Command command;

	@Before
	public void setUp() {
		deleteFolder(new File("./test"));
		this.command = new Ls();		
	}

	@Test
	public void testDisplayFilesWhenOnlyFileExist() throws IOException {
		File file1 = new File("./test/file1.txt");
		File file2 = new File("./test/file2.txt");

		file1.createNewFile();
		file2.createNewFile();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		this.command.run("");

		assertThat(out.toString(), is("file1.txt    file2.txt" + System.lineSeparator()));
	}

	@Test
	public void testDisplayFilesWhenOnlyFolder() throws IOException {
		File file1 = new File("./test/file1.txt");
		File file2 = new File("./test/file2.txt");
		File dir = new File("./test/dir1");

		file1.createNewFile();
		file2.createNewFile();
		dir.mkdir();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		this.command.run("");

		assertThat(out.toString(), is("dir1    file1.txt    file2.txt" + System.lineSeparator()));
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

}
