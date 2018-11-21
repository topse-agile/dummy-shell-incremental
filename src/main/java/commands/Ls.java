package commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ls extends Command {

	@Override
	public void run(String params) {
		File homeDir = new File("./test");
		File[] listFiles = homeDir.listFiles();
		List<String> fileNames = new ArrayList<String>();
		for (File file : listFiles) {
			fileNames.add(file.getName());
		}
		String str = String.join("    ", fileNames);
		System.out.println(str);
	}

}
