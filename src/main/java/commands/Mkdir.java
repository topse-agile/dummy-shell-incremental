package commands;

import java.io.File;

public class Mkdir extends Command {

	private final String ERR_MSG = "mkdir: %s: File exists";

	@Override
	public void run(String params) {
		String trimmedParams = params.trim();
		if (trimmedParams.isEmpty()) {
			println("usage: mkdir [-pv] [-m mode] directory ...");
			return;
		}
		String[] split = trimmedParams.split(" ");

		boolean isRecursively = split[0].startsWith("-p");
		int startIndex = isRecursively ? 1 : 0;
		for (int i = startIndex; i < split.length; i++) {
			String dir = split[i];
			if (!new File(currentDir + "/" + dir).mkdirs()) {
				println(String.format(ERR_MSG, dir));
			}
		}
	}
}
