import commands.Command;
import commands.Echo;
import commands.Ls;
import commands.Touch;

public class CommandSelector {

	public static void execute(String input) {
		if (input.isEmpty()) {
			System.out.println();
			return;
		}

		String[] split = input.split(" ", 2);

		String commandName = split[0];
		String message = "";
		if (split.length == 2) {
			message = split[1];
		}

		Command command = null;
		switch (commandName) {
		case "echo":
			command = new Echo();
			command.run(message);
			break;
		case "touch":
			command = new Touch();
			command.run(message);
			break;
		case "ls":
			command = new Ls();
			command.run(message);
			break;
		default:
			throw new RuntimeException();
		}
	}

}
