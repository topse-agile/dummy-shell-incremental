package commands;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class EchoTest {

	private Command command;

	@Before
	public void setUp() {
		this.command = new Echo();
	}

	@Test
	public void testEcho() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		this.command.run("Hello World!");

		assertThat(out.toString(), is("Hello World!" + System.lineSeparator()));// printlnの改行部分はlineSeparatorを付ける
	}

	@Test
	public void testEchoWithDoubleQuote() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		this.command.run("\"Hello World!\"");

		assertThat(out.toString(), is("Hello World!" + System.lineSeparator()));// printlnの改行部分はlineSeparatorを付ける
	}

	@Test
	public void testEchoWithSingleQuote() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		this.command.run("'Hello World!'");

		assertThat(out.toString(), is("Hello World!" + System.lineSeparator()));// printlnの改行部分はlineSeparatorを付ける
	}

}
