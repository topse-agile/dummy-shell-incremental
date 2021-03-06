package commands;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import utilities.FileUtil;

public class LsTest {

	private Command command;
	private final String SEPARATOR = "    ";

	private ByteArrayOutputStream out;

	@Before
	public void setUp() {
		FileUtil.deleteFolder(new File("./test"));
		this.command = new Ls();

		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

	}

	@Test
	public void testDisplayFilesWhenOnlyFileExist() throws IOException {
		File file1 = new File("./test/file1.txt");
		File file2 = new File("./test/file2.txt");

		file1.createNewFile();
		file2.createNewFile();

		this.command.run("");

		assertThat(out.toString(), is("file1.txt" + SEPARATOR + "file2.txt" + System.lineSeparator()));
	}

	@Test
	public void testDisplayNoFilesWhenHiddenFileExist() throws IOException {
		File file1 = new File("./test/.HiddenFile");

		file1.createNewFile();

		this.command.run("");

		assertThat(out.toString(), is("" + System.lineSeparator()));
	}

	@Test
	public void testDisplayFilesWhenOnlyFolder() throws IOException {
		File file1 = new File("./test/file1.txt");
		File file2 = new File("./test/file2.txt");
		File dir = new File("./test/dir1");

		file1.createNewFile();
		file2.createNewFile();
		dir.mkdir();

		this.command.run("");

		assertThat(out.toString(),
				is("dir1" + SEPARATOR + "file1.txt" + SEPARATOR + "file2.txt" + System.lineSeparator()));
	}

	@Test
	public void testDisplayFilesWhenNoFile() throws IOException {

		this.command.run("");

		assertThat(out.toString(), is("" + System.lineSeparator()));

	}

	@Test
	public void testDisplayAllFilesWhenEmptyDirectory() throws IOException {

		this.command.run("-a");

		assertThat(out.toString(), is("." + SEPARATOR + ".." + System.lineSeparator()));
	}

	@Test
	public void testDisplayBothOfHiddenFilesAndNormalFilesWhenHiddenFilesAndNormalFilesExist() throws IOException {
		File file1 = new File("./test/.HiddenFile");
		File file2 = new File("./test/NormalFile");

		file1.createNewFile();
		file2.createNewFile();

		this.command.run("-a");

		assertThat(out.toString(), is("." + SEPARATOR + ".." + SEPARATOR + ".HiddenFile" + SEPARATOR + "NormalFile"
				+ System.lineSeparator()));
	}

	@Test
	public void testDisplayFileAndDirectoryWhenSpecifyChildDirectory() throws IOException {
		File sample = new File("./test/sample");
		File file1 = new File("./test/sample/file1");
		File directory1 = new File("./test/sample/directory1");

		sample.mkdir();
		file1.createNewFile();
		directory1.mkdir();

		this.command.run("sample");

		assertThat(out.toString(), is("directory1" + SEPARATOR + "file1" + System.lineSeparator()));
	}

	@Test
	public void testDisplayFileAndDirectoryWhenSpecifyChildDirectory2() throws IOException {
		File sample = new File("./test/sample2");
		File file1 = new File("./test/sample2/file1");
		File directory1 = new File("./test/sample2/directory1");

		sample.mkdir();
		file1.createNewFile();
		directory1.mkdir();

		this.command.run("sample2");

		assertThat(out.toString(), is("directory1" + SEPARATOR + "file1" + System.lineSeparator()));
	}

	@Test
	public void testDisplayFileAndDirectoryEachDirectoryWhenSpecifyChildDirectory2() throws IOException {
		File hoge = new File("./test/hoge");
		File hogeFile = new File("./test/hoge/file");
		File hogeDir = new File("./test/hoge/dir");
		hoge.mkdir();
		hogeFile.createNewFile();
		hogeDir.mkdir();

		File fuga = new File("./test/fuga");
		File fugaFile = new File("./test/fuga/file");
		File fugaDir = new File("./test/fuga/dir");
		fuga.mkdir();
		fugaFile.createNewFile();
		fugaDir.mkdir();

		this.command.run("hoge fuga");

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("hoge:" + System.lineSeparator());
		stringBuilder.append("dir" + SEPARATOR + "file" + System.lineSeparator());
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("fuga:" + System.lineSeparator());
		stringBuilder.append("dir" + SEPARATOR + "file" + System.lineSeparator());

		assertThat(out.toString(), is(stringBuilder.toString()));
	}

}
