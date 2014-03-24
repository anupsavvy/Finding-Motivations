package com.parsetext.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PostProcess {
	
	private List<Integer> index = new ArrayList<Integer>();
	
	private Map<Integer,List<WordCount>> topicMap = new HashMap<Integer,List<WordCount>>();
	
	public void findTopWords(Map<String,ArrayList<Integer>> dictionary) throws IOException{
		readIndex();
		int docID = 0;
		for(int i : index){ // cluster index - for all documents
			// check to see if its a new cluster. If not, create a entry for new cluster
			if(!topicMap.containsKey(i)){
				topicMap.put(i, new ArrayList<WordCount>());
			}
			
			// get all words for current docID
			
			Iterator<String> keys = dictionary.keySet().iterator();
			while(keys.hasNext()){ // for all words of one document - top to bottom of dictionary.
				String word = keys.next();
				int count = dictionary.get(word).get(docID);
				List<WordCount> wordList = topicMap.get(i);
				if(count > 0){
					WordCount wordCount = new WordCount(word,count);
					if(!wordList.contains(wordCount)){
						wordList.add(wordCount);
					}else{
						int ind = wordList.indexOf(wordCount);
						wordCount.setCount(wordList.get(ind).getCount() + wordCount.getCount());
						wordList.set(ind, wordCount);
					}
					topicMap.put(i, wordList);
				}
			}
			docID++;
		}
		
		for(int i = 1; i < 4; i++){
			List<WordCount> wc = topicMap.get(i);

			Collections.sort(wc,new WordCountComparator());
			int totalWords = 0;
			topicMap.put(1, wc);
			for(WordCount w : wc){
				System.out.print(w.getWord() +"(" + w.getCount() + ") ");
                totalWords += w.getCount();
			}
			System.out.println("\n "+ totalWords);
			System.out.println("\n-----------------------");

		}
		
	}
	
	private void readIndex() throws IOException{
		File file = new File("/Users/anupsawant/Dropbox/ML/mlclass-ex1-005/Linear Regression/output/clusters.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		String line = reader.readLine();
		int number = Integer.valueOf(line.replaceAll("[^1-9]",""));
		while(line != null){
			index.add(number);
			line = reader.readLine();
			if(line != null)
				number = Integer.valueOf(line.replaceAll("[^1-9]",""));
		}
		
		reader.close();
	}
	
	private class WordCountComparator implements Comparator<WordCount> {
		public int compare(WordCount wc1,WordCount wc2){
			
			if(wc1.getCount() > wc2.getCount()){
				return -1;
			}else if(wc1.getCount() < wc2.getCount()){
				return 1;
			}else{
				return 0;
			}
		}
	}

}
