//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class DNAGuideOld {
//
//
//    public static void main(String[] args) {
//        //input
//        System.out.println("DNA sequence: ");
//        Scanner scan = new Scanner(System.in);
//        String sequence = scan.nextLine();
//        sequence = sequence.toLowerCase();
//
//        //method
//        //dealing with gn
//        String comb = "";
//        ArrayList<String> guideTarget = new ArrayList<>();
//        ArrayList<String> actualTarget = new ArrayList<>();
//        for (int a = 11; a <= (sequence.length()-11); a++) {
//            if (sequence.charAt(a) == 't') {
//                comb = sequence.substring(a - 11, a + 12);
//                int number = 0;
//                for (int b = 17; b < comb.length(); b++) {
//                    if (comb.charAt(b) == 'g' || comb.charAt(b) == 'c') {
//                        number += 1;
//                    }
//                }
//                if (number >= 4 && number <= 11) {
//                    guideTarget.add(comb.substring(0,19));
//                    actualTarget.add(comb.substring(18));
//                }
//            }
//        }
//
//        //loop for targeted sequence
//
//        for (int a = 0; a < guideTarget.size(); a++) {
//            if (guideTarget.get(a).charAt(0) == 't') {
//                if (guideTarget.get(a).charAt(11) == 'g' || guideTarget.get(a).charAt(11) == 'c') {
//                    guide18.add(guideTarget.get(a).substring(0,19));
//                    target.add(actualTarget.get(a));
//                }
//            }
//        }
//
//        String sequenceTarget = "";
//        for (int a = 18; a <= (sequence.length()-18); a++) {
//            if (sequence.charAt(a) == 'a') {
//                sequenceTarget = sequence.substring(a - 18, a + 19);
//                int number = 0;
//                for (int b = 17; b < sequenceTarget.length(); b++) {
//                    if (sequenceTarget.charAt(b) == 'g' || sequenceTarget.charAt(b) == 'c') {
//                        number += 1;
//                    }
//                }
//                if (number >= 4 && number <= 11) {
//                    guideTarget.add(sequenceTarget.substring(0,19));
//                    actualTarget.add(sequenceTarget.substring(18));
//                }
//            }
//        }
//
//        //loop for targeted sequence
//        ArrayList<String> guide18 = new ArrayList<>();
//        ArrayList<String> target = new ArrayList<>();
//        for (int a = 0; a < guideTarget.size(); a++) {
//            if (guideTarget.get(a).charAt(0) == 't') {
//                if (guideTarget.get(a).charAt(11) == 'g' || guideTarget.get(a).charAt(11) == 'c') {
//                    guide18.add(guideTarget.get(a).substring(0,19));
//                    target.add(actualTarget.get(a));
//                }
//            }
//        }
//
//
//
//
//
//        //position 1 is T
//
//        //position 1 complement to target's position 1 (a)
//
//        //12th position is G or C no A
//
//        //G/C ratio content is 20-65%
//
//        //guide is 16-18 nt
//
//        //target is 18-20 long
//
////        //output
//        System.out.println("Targeted sequence: " + target);
//        System.out.println("DNA guide 18nt");
//        System.out.println("DNA Guide: " + guide18);
//
//    }
//}
