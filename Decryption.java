//import statements
import java.util.*;

public class Decryption {
    //creating letter frequency arrayList
    private ArrayList<LetterFrequency> FreqStat = new ArrayList<>(
        Arrays.asList(
            new LetterFrequency('e', 12.02),
            new LetterFrequency('t', 9.10),
            new LetterFrequency('a', 8.12),
            new LetterFrequency('o', 7.68),
            new LetterFrequency('i', 7.31),
            new LetterFrequency('n', 6.95),
            new LetterFrequency('s', 6.28),
            new LetterFrequency('r', 6.02),
            new LetterFrequency('h', 5.92),
            new LetterFrequency('d', 4.32),
            new LetterFrequency('l', 3.98),
            new LetterFrequency('u', 2.88),
            new LetterFrequency('c', 2.71),
            new LetterFrequency('m', 2.61),
            new LetterFrequency('f', 2.30),
            new LetterFrequency('y', 2.11),
            new LetterFrequency('w', 2.09),
            new LetterFrequency('g', 2.03),
            new LetterFrequency('p', 1.82),
            new LetterFrequency('b', 1.49),
            new LetterFrequency('v', 1.11),
            new LetterFrequency('k', 0.69),
            new LetterFrequency('x', 0.17),
            new LetterFrequency('q', 0.11),
            new LetterFrequency('j', 0.1),
            new LetterFrequency('z', 0.07)
        ));

    private ArrayList<String> scentence;
    private ArrayList<String> guessedScentence = new ArrayList<String>();
    private ArrayList<LetterFrequency> letterFreq = new ArrayList<LetterFrequency>();


    //methods

    Decryption(String cipher){
        splitCipher(cipher);
        createGuessedArray();
        CalculateLetterFrequency();
        
        /*to be implemented
        createWordLength();
        */
    }

    private void splitCipher(String cipher){
        scentence = new ArrayList<String>(Arrays.asList(cipher.split(" ")));
        System.out.printf("Split cipher %s\n", scentence);
    }

    private void createGuessedArray(){
        for(String word : scentence){
            
            //generating a replacement the same length as word with - filled
            String replacement = "";
            for(int i = 0; i < word.length(); i++){
                replacement += "-";
            }
            guessedScentence.add(replacement);
        }

        System.out.printf("Guessed Scentence %s\n",guessedScentence);
    }

    private void CalculateLetterFrequency(){
        System.out.printf("Calcuate Letter Frequency has been called\n");
    }

    
}

//object to store within our arraylist
class LetterFrequency{
    private char character;
    private double freq;

    LetterFrequency(char character, double freq){
        this.character = character;
        this.freq = freq;
    }

    //getters and setters

    public char getCharacter() {
        return this.character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public double getFreq() {
        return this.freq;
    }

    public void setFreq(double freq) {
        this.freq = freq;
    }

}
