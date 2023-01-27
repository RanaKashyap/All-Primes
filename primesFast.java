import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;

class primeList {
    // 21093744 is the max number of digits a thread has to calculate for prime
    int[] nums = new int[21093744];
    int[] maxTenPrimes = new int[10];
    int start = 0, end = 0;
    long sum = 0;
    int totalPrimeNum = 0;

    // when spawning threads they are given two values, which is their range
    // they are to find all prime numbers in that range
    // if a number is prime then store 1 and 0 if not a prime.
    // no lock or synchronized keyword is required since all threads have their own objects
    // and they never try to access the same method.
    public void sol(int start, int end) {
        this.end = end;
        this.start = start;
        for (int i = 0; i < (end - start); i++) {
            nums[i] = isPrime(i + start);
        }
    }

    // a number is a prime if it is not divisible by any number less than the 
    // sqrt of itself minus 1.
    // this method also keeps track of sum and the total number of primes.
    public int isPrime(int num) {
        int temp = 2;
        for (int i = 0; i < Math.sqrt(num - 1); i++) {
            if ((num % temp == 0) && (num != temp)) {
                return 0;
            } else {
                temp++;
            }
        }
        sum += num;
        totalPrimeNum++;
        return 1;
    }

    // since we are looking for ten max primes, this method looks for prime
    // numbers starting from its max range and decrementing until ten numbers are identified.
    public void findTenMaxPrimes() {
        int value = 9;
        for (int i = (end - start); i > 0; i--) {
            if (value < 0) {
                break;
            }
            if (nums[i] == 1) {
                maxTenPrimes[value] = i + start;
                value--;
            }
        }
    }

    // get methods to access values.
    public long getSum() {
        return this.sum;
    }

    public int getTNOF() {
        return this.totalPrimeNum;
    }

    public int[] getMaxTenPrimes() {
        return this.maxTenPrimes;
    }
}

public class primesFast {

    public static void main(String[] args) throws Exception {
        // Creating eight objects of primelist for each thread.
        primeList obj1 = new primeList();
        primeList obj2 = new primeList();
        primeList obj3 = new primeList();
        primeList obj4 = new primeList();
        primeList obj5 = new primeList();
        primeList obj6 = new primeList();
        primeList obj7 = new primeList();
        primeList obj8 = new primeList();

        // Creating eight thread objects with runnable object as a parameter.
        // values range from 2 to 10^8.
        // values is evenly distributed between threads based on executiontime.
        // all thread take 9-10 secs to finish computation
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                obj1.sol(2, 21093743);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                obj2.sol(21093744, 35156244);
            }
        });
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                obj3.sol(35156245, 48437495);
            }
        });
        Thread t4 = new Thread(new Runnable() {
            public void run() {
                obj4.sol(48437496, 60156246);
            }
        });
        Thread t5 = new Thread(new Runnable() {
            public void run() {
                obj5.sol(60156247, 71093747);
            }
        });
        Thread t6 = new Thread(new Runnable() {
            public void run() {
                obj6.sol(71093748, 81249998);
            }
        });
        Thread t7 = new Thread(new Runnable() {
            public void run() {
                obj7.sol(81249999, 90624999);
            }
        });
        Thread t8 = new Thread(new Runnable() {
            public void run() {
                obj8.sol(90625000, 100000000);
            }
        });

        // tim calculation to understand how long it takes to identify all prime numbers from 1 to 10^8.
        long startTime = System.nanoTime();

        // starting all eight threads.
        t1.start();t2.start();t3.start();t4.start();t5.start();t6.start();t7.start();t8.start();

        // .join allows the main thread to wait untill all eight threads have finished computation.
        t1.join();t2.join();t3.join();t4.join(); t5.join(); t6.join(); t7.join(); t8.join();

        long endTime = System.nanoTime();
        // find how much time the operation took.
        long totalTime = endTime - startTime;
        // convert nanoseconds to seconds.
        double timeInSeconds = totalTime / 1_000_000_000.0;

        // get the sum of all primes found from each object.
        long totalSum = obj1.getSum() + obj2.getSum() + obj3.getSum() + obj4.getSum() + obj5.getSum() + obj6.getSum()
                + obj7.getSum() + obj8.getSum();
        // get the total number of primes found from each object.
        long totalPrimes = obj1.getTNOF() + obj2.getTNOF() + obj3.getTNOF() + obj4.getTNOF() + obj5.getTNOF()
                + obj6.getTNOF() + obj7.getTNOF() + obj8.getTNOF();
        // since thread 8 had the highest values, we are to find our top ten max primes there
        // run the method to find max primes for obj8 which corresponds to thread 8
        obj8.findTenMaxPrimes();
        try {
            // print results to primes.txt file
            FileWriter myWriter = new FileWriter("primes.txt");
            myWriter.write(timeInSeconds + "    " + totalPrimes + "    " + totalSum);
            myWriter.write("\n");
            int[] maxPrimes = obj8.getMaxTenPrimes();
            for (int i = 0; i < 10; i++) {
                myWriter.write(maxPrimes[i] + " ");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
