import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DummySchell {

	public static void echo(String string) {
		String newString = string;
		if (string.charAt(0) == '\"' && string.charAt(string.length() - 1) == '\"') {
			newString = string.substring(1, string.length() - 1);
		}
		if (string.charAt(0) == '\'' && string.charAt(string.length() - 1) == '\'') {
			newString = string.substring(1, string.length() - 1);
		}

		System.out.println(newString);
	}

	public static void touch(String params) {

		if (params.equals("")) {
			System.out.println("usage:");
			return;
		}

		String[] split = params.split(" ", 2);

		for (String fileName : split) {

			File file = new File(fileName);

			if (file.exists()) {
				file.setLastModified(Calendar.getInstance().getTime().getTime());
				continue;
			}

			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("ファイルの作成でエラーが発生しました。 エラー内容 : " + e.getMessage());
			}
		}
	}

	public static void ls(String params) {
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
