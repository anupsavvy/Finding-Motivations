package com.parsetext.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class WriteFile {
	
	private static Map<String,ArrayList<Integer>> dictionary;
	
	public static void write(String filename,Map<String,ArrayList<Integer>> dictionary) throws IOException{
		WriteFile.dictionary = dictionary;
		File file = new File(filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for(int i = 0; i < PreProcess.NUMBER_OF_MESSAGES; i++){
			writer.write(makeVector(i));
			writer.write("\n");
		}
		writer.close();
	}
	
	private static String makeVector(int messageId){
		String vector = "";
		Iterator<String> itr = WriteFile.dictionary.keySet().iterator();
		
		while(itr.hasNext()){
			String key = itr.next();
			vector += WriteFile.dictionary.get(key).get(messageId) + " ";
		}
		return vector.substring(0, vector.length()-1);
	}

}
