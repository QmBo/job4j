package ru.job4j.loop;
 /**
  * @author Victor Egorov (qrioflat@gmail.com).
  * @version 0.1
  * @since 0.1
  */
public class Factorial {

     /**
      * Return factorial of number.
      * @param n number.
      * @return factorial.
      */
    public int calc(int n) {
        int result = 1;
        for (int i = n; i > 1; i--) {
            result *= i;
        }
        return result;
    }
}
