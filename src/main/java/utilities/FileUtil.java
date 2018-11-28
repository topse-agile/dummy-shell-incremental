package utilities;

import java.io.File;

public class FileUtil {

	public static void deleteFolder(File dir) {
		File[] paths = dir.listFiles();
		if (paths == null)
			return;
		for (File path : paths) {
			if (!path.exists())
				continue;
			else if (path.isDirectory()) {
				deleteFolder(path);
				path.delete();
			} else if (path.isFile())
				path.delete();
		}
	}
}
