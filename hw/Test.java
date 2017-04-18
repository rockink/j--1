import java.lang.System;
import java.lang.Iterable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by rockink on 3/28/17.
 */
public class Test {

    public static void main(String[] args) {

        int i = 0;

//        asd:
//        if(i > 2){
//            i++;
//            if(i > 4)
//                break asd;
//            i++;
//            for(int k = 3; k < 6; k++) break asd;
//            i++;
//        }


        int j = 4;


        System.out.println("this is j " + j);
        System.out.println("this is j " + i);

        boolean foundIt = false;

        search:
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 6; j++) {
                if (i == 3 && j == 4) {
                    foundIt = true;
                    break search;
                }
            }

            System.out.println("This is supposed run only 3 times");
        }



        for (i = 0; i < 5; i++) {
            for (j = 0; j < 6; j++) {
                if (i == 3 && j == 4) {
                    foundIt = true;
                    break;
                }
            }

            System.out.println("This is supposed to run 5 times though");
        }



        if (foundIt) {
            System.out.println("Found " + " at " + i + ", " + j);
        } else {
            System.out.println(" not in the array");
        }

        //        list.add((Object) "i");
//        list.add((Object) "r");
//        list.add((Object) "r");
//        list.add((Object) "r");
//        list.add((Object) "r");
//        list.add((Object) "r");


//        list.add("asda");

//        for(Object d : arrayList){
//            System.out.println(d);
//        }

//        double d = 5.2;
//        addEqual(d);
//        minusEqual(d);
//        mulEqual(d);
//        divEqual(d);
//        remEqual(d);
//        prefixPostfixTest(10);

//        conditionalTest();

//        int i = 5;
//
//        if (i > 3 || i == 5) System.out.println("i greater or 3 true");
//        if (i > 3 && i == 5) System.out.println("i greater and 3 true");
//        if (i < 3) System.out.println("i less than 3 true");
//        if (i == 3) System.out.println("i less than 3 true");
//        else System.out.println("okay");
//
//
//        do {
//            System.out.println("printing i  " + nirmal);
//            i--;
//        }while (i > 1);


//        System.out.println("final" + d);

    }

//    private static void conditionalTest() {
//        int i = 5;
//
//        if (i > 3 || i == 5) System.out.println("i greater or 3 true");
//        if (i > 3 && i == 5) System.out.println("i greater and 3 true");
//        if (i < 3) System.out.println("i less than 3 true");
//        if (i == 3) System.out.println("i less than 3 true");
//        else System.out.println("okay");
//
//
//    }
//
//    private static void prefixPostfixTest(int i) {
//        System.out.println("initially i " + i);
//        for(; i < 3; i++);
//        System.out.println("after increasing i " + i);
//        for(int j = 0; j < 3; j++) i--;
//        System.out.println("postfix prefix " + i);
//    }
//
//
//    private static void remEqual(double d) {
//        double j = 1.0;
//        for(int i = 0; i < 3; i++){
//            d %= j++;
//        }
//
//        System.out.println("remainder " + d);
//    }
//
//    private static void divEqual(double d) {
//        d = 8.0;
//        for(int i = 0; i < 3; i++){
//            d /= 2.1;
//        }
//
//        System.out.println("divequal " + d);
//    }
//
//    private static void mulEqual(double d) {
//        for(int i = 0; i < 3; i++){
//            d *= d;
//        }
//        System.out.println("mul Equal ans" + d);
//    }
//
//    private static void minusEqual(double d) {
//
//        double k = -5.5;
//        for(int i = 0; i < 5; i++){
//            d -= k;
//            System.out.println(d);
//        }
//        System.out.println("-= ans" + d);
//
//    }
//
//    private static void addEqual(double d) {
//        double j = 5.0;
//
//        for(int i = 0; i < 5; i++){
//            d += j;
//            System.out.println(d);
//        }
//
//        System.out.println("+=" + d);
//    }

}
