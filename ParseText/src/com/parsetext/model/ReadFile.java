package com.parsetext.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
	private static ArrayList<String> messages = new ArrayList<String>();
	
	public static void read(String filename) throws IOException{
		File file = new File(filename);
		BufferedReader fileReader = new BufferedReader(new FileReader(file));
		
		String message = fileReader.readLine();
		
		while(message != null){
			messages.add(message);
			message = fileReader.readLine();
		}
		
		fileReader.close();
	}
	
	public static ArrayList<String> getMessages(){
	    return new ArrayList<String>(messages);
	}
}
