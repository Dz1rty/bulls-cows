package bullscows;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        int numLen = 0;
        String rt = "";
        try {
            rt = scanner.nextLine();
            numLen = Integer.parseInt(rt);
            if(numLen==0){
                System.out.println("Error: " + rt + " isn't a valid number.");
                return;
            }
        }catch (Exception e){
            System.out.println("Error: " + rt + " isn't a valid number.");
            return;
        }

        if(numLen>36){
            System.out.println("Error: can't generate a secret number with a length of " + numLen + " because there aren't enough unique digits.");
            return;
        }
        System.out.println("Input the number of possible symbols in the code:");
        int symbols = scanner.nextInt();
        if(symbols<numLen){
            System.out.println("Error: it's not possible to generate a code with a length of " + numLen + " with " + symbols + " unique symbols.");
            return;
        }
        if(symbols>36){
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }

        System.out.println("Okay, let's start a game!");

        String[] number = random(numLen, symbols).split("");

        System.out.println(Arrays.toString(number));
        if(symbols<=10){
            System.out.println("The secret is prepared: " + "*".repeat(numLen) + " (0-" + (symbols-1) + ").");
        }else {
            System.out.println("The secret is prepared: " + "*".repeat(numLen) + " (0-9, "  + (char)(86 + symbols - (symbols - 11)) + "-"+ (char)(86+symbols)+ ").");
        }

        int turns = 0;
        while (true){
            turns++;
            System.out.println("Turn " + turns + ":");
            String[] userInput = scanner.next().split("");

            int cows = 0;
            int bulls = 0;

            for(String y : number){
                for (String x : userInput){
                    if(x.equals(y)){
                        cows++;
                        break;
                    }
                }
            }
//bulls
            for(int i = 0; i < number.length; i++){
                if(number[i] .equals(userInput[i]) ){
                    cows--;
                    bulls++;
                }
            }
            if(cows==1 && bulls==1){
                System.out.println("Grade: 1 bull and 1 cow");
            }else if(cows == 0 && bulls == numLen){
                System.out.println("Grade: " + bulls + " bulls");
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }else if(cows==1 && bulls>1){
                System.out.println("Grade: " + bulls + " bulls and 1 cow");
            }else if(cows>1 && bulls==1){
                System.out.println("Grade: 1 bull and " + cows + " cows");
            }else if(cows>1 && bulls>1){
                System.out.println("Grade: " + bulls + " bulls and " + cows + " cows");
            }else if(cows==0 && bulls==1){
                System.out.println("Grade: 1 bull");
            }else if(cows==0 && bulls>1){
                System.out.println("Grade: " + bulls + " bulls");
            }else if(cows==1 && bulls==0){
                System.out.println("Grade: 1 cow");
            }else if(cows>1 && bulls==0){
                System.out.println("Grade: " + cows + " cows");
            }else if(cows==0&bulls==0){
                System.out.println("None");
            }
        }
    }

    static String random(int numLen, int symbols){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        x:
        while (sb.length()<numLen){

            int rand = random.nextInt(symbols);

            for(String x : sb.toString().split("")){
                if(rand<=9){
                    if(String.valueOf(rand).equals(x)){
                        continue x;
                    }
                }else {
                    if(x.equals((char)(rand+87)+"")){
                        continue x;
                    }
                }

            }
            if(rand>9){
                sb.append((char) (87+rand));
            }else{
                sb.append(rand);
            }
        }
        return sb.toString();
    }
}
