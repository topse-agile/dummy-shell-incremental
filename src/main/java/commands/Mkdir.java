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

		if (split[0].startsWith("-p")) {
			for (String dir : split) {
				if (!new File(currentDir + "/" + dir).mkdirs()) {
					println(String.format(ERR_MSG, dir));
				}
			}
		} else {
			for (String dir : split) {
				if (!new File(currentDir + "/" + dir).mkdir()) {
					println(String.format(ERR_MSG, dir));
				}
			}
		}
	}
}
