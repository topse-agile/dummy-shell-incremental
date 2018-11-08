import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class CommandSelectorTest {

	@Test
	public void echoメソッドが呼ばれている事の確認() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		
		CommandSelector selector = new CommandSelector();
		selector.execute("echo Hello World!");

		assertThat(out.toString(), is("Hello World!" + System.lineSeparator()));//printlnの改行部分はlineSeparatorを付ける
	}
	
	@Test
	public void 空文字のケースの確認() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		
		CommandSelector selector = new CommandSelector();
		selector.execute("");

		assertThat(out.toString(), is("" + System.lineSeparator()));//printlnの改行部分はlineSeparatorを付ける
	}
}
