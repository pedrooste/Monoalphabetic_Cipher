//import statements
import java.util.*;

class Encryption{
    //user message
    private String message;
    //encrypted user message using cipher
    private String cipher;
    //alphabet arraylist
    private static ArrayList<Character> alpha = new ArrayList<>(
        Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'));
    //Key generated to ecnrypt message
    private ArrayList<Character> key = new ArrayList<>();

    //random object (used to generate key)
    public static Random rand = new Random();

    //Constructor
    Encryption(String message){
        this.message = message.toLowerCase();
    }

    //getters and setters

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        //assigning the message in lower case form        
        this.message = message.toLowerCase();
    }

    public String getCipher(){
        return this.cipher;
    }

    public void setCipher(String cipher){
        this.cipher = cipher;
    }

    public ArrayList<Character> getAlpha() {
        return this.alpha;
    }

    public ArrayList<Character> getKey() {
        return this.key;
    }

    public void setKey(ArrayList<Character> key) {
        this.key = key;
    }

    


    //creates key for encryption to use
    public void createKey(){

        //creating a temporary array of alphabet
        ArrayList<Character> temp = (ArrayList<Character>) alpha.clone();

        //creating a local key
        ArrayList<Character> localKey = new ArrayList<>();

        //creating random upperbound
        int upperbound = 26;

        //assigning key
        for (int i = 0; i < alpha.size(); i++){
            //getting random number
            int randNum = rand.nextInt(upperbound);          
            //getting random letter
            Character letter = temp.get(randNum);
            //removing letter
            temp.remove(randNum);
            //reducing random size
            upperbound --;
            //adding letter to key
            localKey.add(letter);

        }
        
        //assining key to object
        setKey(localKey);
        
        //displaying alphabet
        System.out.printf("Alphabet:%n%s%n", getAlpha());

        //displaying key 
        System.out.printf("Key created:%n%s%n", getKey());

    }

    //creates cipher text based on encryption key
    public void createCipher(){
        
        //creating local cipher
        String localCipher = "";

        /*Stumbles on non letter, spaces and full stops */
        for (int i = 0; i < message.length() ; i++){
            
            //getting character to encrpyt
            Character letter = message.charAt(i);

            //determining if the character presented is within the key (this will skip any special characters and full stops)
            int charIndex = getAlpha().indexOf(letter);
            Character cLetter;

            if(charIndex != -1){
                //getting cipher letter
                cLetter = key.get(charIndex);
            }
            else{
                cLetter = letter;
            }
            
            //adding letter to cipher text
            localCipher += Character.toString(cLetter);
        }

        System.out.printf("Cipher Text: %s\n", localCipher);

        //setting the cipher
        cipher = localCipher;
    }

}