import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static String sequence;
    public static ArrayList<String> totalSequence = new ArrayList<>();        //variable for entire DNA sequence that includes all guides


    public static String GuideMaker(int numberNT) {
        ArrayList<String> gn = new ArrayList<>();
        for (int a = 11; a <= (sequence.length()-11); a++ ) {
            if (sequence.charAt(a) == 't') {        //checking for 1st position to be t
                String potientalGn = sequence.substring(11, numberNT);        //variable for nt length
                int number = 0;
                for (int b = 0; b < potientalGn.length(); b++) {
                    if (potientalGn.charAt(b) == 'g' || potientalGn.charAt(b) == 'c') {     //checking g or c
                        number += 1;
                    }
                }
                if (number >= 2 && number <= 6) {          //checking g/c content
                    //totalSequence.add(sequence.substring(0,27));
                    gn.add(potientalGn);
                }
            }
        }




        return gn + " \n" + totalSequence;
    }

//    public String GnMaker(int numberNT) {
//
//    }

    public static void main(String[] args) {
        System.out.println("DNA sequence: ");
        Scanner scan = new Scanner(System.in);
        sequence = scan.nextLine().toLowerCase();

        GuideMaker(16);
        GuideMaker(17);
        GuideMaker(18);


        //getting gn
        //setting for loop to begin at 11 so that we wont get be out of bound to make the guide


        }
}
