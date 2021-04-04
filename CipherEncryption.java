//import statements
import java.util.*;


class CipherEncryption{
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("Compiled");
        System.out.println("Please enter the message you would like to encrypt: ");
        Encryption encrypt = new Encryption(scan.nextLine());
        encrypt.createKey();
        encrypt.createCipher();
    }
}


class Encryption{
    private String message;
    private String cipher;
    private ArrayList<Character> alpha = new ArrayList<Character>();        //alphabet
    private ArrayList<Character> key = new ArrayList<Character>();

    //Constructor
    Encryption(String message){
        this.message = message;
        
        //creating arraylist of alphabet
        Character[] temp = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        Collections.addAll(alpha, temp);
    }

    //getters and setters

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Character> getAlpha() {
        return this.alpha;
    }

    public void setAlpha(ArrayList<Character> alpha) {
        this.alpha = alpha;
    }

    public ArrayList<Character> getKey() {
        return this.key;
    }

    public void setKey(ArrayList<Character> key) {
        this.key = key;
    }
    


    //creates key for encryption to use
    public void createKey(){
        //creating a temporary array of letter
        ArrayList<Character> temp = (ArrayList<Character>) alpha.clone();

        //creating random number object
        Random rand = new Random();
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
            key.add(letter);

        }

        System.out.printf("Key created, %s", getKey());
    }

    //creates cipher text based on encryption key
    public void createCipher(){
        /*Stumbles on non letter, spaces and full stops */
        for (int i = 0; i < message.length() ; i++){
            //getting letter
            Character letter = message.charAt(i);
            System.out.println(alpha.indexOf(letter));
            //getting cipher letter
            Character cLetter = key.get(alpha.indexOf(letter));
            //adding letter to cipher text
            cipher += Character.toString(cLetter);
        }

        System.out.printf("Cipher Text %s", cipher);
    }

}