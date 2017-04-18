import java.lang.System;
import java.lang.Iterable;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by rockink on 3/28/17.
 *
 */
public class Test2 {

    public static void main(String[] args) {
        double[] array = new double[3];
        array[0] = 4.4;
        array[1] = 4.423;
        array[2] = 4.412312;


        for (int i = 0; i < array.length; i++) {
            double each = array[i];
            System.out.println(each);
        }
        System.out.println(array[0]);
    }

}
