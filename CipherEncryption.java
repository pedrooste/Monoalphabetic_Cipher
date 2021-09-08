/*
Author: Pedro Oste
Email: pedro.ost3@gmail.com
Encrypts a user input based on a Monoalphabetic Cipher
*/


//import statements
import java.util.*;
import java.io.*;



//main class
class CipherEncryption{
    
    //scanner to gain user input
    public static Scanner scan = new Scanner(System.in);
    private Encryption encrypt;
    private Decryption decrypt;
    public static void main(String[] args){
        
        CipherEncryption cipherEncryption = new CipherEncryption();
        
        
        
        
        //getting text input
        String input = cipherEncryption.extractString("text.txt");


        //creating encyption object, key and cipher using the input
        cipherEncryption.encrypt = new Encryption(input);
        cipherEncryption.encrypt.createKey();
        cipherEncryption.encrypt.createCipher();

        cipherEncryption.decrypt = new Decryption(cipherEncryption.encrypt.getCipher());
        cipherEncryption.decrypt.possibleCharacters();
        
        System.out.print("\n\nChecking single letter\n");
        cipherEncryption.decrypt.letterElimination(0);
        
        System.out.print("\n\nChecking double letter\n");
        cipherEncryption.decrypt.letterElimination(1);
        
        System.out.print("\n\nChecking tripple letter\n");
        cipherEncryption.decrypt.letterElimination(2);
        

        cipherEncryption.calculateCorrectGuesses();

        cipherEncryption.decrypt.printPossibleCharacters();

        
    }


        


    //methods

    public void calculateCorrectGuesses(){
        //calculating how correct the guesses are
        int guess = 0;
        int correct = 0;

        //finding guesses
        for(int a =0; a < decrypt.getLetterFreq().size(); a++){
            if(decrypt.getLetterFreq().get(a).getGussedLetter() != '-'){
                guess ++;
                boolean found = true;
                int b = 0;

                //finding character equivalent
                while(found && b < encrypt.getAlpha().size()){
                    if(decrypt.getLetterFreq().get(a).getGussedLetter() == encrypt.getAlpha().get(b)){
                        found = false;
                        //determining whether the guess is correct
                        if(decrypt.getLetterFreq().get(a).getCharacter() == encrypt.getKey().get(b)){
                            correct ++;
                        }
                    }
                    b++;
                }
                
            }
        }

        System.out.printf("\nYou have got %d out of %d\n", correct, guess);
    }

    public String extractString(String fileName){
        //extracts the string from the text file
        
        //using a string builder
        StringBuilder str = new StringBuilder();

        //opening the file
        File file = new File(fileName);
        
        try{
            //trying to read the file, specfic format
            Scanner sc = new Scanner(file,"utf-8");

            //looping through all lines...
            while(sc.hasNext()){
                String tempWord = sc.next();

                //now we go through character by character adding valid chars
                for(int i = 0; i < tempWord.length(); i++){
                    if((tempWord.charAt(i)>= 'a' && tempWord.charAt(i)<='z') || (tempWord.charAt(i)>= 'A' && tempWord.charAt(i)<='Z')){
                        str.append(tempWord.charAt(i));
                    }
                }

                //indicating next word
                str.append(" ");

            }

            //closing scanner
            sc.close();

        }
        catch (FileNotFoundException err){
            System.err.print("File Could not be found\n");
        }

        System.out.println("Text " + str.toString());

        return str.toString();
    }


    
}

