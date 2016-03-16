package com.expedia.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {

  private int maxNumOfBadGuess;
  private int numOfBadGuess;
  private String guessedWord;
  private boolean guessAWord;
  private boolean guessInvalid;
  private List<Character> guessedChars;

  public Player() {
    this.guessedChars = new ArrayList<Character>();
    this.numOfBadGuess = 0;
    this.guessAWord = false;
    this.guessInvalid = false;
  }
  /**
   * Pick a guess. If it is a word, update guessAWord to true, otherwise, update it with false
   * @param 
   * @throw 
   * @return 
   * 
   */
  public String pickAGuess() {
    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);
    try {
      System.out.println();
      String guess = input.nextLine().toLowerCase();
      if (!guess.isEmpty()) {
        setGuessedWord(guess);
        if (guess.length() > 1 ) {
          // it is a word
          setGuessAWord(true);
        }
        else if (guess.length() == 1) {
          // it is a char 
          setGuessAWord(false);
        }
      }
      else {
        setGuessedWord(" ");
      }
    }catch (Exception e) {
      System.err.println("Please enter a word or letter" + e.getLocalizedMessage());
    }

    return getGuessedWord();
  }

  public int getMaxNumOfBadGuess() {
    return maxNumOfBadGuess;
  }

  public void setMaxNumOfBadGuess(int maxNumOfBadGuess) {
    this.maxNumOfBadGuess = maxNumOfBadGuess;
  }

  public int getNumOfBadGuess() {
    return numOfBadGuess;
  }

  public void setNumOfBadGuess(int numOfBadGuess) {
    this.numOfBadGuess = numOfBadGuess;
  }

  public String getGuessedWord() {
    return guessedWord;
  }

  public void setGuessedWord(String guessedWord) {
    this.guessedWord = guessedWord;
  }

  public boolean isGuessAWord() {
    return guessAWord;
  }

  public void setGuessAWord(boolean guessAWord) {
    this.guessAWord = guessAWord;
  }

  public List<Character> getGuessedChars() {
    return guessedChars;
  }

  public void setGuessedChars(char guessedChar) {
    this.guessedChars.add(this.guessedChars.size(), guessedChar);
  }

  public boolean isGuessInvalid() {
    return guessInvalid;
  }

  public void setGuessInvalid(boolean guessInvalid) {
    this.guessInvalid = guessInvalid;
  }
}
