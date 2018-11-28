package commands;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Ls extends Command {
	private final String SEPARATOR = "    ";

	@Override
	public void run(String params) {
		String[] split = params.split(" ", 2);
		ArrayList<String> options = extractOptions(split);
		ArrayList<String> paths = extractPaths(split);

		if (paths.size() == 0) {
			File homeDir = new File(currentDir);
			showFiles(homeDir, options);
		} else if (paths.size() == 1) {
			File dir = new File(currentDir + "/" + paths.get(0));
			showFiles(dir, options);
		} else {
			for (Iterator<String> iterator = paths.iterator(); iterator.hasNext();) {
				String path = iterator.next();
				println(path + ":");
				File dir = new File(currentDir + "/" + paths.get(0));
				showFiles(dir, options);
				if (iterator.hasNext()) {
					println();
				}
			}
		}
	}

	private void showFiles(File homeDir, ArrayList<String> options) {
		List<String> fileNames = new ArrayList<String>();
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
		println(str);
	}

	private void sort(File[] listFiles) {
		Arrays.sort(listFiles, new java.util.Comparator<File>() {
			@Override
			public int compare(File file1, File file2) {
				return file1.getName().compareTo(file2.getName());
			}
		});
	}

	private ArrayList<String> extractPaths(String[] split) {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (String param : split) {
			if (!param.startsWith("-") && new File(currentDir + "/" + param).exists()) {
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
}
