package commands;

public class Echo extends Command {

	@Override
	public void run(String string) {
		String newString = string;
		if (string.charAt(0) == '\"' && string.charAt(string.length() - 1) == '\"') {
			newString = string.substring(1, string.length() - 1);
		}
		if (string.charAt(0) == '\'' && string.charAt(string.length() - 1) == '\'') {
			newString = string.substring(1, string.length() - 1);
		}

		System.out.println(newString);
	}

}
