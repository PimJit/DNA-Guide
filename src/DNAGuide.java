import java.util.ArrayList;
import java.util.Scanner;

public class DNAGuide {
    public static String sequence;
    public static ArrayList<String> totalSequence = new ArrayList<>();        //variable for entire DNA sequence that includes all guides


    public static int startGt;
    public static int endGt;
    public static int startGn;
    public static int endGn;
    public static int startGf;
    public static int endGf;
    public static int startGr;
    public static int endGr;

    public static String guideMaker(int numberNT) {
        ArrayList<String> gn = new ArrayList<>();       //variable for actual gn
        ArrayList<String> potentalGn = new ArrayList<>();       //variable for potientalgn to check
        float numberGC;
        StringBuilder print1 = new StringBuilder();

        for (int a = 9; a <= sequence.length()- (10 + numberNT); a++ ) {
            if (sequence.charAt(a) == 't' && sequence.charAt(a + 11) == 'a') {        //checking for 1st position to be t
                totalSequence.add(sequence.substring(a - 10, a + (10 + numberNT)));        //variable for nt length
                potentalGn.add(sequence.substring(a, a + numberNT));
            }
        }

        int number = 0;     //counting g and c
        for (int b = 0; b < potentalGn.size(); b++) {       //looping through the list of potentialGn
            for (int c = 0; c < potentalGn.get(b).length(); c++) {      //looping through individual character of that potentialGn
                if (potentalGn.get(b).charAt(c) == 'g' || potentalGn.get(b).charAt(c) == 'c') {     //checking g or c of that individual character
                    number += 1;
                }
            }

            numberGC = ((float) number)/numberNT;
            if ( numberGC <= 0.37 && numberGC >= 0.125) {          //checking g/c content
                //totalSequence.add(sequence.substring(0,27));
                gn.add(potentalGn.get(b) + "   " + numberGC);
                number = 0;
                numberGC = 0;
            }
        }


        for (int i = 0; i < gn.size();i++) {
            print1.append(gn.get(i)).append("   ");
        }
        return print1.toString();
    }


    public static void main(String[] args) {
        System.out.println("DNA sequence: ");
        System.out.println(args[0]);
        // Scanner scan = new Scanner(System.in);
        // sequence = scan.nextLine().toLowerCase();
        sequence = args[0];

        System.out.println(guideMaker(16));
        System.out.println(guideMaker(17));
        System.out.println(guideMaker(18));


        //getting gn
        //setting for loop to begin at 11 so that we wont get be out of bound to make the guide

    }
}
