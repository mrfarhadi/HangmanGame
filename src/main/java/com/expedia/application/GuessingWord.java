package com.expedia.application;

import java.util.Arrays;

public class GuessingWord extends SecretWord {
  private String word;
  private Boolean[] coverMark;

  public GuessingWord() {
    this.coverMark = new Boolean[super.getLength()];
    Arrays.fill(coverMark, Boolean.TRUE);

    this.word = super.getWord().replaceAll(".", "_");
  }
  
  /**
   * Look for the guessed letter in the secret word (parent class). if found, update coverMark array and return true. Otherwise, return false.
   * @param guess
   * @return boolean
   * 
   */
  public boolean findGuess(char guess){
    int index = super.getWord().toLowerCase().indexOf(guess);
    if (index == -1) {
      return false;
    }
    else {
      while (index >= 0) {
        setCoverMark(index, false);  
        index = super.getWord().indexOf(guess, index+1);
      }
      return true;
    }
  }
  
  /**
   * Check the coverMark array. unveil the letter for any element that its coverMark is false.
   * @param 
   * @return 
   * 
   */
  public void unveilLetter() {
    for (char c: super.getWord().toCharArray()) {
      if (!getCoverMark()[super.getWord().indexOf(c)]) {
        int index = super.getWord().indexOf(c);
        while (index>=0) {
          setWord(index, c);  
          index = super.getWord().indexOf(c, index+1);
        }
      }
      else {
        setWord(super.getWord().indexOf(c), '_'); 
      }
    }
  }
  
  /**
   * Check if the entire word is guessed correctly.
   * @param  guessingWord
   * @return boolean
   * 
   */
  public boolean isGussed() {
    return super.getWord().equals(getWord());
    
  }

  public Boolean[] getCoverMark() {
    return coverMark;
  }

  public void setCoverMark(int index, boolean mark) {
    coverMark[index] = mark;
  }

  @Override
  public String getWord() {
    return word;
  }

  /**
   * Update the guessingWord with the letter guessed
   * @param guessedChar
   * @param guessedCharIndex
   * @return 
   * 
   */
  public void setWord(int index, char c) {
    char[] chars = word.toCharArray();
    chars[index] = c;
    word = String.valueOf(chars);  
  }

}
