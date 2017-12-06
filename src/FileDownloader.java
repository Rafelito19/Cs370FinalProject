


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
 
/// This file contains a public class that simplifies the download of files to one single call. See link bellow for more info
////Code can be found at https://gist.github.com/madan712/8687784
// Class created by Madan Chaudhary.
/// this code downloads any file from the Internet provided the url and file name


public class FileDownloader {
public static void Download(URL url,String filena) {
		File file = new File (filena);
		try {
			InputStream input = url.openStream();
			if (file.exists()) {
				if (file.isDirectory())
					throw new IOException("File '" + file + "' is a directory");
				
				if (!file.canWrite())
					throw new IOException("File '" + file + "' cannot be written");
			} else {
				File parent = file.getParentFile();
				if ((parent != null) && (!parent.exists()) && (!parent.mkdirs())) {
					throw new IOException("File '" + file + "' could not be created");
				}
			}

			FileOutputStream output = new FileOutputStream(file);

			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
			}

			input.close();
			output.close();
			
			System.out.println("File '" + file + "' downloaded successfully!");
		}
		catch(IOException ioEx) {
			ioEx.printStackTrace();
		}
	}
}


/*	public static void main(String[] args) throws IOException {
		
		//URL pointing to the file
		String sUrl = "http://localhost:8080/TestFile/file.zip";
		
		URL url = new URL(sUrl);
		
		//File where to be downloaded
		File file = new File("C:/Temp/file.zip");
		
		URLReader.copyURLToFile(url, file);
	}

}*/