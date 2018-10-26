package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class FileJava {
	
	public static List<String> getFileConList(String path) {
		List<String> sl = new ArrayList<String>();
		try {
			File file = new File(new URI("file:/"+path));
			FileReader fr = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			while ( (line = br.readLine()) != null ) {
				sl.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sl;
	}
	
}
