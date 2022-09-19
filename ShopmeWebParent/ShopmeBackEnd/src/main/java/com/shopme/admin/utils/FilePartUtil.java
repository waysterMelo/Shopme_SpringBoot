package com.shopme.admin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FilePartUtil {
	
	public static void saveFile(String dir, String name, MultipartFile multipartFile) throws IOException{
		
		Path path = Paths.get(dir);
		
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}
		try ( InputStream input =  multipartFile.getInputStream()){
			Path filePath = path.resolve(name);
			Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO: handle exception
			throw new IOException("Could not save file " + name, e);
		}
	}
	
	
	public static void cleanDir(String dir) {
			Path path = Paths.get(dir);
			
			try {
				Files.list(path).forEach(file -> {
					
					if (!Files.isDirectory(file)) {
						 try {
							 Files.delete(file);
						 }catch (IOException e) {
							System.out.println("Could not delete file: " + file );
						}
					}
				});
			}catch (IOException ex) {
				// TODO: handle exception
				System.out.println("Could not list directory:" + path);
			}
			
	}

}
