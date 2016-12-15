package uniandes.unacloud.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

//TODO Documentation
public class FileCloudUtils {
	
	public static File createZipArchive(String directoryPath, String fileName){
		if(fileName.contains("."))fileName = fileName.split(".")[0];
		File directory = new File(directoryPath);
		File zipFile = new File(directoryPath+File.separator+fileName+".zip");
		try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile))) {

			for (File file : directory.listFiles()) {
				if (!file.isDirectory()&&!file.getName().contains(zipFile.getName())) {
					FileInputStream in = new FileInputStream(file);
					out.putNextEntry(new ZipEntry(file.getName()));
					byte[] b = new byte[1024];
					int count;

					while ((count = in.read(b)) > 0) {
						out.write(b, 0, count);
					}					
					in.close();
				}
			}
	       return zipFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static File createZipArchive(String fileName){
		File file = new File(fileName);
		File zipFile = new File(file.getParent()+File.pathSeparator+fileName.split(".")[0]+".zip");
		try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile))) {
			if (!file.getName().contains(zipFile.getName())) {
				FileInputStream in = new FileInputStream(file);
				out.putNextEntry(new ZipEntry(file.getName()));
				byte[] b = new byte[1024];
				int count;
	
				while ((count = in.read(b)) > 0) {
					out.write(b, 0, count);
				}
				in.close();
	
				return zipFile;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
