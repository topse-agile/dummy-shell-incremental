package commands;

public abstract class Command {
	protected final String currentDir = "./test";

	public abstract void run(String params);

	public void println(String message) {
		System.out.println(message);
	}

	public void println() {
		println("");
	}

}
