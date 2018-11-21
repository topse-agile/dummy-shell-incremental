package commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ls extends Command {
	private final String SEPARATOR = "    ";

	@Override
	public void run(String params) {
		File homeDir = new File("./test");

		List<String> fileNames = new ArrayList<String>();

		String[] split = params.split(" ", 2);
		String[] options = extractOptions(split);
		String[] paths = extractPaths(split);

		if (showHiddenFile(options)) {
			fileNames.add(".");
			fileNames.add("..");
		}
		for (String path : paths) {
			File file = new File(path);

			File[] listFiles = homeDir.listFiles();

			for (File file : listFiles) {
				if (showHiddenFile(options)) {
					fileNames.add(file.getName());
				} else {
					if (!isHidden(file)) {
						fileNames.add(file.getName());
					}
				}
			}

		}
//		if (showDirectory(split)) {
//			System.out.println("directory1" + SEPARATOR + "file1");
//			return;
//		}

		for (File file : listFiles) {
			if (showHiddenFile(options)) {
				fileNames.add(file.getName());
			} else {
				if (!isHidden(file)) {
					fileNames.add(file.getName());
				}
			}
		}
		String str = String.join(SEPARATOR, fileNames);

		System.out.println(str);
	}

	private String[] extractPaths(String[] split) {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (String param : split) {
			if (!param.startsWith("-")) {
				arrayList.add(param);
			}
		}
		return arrayList.toArray(new String[0]);
	}

	private String[] extractOptions(String[] split) {
		if (split[0].startsWith("-")) {
			String[] tmp = new String[1];
			tmp[0] = split[0];
			return tmp;
		} else {
			return null;
		}
	}

	private boolean isHidden(File file) {
		String fileName = file.getName();
		return fileName.startsWith(".");
	}

	private boolean showHiddenFile(String[] split) {
		return split != null && split[0].equals("-a");
	}

	private boolean showDirectory(String[] split) {
		return split[0].equals("sample");
	}

}
