package com.parsetext.model;

public class WordCount {
	
	private String word;
	private int count;
	
	public WordCount(String word, int count){
		setWord(word);
		setCount(count);
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj!=null && obj instanceof WordCount){
			WordCount other = (WordCount)obj;
			if(this.getWord().equalsIgnoreCase(other.getWord())){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

}
