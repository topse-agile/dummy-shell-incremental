import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class DummyShellTest {
	
    @Test public void testEcho() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		
		DummySchell.echo("Hello World!");

		assertThat(out.toString(), is("Hello World!" + System.lineSeparator()));//printlnの改行部分はlineSeparatorを付ける
    }

    @Test public void testEchoWithDoubleQuote() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		
		DummySchell.echo("\"Hello World!\"");

		assertThat(out.toString(), is("Hello World!" + System.lineSeparator()));//printlnの改行部分はlineSeparatorを付ける
    }

    @Test public void testEchoWithSingleQuote() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		
		DummySchell.echo("'Hello World!'");

		assertThat(out.toString(), is("Hello World!" + System.lineSeparator()));//printlnの改行部分はlineSeparatorを付ける
    }

}
