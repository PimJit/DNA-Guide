/**
 * @author: Nanthalak (Pim) Jitnavasathien
 */

import java.util.ArrayList;
import java.util.Scanner;

public class DNAGuide {
    public static String sequence;

    public static String grComplementary(String gr) {
       StringBuilder complementary = new StringBuilder();           //individual gr non-complementary

           for (int b = 0; b < gr.length(); b++) {
               if (gr.charAt(b) == 't') {
                    complementary.append("a");
               }
               if (gr.charAt(b) == 'a') {
                   complementary.append("t");
               }
               if (gr.charAt(b) == 'g') {
                   complementary.append("c");
               }
               if (gr.charAt(b) == 'c') {
                   complementary.append("g");
               }
           }
           complementary.reverse();         //reverse order
        return complementary.toString();
    }

    public static String guideMaker(int numberNT) {
        ArrayList<String> totalSequence = new ArrayList<>();            //variable for entire DNA sequence that includes all guides

        ArrayList<String> gn = new ArrayList<>();                       //variable for actual gn
        ArrayList<String> potentialGn = new ArrayList<>();              //variable for potential to check
        ArrayList<String> potentialSequence = new ArrayList<>();        //variable for potential full sequence for later checking
        float numberGC;
        StringBuilder gnPrint = new StringBuilder();                    //print for gn
        StringBuilder totalSequencePrint = new StringBuilder();         //print for totalSequence
        StringBuilder gfPrint = new StringBuilder();                    // print for gf
        StringBuilder gtPrint = new StringBuilder();                    //print for gt
        StringBuilder grPrint = new StringBuilder();                    //print for gr

        ArrayList<String> gf = new ArrayList<>();        //variable for passed gf
        ArrayList<String> gt = new ArrayList<>();        //variable for passed gt
        ArrayList<String> gr = new ArrayList<>();        //variable for passed gr
        String output = "";

        Boolean potentialGf = false;        //initializing potential gf guides for later check
        Boolean potentialGt = false;        //initializing potential gt guides for later check
        Boolean potentialGr = false;        //initializing potential gr guides for later check

        //creating gn
        for (int a = 9; a <= sequence.length()- (10 + numberNT); a++ ) {
            if (sequence.charAt(a) == 't' && sequence.charAt(a + 12) == 'a') {                  //checking for 1st position to be t
                potentialSequence.add(sequence.substring(a - 10, a + (10 + numberNT)));         //variable for nt length
                potentialGn.add(sequence.substring(a, a + numberNT));                           //getting potentialGn sequence for later checking
            }
        }

        int number = 0;     //counting g and c
        for (int b = 0; b < potentialGn.size(); b++) {                                                 //looping through the list of potentialGn
            for (int c = 0; c < potentialGn.get(b).length(); c++) {                                    //looping through individual character of that potentialGn
                if (potentialGn.get(b).charAt(c) == 'g' || potentialGn.get(b).charAt(c) == 'c') {      //checking g or c of that individual character
                    number += 1;
                }
            }

            numberGC = ((float) number)/numberNT;                   //getting percentage of G/C
            if ( numberGC <= 0.37 && numberGC >= 0.125) {           //checking g/c content
                totalSequence.add(potentialSequence.get(b));
                gn.add(potentialGn.get(b) + "   " + numberGC);      //getting the passed gn and its G/C percentage
                number = 0;     //resetting
                numberGC = 0;   //resetting
            }
        }

        //creating gt
        for (int a = 0; a < totalSequence.size(); a++) {
            //checking gt
            if (totalSequence.get(a).charAt(0) == 't') {
                if (totalSequence.get(a).charAt(11) == 'g' || totalSequence.get(a).charAt(11) == 'c') {
                    potentialGt = true;
                } else {
                    potentialGt = false;
                }
            }

            //checking gf
            if (totalSequence.get(a).charAt(10 + (numberNT - 10)) == 't') {
                if (totalSequence.get(a).charAt(9 + (numberNT + 2)) == 'g' || totalSequence.get(a).charAt(9 + (numberNT + 2)) == 'c') {
                    potentialGf = true;
                } else {
                    potentialGf = false;
                }
            }

            //checking gr
            if (totalSequence.get(a).charAt(19 + numberNT) == 'a') {
                if (totalSequence.get(a).charAt(10 + (numberNT - 2)) == 'g' || totalSequence.get(a).charAt(10 + (numberNT - 2)) == 'c') {
                    potentialGr = true;
                } else {
                    potentialGr = false;
                }
            }

            //getting all guides
            String getGr;
            if (potentialGt && potentialGf && potentialGr) {
                gt.add(totalSequence.get(a).substring(0,18));                                           //gt size of 18 nt
                gf.add(totalSequence.get(a).substring(10 + (numberNT - 10), 29 + (numberNT - 11)));     //gf size of 18 nt
                getGr = totalSequence.get(a).substring(numberNT + 2, 10 + numberNT + 10);               //gr size of 18 nt
                gr.add(grComplementary(getGr));             //gr size of 18 nt(?)
                potentialGf = false;                        //resetting variable for future loops
                potentialGt = false;                        //resetting variable for future loops
                potentialGr = false;                        //resetting variable for future loops

                //return values
                for (int i = 0; i < gn.size(); i++) {            //to print ArrayList gn
                    gnPrint.append(gn.get(i)).append("   ");
                }

                for (int i = 0; i < gf.size(); i++) {            //to print ArrayList gf
                    gfPrint.append(gf.get(i)).append("   ");
                }

                for (int i = 0; i < gt.size(); i++) {            //to print ArrayList gt
                    gtPrint.append(gt.get(i)).append("   ");
                }

                for (int i = 0; i < gr.size(); i++) {            //to print ArrayList gr
                    grPrint.append(gr.get(i)).append("   ");
                }

                output = gnPrint.toString() + " " + gtPrint.toString() + " " + gfPrint.toString() + " " + grPrint.toString();       //return value from this method
            } else {
                output = "None matched the criteria";
            }
        }
        return output;
    }

    public static void main(String[] args) {
        System.out.println("DNA sequence: ");
        //System.out.println(args[0]);
        Scanner scan = new Scanner(System.in);
        sequence = scan.nextLine().toLowerCase();
        //sequence = args[0];

        System.out.println(guideMaker(16));
        System.out.println(guideMaker(17));
        System.out.println(guideMaker(18));
    }
}
