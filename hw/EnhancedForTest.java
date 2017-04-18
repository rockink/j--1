import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.lang.Iterable;
import java.lang.System;

/**
 * Created by rockink on 4/16/17.
 */
public class EnhancedForTest {

    public static void main(String[] args){

        double[] d = new double[2];
        d[0] = 1.1;
        d[1] = 2.1;




//        ArrayList c = new ArrayList();
//        c.add((Object) "4");
//        c.add((Object) "4");
//        c.add((Object) "4");
//        c.add((Object) "4");
//
//        for (int i = 0; i < d.length; i++) {
//            double each = d[i];
//            System.out.println(each);
//
//        }
//
//

        System.out.println("enhanedfor");
        for (double each : d) {
            System.out.println(each);
        }


//


//        for (Object each : c) {
//            System.out.println(each);
//        }


    }

}
