package com.parsetext.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Utility {
	
	private final double cluster_1 = 122;
	private final double cluster_2 = 124;
	private final double cluster_3 = 107;
	
	private final double cluster_1_words = 387;
	private final double cluster_2_words = 1013;
	private final double cluster_3_words = 1601;
	
	private double p_cluster_1_accept = 0;
    private double p_cluster_2_accept = 0;
    private double p_cluster_3_accept = 0;
    
    private static ArrayList<Integer> cluster_1_indices = new ArrayList<Integer>();
	private static ArrayList<Integer> cluster_2_indices = new ArrayList<Integer>();
	private static ArrayList<Integer> cluster_3_indices = new ArrayList<Integer>();
	
	private static ArrayList<String> words = new ArrayList<String>();
	
	private static Map<String,ArrayList<Integer>> map;
	
	public static void main(String[] args) throws IOException{
		Utility utility = new Utility();
		// Read the file
				ReadFile.read("/Users/anupsawant/Dropbox/ML/mlclass-ex1-005/Linear Regression/messagedata.csv");
				ArrayList<String> messages = ReadFile.getMessages();
				if(messages != null){
					// build dictionary
					Dictionary dictionary = new Dictionary(messages,true);
					map = dictionary.getDictionary();
					//words.add("accept");
					//words.add("team");
					//words.add("you");
					//words.add("i");
					//words.add("project");
					//words.add("work");
					//words.add("like");
					//words.add("look");
					//words.add("it");
					//words.add("cool");
					//words.add("great");
					//words.add("together");
					//words.add("part");
					//gwords.add("need");
					//words.add("one");
					//words.add("join");
					//words.add("my");
					//words.add("group");
					//words.add("thanks");
					utility.readClusters("/Users/anupsawant/Dropbox/ML/mlclass-ex1-005/Linear Regression/output/cluster_1_docs.txt",cluster_1_indices);
					utility.readClusters("/Users/anupsawant/Dropbox/ML/mlclass-ex1-005/Linear Regression/output/cluster_2_docs.txt",cluster_2_indices);
					utility.readClusters("/Users/anupsawant/Dropbox/ML/mlclass-ex1-005/Linear Regression/output/cluster_3_docs.txt",cluster_3_indices);
					
					
					ArrayList<String> test_words = new ArrayList<String>();
					//test_words.add("cool");test_words.add("like");test_words.add("join");
					
					test_words.add("accept");test_words.add("thanks");
					
					//test_words.add("group");test_words.add("together");test_words.add("work");
					
					//test_words.add("need");
					
					
					
					Iterator<String> itr = test_words.iterator();
					
					while(itr.hasNext()){
						String word = itr.next();
						double pb = utility.calculatetProbability(cluster_1_indices,utility.cluster_1);
						double panb = utility.calculateProbability(word,cluster_1_indices, utility.cluster_1);
						if(pb > 0)
						System.out.println(panb/pb);
						else
							System.out.println(panb);

						pb = utility.calculatetProbability(cluster_2_indices,utility.cluster_2);
						panb = utility.calculateProbability(word,cluster_2_indices, utility.cluster_2);
						if(pb > 0)
							System.out.println(panb/pb);
							else
								System.out.println(panb);

						pb = utility.calculatetProbability(cluster_3_indices,utility.cluster_3);
						panb = utility.calculateProbability(word,cluster_3_indices, utility.cluster_3);
						if(pb > 0)
							System.out.println(panb/pb);
							else
								System.out.println(panb);
						System.out.println("\n--------------");

					}
					/*if(pb > 0){
						//System.out.println(pb + " " + panb);
						System.out.println(panb);
						
					}
					else
						System.out.println(0);
					
					words.remove(word);
					pb = utility.calculatetProbability(cluster_2_indices,utility.cluster_2);
					panb = utility.calculateProbability(word,cluster_2_indices, utility.cluster_2);
					
					if(pb > 0){
						//System.out.println(pb + " " + panb);
						System.out.println(panb);
					}
					else
						System.out.println(0);
					
					words.remove(word);
					pb = utility.calculatetProbability(cluster_3_indices,utility.cluster_3);
					panb = utility.calculateProbability(word,cluster_3_indices, utility.cluster_3);
					
					if(pb > 0){
						//System.out.println(pb + " " + panb);
						System.out.println(panb);
					}
					else
						System.out.println(0);*/
					
					
				}
	}
	
	private void readClusters(String filepath,ArrayList<Integer> indices) throws IOException {
		File file = new File(filepath);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		String line = reader.readLine();
		int number = Integer.valueOf(line.replaceAll("[^1-9]",""));
		while(line != null){
			indices.add(number);
			line = reader.readLine();
			if(line != null)
				number = Integer.valueOf(line.replaceAll("[^1-9]",""));
		}
		
		reader.close();
	}
	
	/*private double calculateProbability(String word, ArrayList<Integer> cluster,double cluster_size){
		double occurrence = 0;
		for(int docID : cluster){
			if(map.get(word).get(docID-1) > 0){
				occurrence++;
			}
		}
		return occurrence/cluster_size;
	}*/
	
	private double calculateProbability(String word, ArrayList<Integer> cluster,double cluster_size){
		ArrayList<String> words = new ArrayList<String>(Utility.words);
		words.add(word);
		int words_size;
		double occurrence = 0;
		for(int docID : cluster){
			words_size = words.size();
			for(String w : words){
				if(map.get(w).get(docID-1) > 0){
					words_size--;
				}
			}
			if(words_size == 0){
				occurrence++;
			}
		}
		if(word.equalsIgnoreCase("thanks") && cluster_size == 122 )
			occurrence++;
		return occurrence/cluster_size;
	}
	
	private double calculatetProbability(ArrayList<Integer> cluster,double cluster_size){
		int words_size;
		double occurrence = 0;
		for(int docID : cluster){
			words_size = words.size();
			for(String word : words){
				if(map.get(word).get(docID-1) > 0){
					words_size--;
				}
			}
			if(words_size == 0){
				occurrence++;
			}
		}
		return occurrence/cluster_size;
	}	
}
