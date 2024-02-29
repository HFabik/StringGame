/*Hannah Fabik
ICS 3U7 Mrs.Strelkovska
April 1 2019
String Game
*/

import java.util.*;
import java.io.*;

public class StringGame
{
    private static String[] fruits = new String[69];
    private static String[] vegetables = new String[69];
    private static String[] colours = new String[69];
    private static Scanner in = new Scanner(System.in);
    private static String[] dictionary = null;

    private static void instOpen(String game)
    {
        String start = "n";
        String fileName = "Instructions" + game + ".txt", line;
        try
        {
            Scanner scFile = new Scanner(new File(fileName));
            while (scFile.hasNext())
            {
                line = scFile.nextLine();
                System.out.println(line);
            }
        } catch (Exception e)
        {
            System.out.println("Unfortunately your file was not found. Sorry for the inconvenience.");
        }
        while (!(start.equalsIgnoreCase("y") || start.equalsIgnoreCase("yes")))
        {
            System.out.println("Ready?");
            start = in.next();
            in.nextLine();
        }
    }

    private static void dictionaryOpen()
    {
        String fileName;
        StringBuilder line = new StringBuilder();
        for (char c = 'A'; c < 91; c++)
        {
            for (int i = 1; i < 3; i++)
            {
                fileName = ".\\Dictionary\\Dictionary-" + c + i + ".txt";
                try
                {
                    Scanner scFile = new Scanner(new File(fileName));
                    while (scFile.hasNext())
                    {
                        line.append(scFile.nextLine());
                    }
                } catch (Exception e)
                {
                    System.out.println("Sorry, the file is not available.");
                }
            }
        }
        dictionary = line.toString().split(", ");
    }

    private static HashSet<String> chooseC()
    {
        String[] words;
        while (true)
        {
            System.out.println("Choose your category: Colours, Fruits, or Vegetables ");
            String category = in.next().trim().toLowerCase();
            in.nextLine();

            switch (category)
            {
                case "colours":
                    words = colours;
                    break;
                case "fruits":
                    words = fruits;
                    break;
                case "vegetables":
                    words = vegetables;
                    break;
                default:
                    System.out.println("That is not a valid option.");
                    continue;
            }
            break;
        }
        HashSet<String> set = new HashSet<>();
        for (String word : words)
        {
            set.add(word.toLowerCase());
        }
        return (set);
    }

    private static void initialize()
    {
        String line;
        try
        {
            Scanner scFile = new Scanner(new File("vegetables.txt"));
            line = scFile.nextLine();
            vegetables = line.split(", ");
            Scanner sc = new Scanner(new File("fruits.txt"));
            line = sc.nextLine();
            fruits = line.split(", ");
            Scanner scan = new Scanner(new File("colours.txt"));
            line = scan.nextLine();
            colours = line.split(", ");
        } catch (Exception e)
        {
            System.out.println("Sorry, this file was not found.");
        }
    }

    private static char nextLetter(char letter)
    {
        if (letter != 'z')
        {
            letter = (char) (letter + 1);
        } else
            letter = 'a';
        return (letter);
    }

    private static void concentration()
    {
        String word ;
        int wordNum;
        HashSet<String> remainingSet = chooseC();
        HashSet<String> usedWords = new HashSet<>();
        System.out.println("I'll start.");
        //game starts
        while (!remainingSet.isEmpty())
        {
            //Computer's turn
            wordNum = (int) (Math.random() * remainingSet.size());
            word = remainingSet.toArray(new String[0])[wordNum];
            remainingSet.remove(word);
            usedWords.add(word);
            System.out.println(word);
            if (remainingSet.isEmpty())
                break;
            //User's turn
            //time limit
            int millisPassed = 0;
            try
            {
                while (System.in.available() == 0 && millisPassed <= 15000)
                {
                    Thread.sleep(100);
                    millisPassed += 100;
                }
            } catch (Exception ignore)
            {
            }
            if (millisPassed > 15000)
            {
                System.out.println("Sorry your time is up.");
                return;
            }
            word = in.nextLine().toLowerCase();
            if (usedWords.contains(word))
            {
                System.out.println("No repeats!");
                return;
            }
            if (!remainingSet.contains(word))
            {
                System.out.println("Sorry, that word is not in my dictionary.");
                return;
            }
            remainingSet.remove(word);
            usedWords.add(word);
            System.out.println("There are " + remainingSet.size() + " words left.");
        }
        System.out.println("There are no more words left. Good game!");
    }

    private static String getNextWord (char letter, HashSet<String> remainingWords)
    {
        String word = null;
        while (word == null)
        {
            for (String word1 : remainingWords)
            {
                if (word1.charAt(0) == letter)
                {
                    word = word1;
                    break;
                }
            }
            if (word == null)
            {
                letter = nextLetter(letter);
            }
        }
        return word;
    }

    private static void categories()
    {
        int mode;
        char letter = 'a';
        String word;
        HashSet<String> remainingWords = chooseC();
        //choose mode
        while (true)
        {
            System.out.println("Choose your mode: (Mode 1 = 1, Mode 2 = 2)");
            if (in.hasNextInt())
            {
                mode = in.nextInt();
                in.nextLine();
                if (mode == 1 || mode == 2)
                    break;
            }
        }
        System.out.println("I'll start.");
        //mode 1
        if (mode == 1)
        {
            while (!remainingWords.isEmpty())
            {
                //Computer's turn
                word = getNextWord(letter, remainingWords);
                letter = nextLetter(word.charAt(0));
                System.out.println(word);
                remainingWords.remove(word);
                word = getNextWord(letter, remainingWords);
                letter = word.charAt(0);
                System.out.println("The next word should start with "+ letter + ".");
                //User's turn
                word = in.nextLine().toLowerCase();
                if (!remainingWords.contains(word))
                {
                    System.out.println("Sorry, that word is unacceptable. Good game!");
                    return;
                }
                if (word.charAt(0) != letter)
                {
                    System.out.println("Sorry, that word is unacceptable. Good game!");
                    return;
                }
                remainingWords.remove(word);
                letter = nextLetter(letter);
            }
        }
        //mode 2
        else
        {
            while (!remainingWords.isEmpty())
            {
                //Computer's turn
                word = getNextWord(letter, remainingWords);
                letter = word.charAt(word.length() - 1);
                System.out.println(word);
                remainingWords.remove(word);
                word = getNextWord(letter, remainingWords);
                letter = word.charAt(0);
                System.out.println ("The next word should start with " + letter + ".");
                //User's turn
                word = in.nextLine().toLowerCase();
                if (!remainingWords.contains(word))
                {
                    System.out.println("Sorry, that word is unacceptable. Good game!");
                    return;
                }
                if (word.charAt(0) != letter)
                {
                    System.out.println("Sorry, that word is not acceptable. Good game!");
                    return;
                }
                remainingWords.remove(word);
                letter = word.charAt(word.length() - 1);
            }
        }
        System.out.println("Good game!");
    }

    private static void picnic()
    {
        char pattern;
        String guess;
        HashSet<String> remainingWords = new HashSet<>(Arrays.asList(dictionary));
        ArrayList<String> wordList = new ArrayList<>();
        pattern = (char) (int) (Math.random() * 26 + 97);
        for (String word1 : remainingWords)
        {
            for (int i = 0; i < word1.length(); i++)
            {
                if (word1.charAt(i) == pattern)
                {
                    wordList.add(word1);
                    break;
                }
            }
        }
        while (!wordList.isEmpty())
        {
            System.out.println(wordList.remove((int)(Math.random()*wordList.size())) + "\nWhat is the letter? ");
            guess = in.next().toLowerCase();
            in.nextLine();
            if (guess.charAt(0) == pattern)
            {
                System.out.println("Congratulations you have guessed the pattern!");
                return;
            } else
                System.out.println("That is incorrect.");
        }
        System.out.println ("There are no more words left for me to choose from! Try again next time!");
    }

    private static void twoWayHangman()
    {
        chooseC();
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String play, game;
        initialize();
        dictionaryOpen();
        //Welcome message
        System.out.print("Welcome to a compilation of some word games! Would you like to play? (y = yes, other = no) ");
        play = in.next();
        in.nextLine();
        while (play.equalsIgnoreCase("y"))
        {
            //Game choice
            System.out.println("Choose your game: \n1. Concentration \n2. Categories \n3. Picnic \n4. Two-way Hangman");
            game = in.next();
            in.nextLine();
            //Opens Game
            switch (game)
            {
                case "1":
                    instOpen("1");
                    concentration();
                    break;
                case "2":
                    instOpen("2");
                    categories();
                    break;
                case "3":
                    instOpen("3");
                    picnic();
                    break;
                case "4":
                    instOpen("4");
                    twoWayHangman();
                    break;
                default:
                    System.out.println("Sorry that game is not valid. Please try again and enter an integer from 1 to 4 when asked.");
            }
            //Play again?
            System.out.print("Would you like to play again? (y = yes, n = no) ");
            play = in.next();
            in.nextLine();
        }
        System.out.println("Goodbye! Have a nice day!");
    }
}