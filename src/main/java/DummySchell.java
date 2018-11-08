import java.io.File;
import java.io.IOException;
import java.util.Calendar;

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

}
