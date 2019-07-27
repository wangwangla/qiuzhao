package io.jboot.admin.base.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import com.jfinal.upload.UploadFile;

public class UpLoadUtils {
	public static List<String> uploadFile(UploadFile file ,List<String> listImage) throws Exception {
		 String fileName = UUID.randomUUID().toString().substring(0, 5)+file.getFileName();
		 File Newfile = new File("/usr/local/nginx/html",fileName);
		 listImage.add(fileName);
		 FileInputStream inputStream = new FileInputStream(file.getFile());
		 FileOutputStream outputStream = new FileOutputStream(Newfile);
		 byte[] b = new byte[1024];
		 int length = 0 ;
		 while((length=inputStream.read(b))>0) {
			 outputStream.write(b, 0, length);
		 }
		 inputStream.close();
		 outputStream.close();
		return listImage;
	}
	
	public static void delete(String fileName) {
		 File file = new File("/usr/local/nginx/html",fileName);
		 file.delete();
	}
}
