//import statements
import java.util.*;
//import java.util.concurrent.atomic.AtomicInteger;

public class Decryption {
    //creating letter frequency arrayList
    private ArrayList<LetterFrequency> freqStat = new ArrayList<>(
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

    //contains all common words
    private ArrayList<ArrayList<String>> commonWords = new ArrayList<>(
            Arrays.asList(
                new ArrayList<>(Arrays.asList("a", "i")), 
                new ArrayList<>(Arrays.asList("of", "to", "in", "it", "is", "be", "as", "at", "so", "we", "he", "by", "or", "on", "do", "if", "me", "my", "up", "an", "go", "no", "us", "am")), 
                new ArrayList<>(Arrays.asList("the", "and", "for", "are", "but", "not", "you", "all", "any", "can", "had", "her", "was", "one", "our", "out", "day", "get", "has", "him", "his", "how", "man", "new", "now", "old", "see", "two", "way", "who", "boy", "did", "its", "let", "put", "say", "she", "too", "use")), 
                new ArrayList<>(Arrays.asList("that", "with", "have", "this", "will", "your", "from", "they", "know", "want", "been", "good", "much", "some", "time", "what", "when", "then", "them", "like", "were", "come", "more", "make", "word", "said", "look", "many", "each", "long"))
                ));
    
    private ArrayList<String> cipher;                                               
    private ArrayList<String> guessedCipher = new ArrayList<>();             
    private ArrayList<GuessedLetter> letterFreq = new ArrayList<>();   


    //getters and setters

    public ArrayList<LetterFrequency> getFreqStat() {
        return this.freqStat;
    }

    public void setFreqStat(ArrayList<LetterFrequency> freqStat) {
        this.freqStat = freqStat;
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
        calculateLetterFrequency(cMessage.length());
        
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

    private void calculateLetterFrequency(int length){
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
        
        
    }

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
 
    public void possibleCharacters(){
        //gets possible characters that the encoded letter could be from 5 closest characters from letter frequency
        for(int a = 0; a < letterFreq.size(); a++){ 
            //creating a temp Arraylist to be later set
            ArrayList<Character> temp = new ArrayList<>();

            //looping through five (from lower to upper)
            for(int b = -2; b < 3; b++){
                try{
                    temp.add(freqStat.get(a-b).getCharacter());

                }
                catch(IndexOutOfBoundsException err){
                    //catches either end of the array, this is expected
                    System.out.printf("\nIndex out of bounds %d, skipping", (a-b));
                }
            }

            //setting the arrayList
            letterFreq.get(a).setPossibleCharacters(temp);
        }

        System.out.printf("Possible Characters:\n\n%s", letterFreq);
    }

    public void letterElimination(int wordRow){
        /*This method is used to check the letters against the words
            whilst wordlist serves as a arrayindex it can also be used as the word size if we add 1
        */
        
        //checking within bounds. 
        if(wordRow > commonWords.size() || wordRow < 0){
            throw new IllegalArgumentException("Invalid Row");
        }

        //finding the size of the words
        int size = commonWords.get(wordRow).get(0).length();


        //for each object within letterFreq
        for(int a = 0; a < letterFreq.size(); a++){

            //recorded and compared entries
            ArrayList<String> wordInstance = new ArrayList<>();                                                      //contains instances found
            ArrayList<Integer> letterIndex = new ArrayList<>(Collections.nCopies(size, 0));            //contains indexes found
            ArrayList<Integer> indexRemoval = new ArrayList<>();                                                     //AL of indexes to be removed

            //for each word within the cipher
            for(int b = 0; b < cipher.size(); b++){
                //checking the size of the word and if the character is present
                if(cipher.get(b).length() == size && cipher.get(b).contains(Character.toString(letterFreq.get(a).getCharacter()))){
                    

                    //checking wether the word is a duplicate
                    if(!wordInstance.contains(cipher.get(b))){
                        
                        //adding the two letter word into the list and increasing the index count
                        wordInstance.add(cipher.get(b));

                        //finding the index of the word, increasing the counter
                        int index = cipher.get(b).indexOf(letterFreq.get(a).getCharacter());
                        int value = letterIndex.get(index);
                        value ++;
                        letterIndex.set(index, value);
                    }
                    
                }
            }

            //for each possible letter guess
            for(int b = 0; b < letterFreq.get(a).getPossibleCharacters().size(); b++){
                
                ArrayList<Integer> letterComparison = new ArrayList<>(Collections.nCopies(size, 0));       //contains possible indexes in statistics


                //for each two letter (statistic)
                for(int c = 0; c < commonWords.get(wordRow).size(); c++){
                    //checking for match
                    if(commonWords.get(wordRow).get(c).contains(Character.toString(letterFreq.get(a).getPossibleCharacters().get(b)))){
                        //recording the index if there is a match
                        int index = commonWords.get(wordRow).get(c).indexOf(letterFreq.get(a).getPossibleCharacters().get(b));
                        int value = letterComparison.get(index);
                        value ++;
                        letterComparison.set(index, value);
                    }
                }

                //debug statemetn
                System.out.printf("Character: %s, Guess: %s, Index: %s, Comparison %s\n", letterFreq.get(a).getCharacter(), letterFreq.get(a).getPossibleCharacters().get(b), letterIndex, letterComparison);
                
                //if the possible letter is not possible
                boolean possible = true;
                int counter = 0;

                //checking whether the word is possible
                while(possible && counter < size){
                    if(letterIndex.get(counter) > letterComparison.get(counter)){
                        System.out.printf("Character(cipher): %s, Character(getting removed): %s, index: %d, TwoLetterIndex: %s, TwoLetterCompariosn: %s\n", letterFreq.get(a).getCharacter(), letterFreq.get(a).getPossibleCharacters().get(b), b, letterIndex, letterComparison);
                        indexRemoval.add(b);
                        possible = false;
                    }
                    counter ++;
                }

            }

            
            //removing characters this has to be done backwards to not affect the indexing
            for(int b = indexRemoval.size()-1 ; b > -1; b--){
                

                letterFreq.get(a).getPossibleCharacters().remove((int)indexRemoval.get(b));
            }
        }
    }

    public void printPossibleCharacters(){
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
    private ArrayList<Character> possibleCharacters = new ArrayList<>();

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
        return this.possibleCharacters;
    }

    public void setPossibleCharacters(ArrayList<Character> possibleCharacters) {
        this.possibleCharacters = possibleCharacters;
    }


    //tostring method
    public String toString(){
        return String.format("\nChar: %c\tFrequency: %f\tGuessed Letter: %c\nPossible Guesses: %s\n", getCharacter(), getFreq(), getGussedLetter(), getPossibleCharacters());
    }
}