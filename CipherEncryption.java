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
    public static void main(String[] args){
        //getting user input
        System.out.print("Please enter the message you would like to encrypt: ");
        
        //creating encyption object, jey and cipher using the input
        Encryption encrypt = new Encryption(scan.nextLine());
        encrypt.createKey();
        encrypt.createCipher();

        Decryption decrypt = new Decryption(encrypt.getCipher());
    }
}

