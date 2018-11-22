package commands;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ls extends Command {
	private final String SEPARATOR = "    ";

	@Override
	public void run(String params) {
		File homeDir = new File("./test");

		List<String> fileNames = new ArrayList<String>();

		String[] split = params.split(" ", 2);
		ArrayList<String> options = extractOptions(split);
		ArrayList<String> paths = extractPaths(split);

		if (paths.size() == 0) {
			if (showHiddenFile(options)) {
				fileNames.add(".");
				fileNames.add("..");
			}
			File[] listFiles = homeDir.listFiles();
			sort(listFiles);
			
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
		} else {
			for (String path : paths) {
				File dir = new File("./test/" + path);
				System.err.print(path);

				if (showHiddenFile(options)) {
					fileNames.add(".");
					fileNames.add("..");
				}
				File[] listFiles = dir.listFiles();
				sort(listFiles);
				
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
		}
	}

	private void sort(File[] listFiles) {
		Arrays.sort(listFiles, new java.util.Comparator<File>() {
			public int compare(File file1, File file2) {
				return file1.getName().compareTo(file2.getName());
			}
		});
	}

	private ArrayList<String> extractPaths(String[] split) {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (String param : split) {
			if (!param.startsWith("-") && new File("./test/" + param).exists()) {
				arrayList.add(param);
			}
		}
		return arrayList;
	}

	private ArrayList<String> extractOptions(String[] split) {
		if (split[0].startsWith("-")) {
			return new ArrayList<>(Arrays.asList(split[0]));
		} else {
			return new ArrayList<>();
		}
	}

	private boolean isHidden(File file) {
		String fileName = file.getName();
		return fileName.startsWith(".");
	}

	private boolean showHiddenFile(ArrayList<String> options) {
		return options.size() > 0 && options.get(0).equals("-a");
	}

	private boolean showDirectory(ArrayList<String> paths) {
		return paths.size() > 0 && paths.get(0).equals("sample");
	}

}
