package commands;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class Touch extends Command {

	@Override
	public void run(String params) {
		if (params.equals("")) {
			System.out.println("usage:");
			return;
		}

		String[] split = params.split(" ", 2);

		for (String fileName : split) {

			File file = new File("./test/" + fileName);

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
