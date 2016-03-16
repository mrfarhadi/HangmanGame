package com.expedia.application;

import java.util.Collections;

public class Game {

  private boolean gameOver;
  private boolean playerWon;

  private Player player;
  private SecretWord secretWord;
  private GuessingWord guessingWord;
  private HangmanSketch hangmanSketch;

  public Game(Player player, SecretWord secretWord) {
    this.setPlayer(player);
    this.setSecretWord(secretWord);
    this.guessingWord = new GuessingWord();
    this.hangmanSketch = new HangmanSketch();
  }

  /**
   * Play the Game. The secret word is compared with the user's guesses. 
   * @param player
   * 
   */
  public void play(Player player, boolean guessAWord) {
    if(guessAWord){
      compareWord(player.getGuessedWord(), secretWord.getWord());
    } 
    else { 
      compareLetter(player.getGuessedWord().charAt(0));
    }
  }

  /**
   * Gets the user guess. Compare number of bad guesses with the maximum allowed form config file
   * Checks if a game is over
   * @param player
   * @param config
   * 
   */
  public void getUserGuess(ConfigLoader config) {
    System.out.println("WORD: " + secretWord.getWord());

    while(player.getNumOfBadGuess() < config.getMaxNumOfBadGuess() && !gameOver) {
      System.out.println(guessingWord.getWord().replaceAll(".(?=.)", "$0 "));
      System.out.println("Pick a letter or guess a word (Number of bad guess: " + player.getNumOfBadGuess() + ")");
      player.pickAGuess();
      play(player, player.isGuessAWord());
      hangmanSketch.printHangman(player.getNumOfBadGuess());
      isGameOver(config);  
    }
    System.out.println("Game finished.");  
  }

  /**
   * If user's input is a letter, not a word, compare the guessed letter with the letters in the secret word
   * Check if the letter was already guessed. Give warning if it is repeated once, if more, guess is incorrect
   * @param 
   * 
   */
  public void compareLetter(char guessedLetter) {
    int guessNumOfRpt = Collections.frequency(player.getGuessedChars(), guessedLetter);        

    // first time this letter is guessed
    if (guessNumOfRpt == 0) {
      if(!guessingWord.findGuess(player.getGuessedWord().charAt(0))) {
        player.setNumOfBadGuess(player.getNumOfBadGuess()+1);
        player.setGuessedChars(guessedLetter);
      }  
      else { // it is a good guess.. unveil the letter
        guessingWord.unveilLetter();
        player.setGuessedChars(guessedLetter);
      }
    }
    // second time this letter is guessed.. give warning
    else if (guessNumOfRpt == 1) {
      System.out.println("Warning: This letter was already picked");
      player.setGuessedChars(guessedLetter);
    }
    else {
      System.out.println("Your guess has been repetaed more than twice");
      player.setNumOfBadGuess(player.getNumOfBadGuess()+1);
      player.setGuessedChars(guessedLetter);
    }
  }

  /**
   * If user's input is a word, not a letter, compare the guessed word with the entire secret word word
   * Check if the letter was already guessed. Give warning if it is repeated once, if more, guess is incorrect
   * @param guessingWord
   * @param secretWord
   * @return boolean
   * 
   */
  public boolean compareWord(String guessWord, String secretWord) {
    if (guessWord.equals(secretWord)) {
      System.out.println("Congrats! You won");
      setPlayerWon(true);
      setGameOver(true);
      return true;
    }
    else {
      player.setNumOfBadGuess(player.getNumOfBadGuess()+1);
      return false;
    }
  }

  /**
   * Check if the game is over. Game will be over if the word is guessed or number of bad guesses exceeds the max 
   * allowed bad guesses (10).
   * @param 
   * @return boolean
   * 
   */
  public boolean isGameOver(ConfigLoader config) {
    if (guessingWord.isGussed()){
      setPlayerWon(true);
      setGameOver(true);
      System.out.println("Congrats! You won");
    }
    if (player.getNumOfBadGuess() >= config.getMaxNumOfBadGuess()) {
      setPlayerWon(false);
      setGameOver(true);
      System.out.println("Sorry! You Lost. Number of bad guess: " + player.getNumOfBadGuess());
    }
    return gameOver;
  }

  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public SecretWord getSecretWord() {
    return secretWord;
  }

  public void setSecretWord(SecretWord secretWord) {
    this.secretWord = secretWord;
  }

  public boolean isPlayerWon() {
    return playerWon;
  }

  public void setPlayerWon(boolean playerWon) {
    this.playerWon = playerWon;
  }
}
