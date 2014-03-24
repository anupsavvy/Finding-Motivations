package com.parsetext.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Dictionary {
	private static Map<String,ArrayList<Integer>> dictionary;
	
    private ArrayList<String> stopwords = new ArrayList<String>();
    
    private boolean dic_type;
	
	public Dictionary (ArrayList<String> messages,boolean type) throws IOException{
		readStopWords();
		this.dic_type = type;
		dictionary = new TreeMap<String,ArrayList<Integer>>();
		buildDicitionary(messages);
	}
	
	
	public void buildDicitionary(ArrayList<String> messages){
		Iterator<String> itr = messages.iterator();
		int messageNumber = 0;
		int number_of_Messages = messages.size();
		Lemmatizer lemmatizer = new Lemmatizer();
		while(itr.hasNext()){
			String curr_message = itr.next();
			curr_message = curr_message.toLowerCase();
			List<String> parsedMessage = new ArrayList<String>();
			
			if(!this.dic_type){

				// changing to lower case and splitting at space
				List<String> message =  Arrays.asList(curr_message.toLowerCase().split("\\s+"));

				Iterator<String> mitr = message.iterator();
				while(mitr.hasNext()){
					parsedMessage.add(mitr.next().replaceAll("[^a-zA-Z]", ""));
				}

			}else{
				parsedMessage = lemmatizer.lemmatize(curr_message);
				
				for(int i = 0; i < parsedMessage.size();i++){
					parsedMessage.set(i, parsedMessage.get(i).replaceAll("[^a-zA-Z]", ""));
				}
				
			}
			
			// removing stopwords
			parsedMessage.removeAll(stopwords);
			
			// adding words to dictionary
			Iterator<String> mitr = parsedMessage.iterator();
			while(mitr.hasNext()){
				String word = mitr.next();
				if(!dictionary.containsKey(word)){
					ArrayList<Integer> list = new ArrayList<Integer>();
					for(int i = 0; i < number_of_Messages; i++){
						list.add(0);
					}
					list.set(messageNumber,0);
					dictionary.put(word, list);
				}
				ArrayList<Integer> list = dictionary.get(word);
				list.set(messageNumber,list.get(messageNumber) + 1);
				dictionary.put(word, list);
			}
			messageNumber++;
		}
	}
	
	private void readStopWords() throws IOException{
		File file = new File("/Users/anupsawant/Dropbox/ML/mlclass-ex1-005/Linear Regression/stopwords.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		String word = reader.readLine();
		while(word != null){
			stopwords.add(word);
			word = reader.readLine();
		}
		
		reader.close();
	}
	
	public Map<String,ArrayList<Integer>> getDictionary(){
		return new TreeMap<String,ArrayList<Integer>>(dictionary);
	}
	
}
