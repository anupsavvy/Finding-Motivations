package com.parsetext.model;

import java.io.IOException;
import java.util.ArrayList;

public class PreProcess {
	
	public static int NUMBER_OF_MESSAGES;
	
	public static void main(String[] args) throws IOException{
		// Read the file
		ReadFile.read(args[0]);
		ArrayList<String> messages = ReadFile.getMessages();
		NUMBER_OF_MESSAGES = messages.size();
		if(messages != null){
			// build dictionary
			Dictionary dictionary = new Dictionary(messages,true);
			// print message vectors
			WriteFile.write("/Users/anupsawant/Dropbox/ML/mlclass-ex1-005/Linear Regression/message_vectors.txt",
					dictionary.getDictionary());
			PostProcess postProcess = new PostProcess();
			postProcess.findTopWords(dictionary.getDictionary());
		}

	}
	
}
