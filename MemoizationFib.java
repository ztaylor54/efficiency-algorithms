/*
This program uses memoization (https://en.wikipedia.org/wiki/Memoization) to optimize the fibonacci sequence's
time complexity. It can handle any int `n` so long as the JVM has a large enough stack.
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class MemoizationFib {
    private static ArrayList<String> computed = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean goodInput = false;
        int n = 0;
        while (!goodInput) {
            try {
                System.out.print("Enter n: ");
                n = Integer.parseInt(scan.next());
                if(n < 0)
                    throw new Exception();
                goodInput = true;
                System.out.println();
            } catch (Exception e) {
                System.err.println("Invalid input. Please enter a positive integer.");
            }
        }

        long startTime = System.nanoTime(); //time how long it takes
        BigInteger result = fib(n);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; //get milliseconds

        System.out.println("Fibonacci(" + n + ") = " + result.toString() + "\nExecution took " + duration + " milliseconds");
    }

    public static BigInteger fib(int n) {
        if(n == 0 || n == 1) { //base case
            return BigInteger.valueOf(n);
        } else {
             //do the calculation
            String[] check = checker(n);
            if (Boolean.parseBoolean(check[0])) {
                return new BigInteger(computed.get(Integer.parseInt(check[1])).split(":")[1]);
            } else
            {
                BigInteger f = (fib(n-1).add(fib(n-2)));
                computed.add(n + ":" + f);
                return f;
            }
        }
    }

    public static String[] checker(int n) {
        String[] result = {"", ""};
        for(String s : computed) {
            if (Integer.parseInt(s.split(":")[0]) == n) { //if we've already computed this `n`
                result[0] = "true";
                result[1] = computed.indexOf(s) + "";
                return result;
            } else {
                result[0] = "false";
                result[1] = "-1";
            }
        }
        return result; //return whether we've computed it, and if we have, return the index
    }
}

/*
Copyright (C) 2016  Zachary Taylor | ztaylor54@gmail.com

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
