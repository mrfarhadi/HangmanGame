package com.expedia.application;

public class SecretWord {

  private static String word;
  private static int length;
  
  public SecretWord() {
	    SecretWord.length = getLength();
	  }

  public SecretWord(String word) {
    SecretWord.word =word;
    setLength();
  }
  
  public String getWord() {
    return word;
  }
  public void setWord(String word) {
    SecretWord.word = word;
  }

  public static int getLength() {
    return length;
  }

  public void setLength() {
    length = word.length();
  }

}
