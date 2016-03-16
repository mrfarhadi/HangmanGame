package com.expedia.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.expedia.application.Game;
import com.expedia.application.Player;
import com.expedia.application.SecretWord;
import com.expedia.application.ConfigLoader;

/**
 * This class starts the hangman game
 * and runs until the game is over 
 *
 */
public class AppStart {
  public static void main(String[] args) throws IOException {
    ConfigLoader configLoader = new ConfigLoader();
    Player player = new Player();

    String secWords = configLoader.getWords();
    List<String> secWordsList = configLoader.getFileWords(secWords);

    if (secWordsList.size() >= configLoader.getMinNumOfWords()) {
      Random randomizer = new Random();
      String randomWord = secWordsList.get(randomizer.nextInt(secWordsList.size()));

      SecretWord secWord = new SecretWord(randomWord);

      Game hangGame = new Game(player, secWord);
      hangGame.getUserGuess(configLoader);
    }
    else {
      System.out.println("Not enough words in the resource file. Current num of words: " + secWordsList.size());
    }
    
    
    
  }
}
