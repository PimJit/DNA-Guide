/**
 * @author: Nanthalak (Pim) Jitnavasathien
 */

import javax.swing.*;
import java.io.*;
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

    public static void guideMaker(int numberNT) {
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
        StringBuilder gcPrint = new StringBuilder();                    //print for gc content for gn


        ArrayList<String> gf = new ArrayList<>();               //variable for passed gf
        ArrayList<String> gt = new ArrayList<>();               //variable for passed gt
        ArrayList<String> gr = new ArrayList<>();               //variable for passed gr
        ArrayList<Integer> gcContent = new ArrayList<>();       //variable for gc content
        String output = "";

        Boolean GnPotential = false;
        Boolean potentialGf = false;        //initializing potential gf guides for later check
        Boolean potentialGt = false;        //initializing potential gt guides for later check
        Boolean potentialGr = false;        //initializing potential gr guides for later check

        Character test;
        //creating gn
        for (int a = 10; a <= sequence.length() - (11 + numberNT); a++) {
            if (sequence.charAt(a) == 't' && (sequence.charAt(a + 11) == 'g' || sequence.charAt(a + 11) == 'c') &&
                    sequence.charAt(a - 10) == 't' && sequence.charAt(a + numberNT - 10) == 't') {                  //checking for 1st position to be t
                potentialSequence.add(sequence.substring(a - 10, a + (11 + numberNT)));         //variable for nt length
                test = sequence.charAt(a-10);
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
            numberGC = ((float) number) / numberNT;                   //getting percentage of G/C
            if (numberGC <= 0.37 && numberGC >= 0.125) {           //checking g/c content (Ideal 0.125 - 0.37)
                totalSequence.add(potentialSequence.get(b));
                gcContent.add((int) numberGC);      //getting the passed gn and its G/C percentage
                GnPotential = true;
                number = 0;     //resetting
                numberGC = 0;   //resetting
            } else {
                number = 0;
                numberGC = 0;
            }
        }

        //creating gt
        for (int a = 0; a < totalSequence.size(); a++) {
            //checking gt
            if (totalSequence.get(a).charAt(11) == 'g' || totalSequence.get(a).charAt(11) == 'c') {
                potentialGt = true;
            } else {
                potentialGt = false;
            }

            //checking gf
            if (totalSequence.get(a).charAt(9 + (numberNT + 2)) == 'g' || totalSequence.get(a).charAt(9 + (numberNT + 2)) == 'c') {
                potentialGf = true;
            } else {
                potentialGf = false;
            }

            //checking gr
            if (totalSequence.get(a).charAt(10 + (numberNT - 2)) == 'g' || totalSequence.get(a).charAt(10 + (numberNT - 2)) == 'c') {
                potentialGr = true;
            } else {
                potentialGr = false;
            }

            //getting all guides
            String getGr;
            if (potentialGt && potentialGf && potentialGr && GnPotential) {
                gn.add(totalSequence.get(a).substring(10, 10 + numberNT));
                gt.add(totalSequence.get(a).substring(0, 18));                                           //gt size of 18 nt
                gf.add(totalSequence.get(a).substring(10 + (numberNT - 10), 29 + (numberNT - 11)));     //gf size of 18 nt
                getGr = totalSequence.get(a).substring(numberNT + 2, 10 + numberNT + 10);               //gr size of 18 nt
                gr.add(grComplementary(getGr));             //gr size of 18 nt(?)
                potentialGf = false;                        //resetting variable for future loops
                potentialGt = false;                        //resetting variable for future loops
                potentialGr = false;                        //resetting variable for future loops

                //return value
            }
        }

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

        for (int i = 0; i < gcContent.size(); i++) {            //to print ArrayList gr
            gcPrint.append(gcContent.get(i)).append("   ");
        }
        System.out.println(gnPrint);
//        final JDialog dialog = new JDialog();
//        dialog.setAlwaysOnTop(true);
//        JOptionPane.showMessageDialog(dialog, "Output " + "from " + numberNT + " length: " + "\nGN:\n " + gnPrint.toString() +
//                "\nGT:\n" + "    " + gtPrint.toString() + "\nGF:\n" + "    " + gfPrint.toString() +
//                "\nGR:\n" + "    " + gtPrint.toString());
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Choose One:\n1. Read sequence from file \n2. Write your own sequence input\n" +
                "    **Please put EITHER 1 or 2**");
        Scanner scan = new Scanner(System.in);
        int number = Integer.parseInt(scan.nextLine());

        if (number == 1) {
            System.out.println("What is your filename?\n    **CASE SENSITIVE**");
            String filename = scan.nextLine();

            if (filename == null) {
                System.out.println("You did not give the program any filename to read.");
            }

            File file = new File(filename + ".txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            try {
                sequence = br.readLine();
                guideMaker(16);
                guideMaker(17);
                guideMaker(18);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (sequence != null)
                        br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (number == 2) {
            System.out.println("DNA sequence: ");
            sequence = scan.nextLine().toLowerCase();
            //System.out.println(args[0]);
            //sequence = args[0];

            guideMaker(16);
            guideMaker(17);
            guideMaker(18);
        } else {
            System.out.println("Please type 1 or 2");
        }
    }
}
