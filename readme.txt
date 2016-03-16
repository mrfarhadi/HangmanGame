Hangman Game 

This application simulates hangman game. It can be run from command line. Once you run the program, it gives you the secret word that needs to be guessed. The reason the secret word is given, is to allow the user to test the program easily.
The application has a "StartApp" class which runs the program. 

It has an application.properties file which has all properties needed for this app as well as 50 words. The program randomly picks one word from this list as the secret word. 
“ConfigLoader” class loads the properties for the game.

“Player” class has all the attributes for a player such as guess word, number of bad guesses, pick a word method and etc. 

“SecretWord” class is a container for the secret word to be guessed.

“GuessingWord” class is a child of “SecretWord” class and inherits its length. This class is a container for a word being guessed. For example, “farhadi” is the secretWord, but “fa__ad_” is a guessingWord.

HangmanSketch class had the ascii code for the hangman. It has 11 different shapes in asscii. 

“Game” class plays the game and shows the hangmanSketch status to the user. It has play, getUserGuess, compare word and letter and etc functions. 

The application has 9 unit tests to test the functionality of the Game methods. 
