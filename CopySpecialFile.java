package net.ukr.geka3;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.FileSystem;

public class CopySpecialFile {
	public static void copyOnly(String extension, String from, String to) {
		File fileFrom = new File(from);
		if (!fileFrom.isDirectory()) {
			System.out.println("file 'from' is not directory process of copy was stoped");
			return;
		}
		File fileTo = new File(to);
		if (!fileTo.isDirectory()) {
			System.out.println("file 'to' is not directory process of copy was stoped");
			return;
		}

		File[] listFileFrom = fileFrom.listFiles((fileA) -> {
			if (!fileA.isFile()) {
				return false;
			}
			String nameFile = fileA.getName();
			int lastDotPosition = nameFile.lastIndexOf('.');
			if (lastDotPosition == -1) {
				System.out.println("file " + nameFile + " don't has dot in name");
				return false;
			}
			 String realExtension = nameFile.substring(lastDotPosition + 1);
			 
			 
			 if(realExtension.equals(extension)){
				  return true;
			 }else{
				return false;
			 }
			 			 
			
		});
		for (File file : listFileFrom) {
			System.out.println(file.getName() + " will be coped");
			
			copy(file,fileTo);
			
		}
	}
	
	public static void copy(File file, File to) {
		if (file == null){
			System.out.println("ref to file is null");
			throw new IllegalArgumentException("Null pointer");
		}
		if (to == null){
			System.out.println("ref to directory is null");
			throw new IllegalArgumentException("Null pointer");
		}
		
		if (!file.isFile()){
			System.out.println("there is not file to copy");
			throw new IllegalArgumentException("Null pointer");
		}
		if (!to.isDirectory()){
			System.out.println("there is not directory");
			throw new IllegalArgumentException("Null pointer");
			
		}
		
		try(FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(to.getAbsolutePath() + "\\" +  file.getName())){
			
			byte [] buffer = new byte[1024 * 1024];
			int n = 0;
			
			for(;(n = fis.read(buffer)) != -1; ){
				fos.write(buffer, 0, n);
				
			}
		}
		catch(IOException e){
			System.out.println(e);
		}
		
		
		
	}

}
