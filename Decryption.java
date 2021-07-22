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
        
    private ArrayList<String> singleLetter = new ArrayList<>(
        Arrays.asList("a", "i"));

    private ArrayList<String> twoLetter = new ArrayList<>(
        Arrays.asList("of", "to", "in", "it", "is", "be", "as", "at", "so", "we", "he", "by", "or", "on", "do", "if", "me", "my", "up", "an", "go", "no", "us", "am"));
    
    private ArrayList<String> cipher;
    private ArrayList<String> guessedCipher = new ArrayList<String>();
    private ArrayList<GuessedLetter> letterFreq = new ArrayList<GuessedLetter>();


    //getters and setters

    public ArrayList<LetterFrequency> getFreqStat() {
        return this.FreqStat;
    }

    public void setFreqStat(ArrayList<LetterFrequency> FreqStat) {
        this.FreqStat = FreqStat;
    }

    public ArrayList<String> getCipher() {
        return this.cipher;
    }

    public void setCipher(ArrayList<String> cipher) {
        this.cipher = cipher;
    }

    public ArrayList<String> getGuessedCipher() {
        return this.guessedCipher;
    }

    public void setGuessedCipher(ArrayList<String> guessedCipher) {
        this.guessedCipher = guessedCipher;
    }

    public ArrayList<GuessedLetter> getLetterFreq() {
        return this.letterFreq;
    }

    public void setLetterFreq(ArrayList<GuessedLetter> letterFreq) {
        this.letterFreq = letterFreq;
    }


    //methods

    Decryption(String cMessage){
        splitCipher(cMessage);
        createGuessedArray();
        CalculateLetterFrequency(cMessage.length());
        
        /*to be implemented
        createWordLength();
        */
    }

    private void splitCipher(String cMessage){
        //storing the cipher as an arraylist of words, this will be striped of non alphabetic characters and split as per space.
        cipher = new ArrayList<String>(Arrays.asList(cMessage.replaceAll("[^a-z ]", "").split(" ")));
        System.out.printf("Split cipher %s\n", cipher);
    }

    private void createGuessedArray(){
        for(String word : cipher){
            
            //generating a replacement the same length as word with - filled
            String replacement = "";
            for(int i = 0; i < word.length(); i++){
                replacement += "-";
            }
            guessedCipher.add(replacement);
        }

        System.out.printf("Guessed Scentence %s\n",guessedCipher);
    }

    private void CalculateLetterFrequency(int length){
        char letter = 'a';

        //looping through a-z
        while((int)letter < 123){
            int count = 0;
            
            //looping through each word within the cipher
            for(String word : cipher){
                //looping through each character within the word
                for(int i = 0; i < word.length(); i++){
                    //if the words are the same, the count will increase
                    if(letter == word.charAt(i)){
                        count ++;
                    }
                }
            }

            letterFreq.add(new GuessedLetter(letter, (double)count/length*100));
            letter ++;
        }

        
        //sorting the arraylist based on the letter frequency
        Collections.sort(letterFreq, new Comparator<GuessedLetter>(){
            //ascending, flip to do decending
            public int compare(GuessedLetter obj1, GuessedLetter obj2){
                return Double.valueOf(obj2.getFreq()).compareTo(obj1.getFreq());
            }
        });
        
        
        //printing out letter Freq found
        //System.out.printf("Letter Frequency Found: %s\n", letterFreq);

    }

    /*Outdated
    public boolean GuessLetterFrequency(){
        
        int i =0;
        boolean change = false;     //used to return, indicating a chage

        //looping through each object within letter frequency, looking for distinct frequencies
        while( i < letterFreq.size()-1){                                                                //this is currently one less than the alhpabet, whilst its good now we should check it out later
            
            //if the frequency is not distinct, we will iterate to the next disntic frequency
            if(letterFreq.get(i).getFreq() == letterFreq.get(i+1).getFreq())    {
                
                int count = 2;
                boolean duplicate = true;
                
                //finding the next distinct frequency
                while(duplicate && i+count < letterFreq.size()){
                    if(letterFreq.get(i).getFreq() == letterFreq.get(i+count).getFreq())    {
                        count ++;
                    }
                    else{
                        duplicate = false;
                    }
                }
                i += count;

            }
            else{
                //Changing the gussed letter based on frequency
                letterFreq.get(i).setGussedLetter(FreqStat.get(i).getCharacter());

                //removing the character from the guessable characters list
                
                //updating the guessedCipher
                updateGuessedCipher(letterFreq.get(i));

                //indicating a change has been made
                change = true;
                
            }
            i ++;
        }

        System.out.printf("Updated Gusesses:\n%s\n", letterFreq);
        System.out.printf("\nUpdated cipher: %s", guessedCipher);
        return change;

    }
    */

    public void updateGuessedCipher(GuessedLetter guess){
        //replaces all encrypted letters with the guessed letter

        for(int a = 0; a < cipher.size(); a++){
            //creates a temporary string to modify
            String temp = guessedCipher.get(a);

            for(int b = 0; b < cipher.get(a).length(); b++ ){
                //checking to see if the characters are the same
                if(cipher.get(a).charAt(b) == guess.getCharacter()){
                    //updates character by seperating the string into two parts
                    temp = temp.substring(0,b) + guess.getGussedLetter() + temp.substring(b + 1);
                }
            }

            //updates string within the arrayList
            guessedCipher.set(a, temp);

        }

    }
 
    public void PossibleCharacters(){
        //gets possible characters that the encoded letter could be from 5 closest characters from letter frequency
        for(int a = 0; a < letterFreq.size(); a++){        
            for(int b = -2; b < 3; b++){
                try{
                    letterFreq.get(a).getPossibleCharacters().add(FreqStat.get(a-b).getCharacter());
                }
                catch(IndexOutOfBoundsException err){     //find what error to catch
                    //debug statement
                    System.out.printf("\nIndex out of bounds, skipping");
                }
            }
        }

        System.out.printf("Possible Characters:\n\n%s", letterFreq);
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

    public String toString(){
        return String.format("\nChar: %c\tFrequency: %f", getCharacter(), getFreq());
    }

}

class GuessedLetter extends LetterFrequency{

    private char gussedLetter = '-';
    private ArrayList<Character> PossibleCharacters = new ArrayList<>();

    GuessedLetter(char character, double freq){
        super(character, freq);
    
    }        //calling the parent class constructor

    //getters and setters
    public char getGussedLetter() {
        return this.gussedLetter;
    }

    public void setGussedLetter(char gussedLetter) {
        this.gussedLetter = gussedLetter;
    }

    public ArrayList<Character> getPossibleCharacters() {
        return this.PossibleCharacters;
    }

    public void setPossibleCharacters(ArrayList<Character> PossibleCharacter) {
        this.PossibleCharacters = PossibleCharacters;
    }


    //tostring method
    public String toString(){
        return String.format("\nChar: %c\tFrequency: %f\tGuessed Letter: %c\nPossible Guesses: %s\n", getCharacter(), getFreq(), getGussedLetter(), getPossibleCharacters());
    }
}