package com.expedia.application;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class GameTest {
  
  private ConfigLoader config;
  private Game game;
  private Player player;
  private SecretWord secWord;
  private GuessingWord guessingWord;
  
  @Before
  public void setup() throws IOException {
    config = new ConfigLoader();
    config.setMaxNumOfBadGuess(10);
    config.setMinNumOfWords(2);
    config.setWords("expedia,hotwire");
    player = new Player();
    
  }
  
  @Test
  public void play_correct_guess_word() {
    secWord = new SecretWord("expedia");
    guessingWord = new GuessingWord();
    game = new Game(player, secWord);
    
    player.setGuessedWord("expedia");
    game.play(player, true);
    assertTrue(game.isPlayerWon());
  }
  @Test
  public void play_wrong_guess_word() {
    secWord = new SecretWord("expedia");
    guessingWord = new GuessingWord();
    game = new Game(player, secWord);
    
    player.setGuessedWord("eaaapia");
    game.play(player, true);
    assertTrue(!game.isPlayerWon());
  }
  
  @Test
  public void play_correct_guess_letters() {
    secWord = new SecretWord("expedia");
    guessingWord = new GuessingWord();
    game = new Game(player, secWord);
    
    player.setGuessedWord("e");
    game.play(player, false);
    
    player.setGuessedWord("x");
    game.play(player, false);
    
    player.setGuessedWord("p");
    game.play(player, false);
    
    player.setGuessedWord("d");
    game.play(player, false);
    
    player.setGuessedWord("i");
    game.play(player, false);
    
    player.setGuessedWord("a");
    game.play(player, false);
    
    game.isGameOver(config);
    assertTrue(game.isPlayerWon());
  }
  
  @Test
  public void play_wrong_guess_letters() {
    secWord = new SecretWord("expedia");
    guessingWord = new GuessingWord();
    game = new Game(player, secWord);
    
    player.setGuessedWord("k");
    game.play(player, false);
    
    player.setGuessedWord("x");
    game.play(player, false);
    
    player.setGuessedWord("M");
    game.play(player, false);
    
    player.setGuessedWord("d");
    game.play(player, false);
    
    player.setGuessedWord("i");
    game.play(player, false);
    
    player.setGuessedWord("a");
    game.play(player, false);
    
    game.isGameOver(config);
    assertTrue(!game.isPlayerWon());
  }
  
  @Test
  public void play_correct_guess_letters_words() {
    secWord = new SecretWord("expedia");
    guessingWord = new GuessingWord();
    game = new Game(player, secWord);
    
    player.setGuessedWord("k");
    game.play(player, false);
    
    player.setGuessedWord("x");
    game.play(player, false);
    
    player.setGuessedWord("M");
    game.play(player, false);
    
    player.setGuessedWord("a");
    game.play(player, false);
    
    player.setGuessedWord("i");
    game.play(player, false);
    
    player.setGuessedWord("expedia");
    game.play(player, true);
    
    game.isGameOver(config);
    assertTrue(game.isPlayerWon());
  }
  
  @Test
  public void play_exceed_badguess() {
    secWord = new SecretWord("expedia");
    guessingWord = new GuessingWord();
    game = new Game(player, secWord);
    
    player.setGuessedWord("k");  //1
    game.play(player, false);
    
    player.setGuessedWord("x");
    game.play(player, false);
    
    player.setGuessedWord("M"); //2
    game.play(player, false);
    
    player.setGuessedWord("a");
    game.play(player, false);
    
    player.setGuessedWord("i");
    game.play(player, false);
    
    player.setGuessedWord("q"); //3
    game.play(player, false);
    
    player.setGuessedWord("q"); //3
    game.play(player, false);
    
    player.setGuessedWord("q"); //4
    game.play(player, false);
    
    player.setGuessedWord("h"); //5
    game.play(player, false);
    
    player.setGuessedWord("u"); //6
    game.play(player, false);
    
    player.setGuessedWord("t"); //7
    game.play(player, false);
    
    player.setGuessedWord("r"); //8
    game.play(player, false);
    
    player.setGuessedWord("w"); //9
    game.play(player, false);
        
    player.setGuessedWord("v"); //11
    game.play(player, false);
    
    game.isGameOver(config);
    assertTrue(!game.isPlayerWon());
  }
  
  @Test
  public void play_ignore_warning() {
    secWord = new SecretWord("expedia");
    guessingWord = new GuessingWord();
    game = new Game(player, secWord);
    
    player.setGuessedWord("e");  // 0 error
    game.play(player, false);
    
    player.setGuessedWord("e");  // 0 error
    game.play(player, false);
    
    player.setGuessedWord("x");  // 0 error
    game.play(player, false);
    
    player.setGuessedWord("x");  // 0 error 
    game.play(player, false);
    
    player.setGuessedWord("p");  // 0 error
    game.play(player, false);
    
    player.setGuessedWord("v");  // 1 error
    game.play(player, false);
    
    player.setGuessedWord("w");  // 2 error
    game.play(player, false);
    
    player.setGuessedWord("w");  // 2 error
    game.play(player, false);
    
    player.setGuessedWord("h");  // 3 error
    game.play(player, false);
    
    player.setGuessedWord("t");  // 4 error
    game.play(player, false);
    
    player.setGuessedWord("u");  // 5 error
    game.play(player, false);
    
    player.setGuessedWord("l");  // 6 error
    game.play(player, false);
    
    player.setGuessedWord("w");  // 6 error
    game.play(player, false);
        
    player.setGuessedWord("v");  // 6 error
    game.play(player, false);
    
    player.setGuessedWord("d");  // 6 error
    game.play(player, false);
    
    player.setGuessedWord("i");  // 6 error
    game.play(player, false);
    
    player.setGuessedWord("a");  // 6 error
    game.play(player, false);
    
    game.isGameOver(config);
    assertTrue(game.isPlayerWon());
  }
  
  @Test
  public void play_correctguess_gameover() {
    secWord = new SecretWord("expedia");
    guessingWord = new GuessingWord();
    game = new Game(player, secWord);
    player.setGuessedWord("expedia");    
    game.play(player, true);

    assertTrue(game.isGameOver(config));
  }
  
  @Test
  public void play_compare_Word() {
    secWord = new SecretWord("expedia");
    guessingWord = new GuessingWord();
    game = new Game(player, secWord);
    player.setGuessedWord("expedia"); 
    game.play(player, true);
    
    game.compareWord(secWord.getWord(), guessingWord.getWord());
    assertTrue(game.isPlayerWon());
  }
}
