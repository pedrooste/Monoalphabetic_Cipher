/*
Author: Pedro Oste
Email: pedro.ost3@gmail.com
Encrypts a user input based on a Monoalphabetic Cipher
*/


//import statements
import java.util.*;


//main class
class CipherEncryption{
    
    //scanner to gain user input
    public static Scanner scan = new Scanner(System.in);
    private Encryption encrypt;
    private Decryption decrypt;
    public static void main(String[] args){
        
        CipherEncryption cipherEncryption = new CipherEncryption();
        
        //getting user input
        System.out.print("Please enter the message you would like to encrypt: ");
        
        //creating encyption object, jey and cipher using the input
        cipherEncryption.encrypt = new Encryption(scan.nextLine());
        cipherEncryption.encrypt.createKey();
        cipherEncryption.encrypt.createCipher();

        cipherEncryption.decrypt = new Decryption(cipherEncryption.encrypt.getCipher());
        //cipherEncryption.decrypt.GuessLetterFrequency(); no longer in use
        cipherEncryption.decrypt.PossibleCharacters();

        cipherEncryption.calculateCorrectGuesses();


        
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


    
}

