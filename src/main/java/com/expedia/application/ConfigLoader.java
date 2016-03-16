package com.expedia.application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * This class gets the configs from "config.properties" file in the resources
 * and runs until the game ends showing the total scores
 *
 */
public class ConfigLoader {
  
  private final static String MAX_INCORRECT_GUESS = "max.incorrect.guess";
  private final static String MIN_NUM_WORDS = "min.words";
  private final static String WORDS = "current.words";
  
  private int maxNumOfBadGuess;
  private int minNumOfWords;
  private String words;
  
  
  public ConfigLoader() throws IOException {
    Properties props = new Properties();
    String propsFileName = "config.properties";
    InputStream inputStream = null;

    try {
      inputStream = getClass().getClassLoader().getResourceAsStream(propsFileName);

      if (inputStream != null) {
        props.load(inputStream);
      } else {
        throw new FileNotFoundException("The file " + propsFileName + " is not found.");
      }

      String tempProperty = props.getProperty(MAX_INCORRECT_GUESS);
      setMaxNumOfBadGuess(Integer.parseInt(tempProperty));
      
      tempProperty = props.getProperty(MIN_NUM_WORDS);
      setMinNumOfWords(minNumOfWords);
      
      tempProperty = props.getProperty(WORDS);
      setWords(tempProperty);

    } catch (NumberFormatException e) {
      System.out.println("Exception while getting properties due to wrong number format entered, Values will be"
            + " assigned to defaults");
      setMaxNumOfBadGuess(10);
      setWords("Expedia");
    } catch (Exception e) {
      System.out.println("Exception while loading the property file: " + e);
    } finally {
      inputStream.close();
    }
  }

   /**
    * Puts all the words in an array. Uses ',' as delimiter 
    * 
    * @param 
    * @return wordsArray
    * 
    */
  public List<String> getFileWords(String fileToString) {
    return new ArrayList<String>(Arrays.asList(fileToString.split(",")));  
  }
  public int getMaxNumOfBadGuess() {
    return maxNumOfBadGuess;
  }
  public void setMaxNumOfBadGuess(int maxNumOfBadGuess) {
    this.maxNumOfBadGuess = maxNumOfBadGuess;
  }
  public String getWords() {
    return words;
  }
  public void setWords(String words) {
    this.words = words;
  }

  public int getMinNumOfWords() {
    return minNumOfWords;
  }

  public void setMinNumOfWords(int minNumOfWords) {
    this.minNumOfWords = minNumOfWords;
  }
}
