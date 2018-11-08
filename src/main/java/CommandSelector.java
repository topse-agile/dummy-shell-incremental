
public class CommandSelector {

	public static void execute(String input) {
		if (input.isEmpty()) {
			System.out.println();
			return;
		}

		String[] split = input.split(" ", 2);

		String command = split[0];
		String message = split[1];

		switch (command) {
		case "echo":
			DummySchell.echo(message);
			break;
		default:
			throw new RuntimeException();
		}
	}

}
