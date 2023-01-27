import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;

class primeList {
    int[] nums = new int[21093744];
    int[] maxTenPrimes = new int[10];
    int start = 0, end = 0;
    long sum = 0;
    int totalPrimeNum = 0;

    public synchronized void sol(int start, int end) {
        this.end = end;
        this.start = start;
        for (int i = 0; i < (end - start); i++) {
            nums[i] = isPrime(i + start);
        }
    }

    public synchronized int isPrime(int num) {
        int temp = 2;
        for (int i = 0; i < Math.sqrt(num + 1); i++) {
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
        primeList obj1 = new primeList();
        primeList obj2 = new primeList();
        primeList obj3 = new primeList();
        primeList obj4 = new primeList();
        primeList obj5 = new primeList();
        primeList obj6 = new primeList();
        primeList obj7 = new primeList();
        primeList obj8 = new primeList();

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

        long startTime = System.nanoTime();
        t1.start();t2.start();t3.start();t4.start();t5.start();t6.start();t7.start();t8.start();
        t1.join();t2.join();t3.join();t4.join(); t5.join(); t6.join(); t7.join(); t8.join();
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        double timeInSeconds = totalTime / 1_000_000_000.0;

        long totalSum = obj1.getSum() + obj2.getSum() + obj3.getSum() + obj4.getSum() + obj5.getSum() + obj6.getSum()
                + obj7.getSum() + obj8.getSum();
        long totalPrimes = obj1.getTNOF() + obj2.getTNOF() + obj3.getTNOF() + obj4.getTNOF() + obj5.getTNOF()
                + obj6.getTNOF() + obj7.getTNOF() + obj8.getTNOF();
        obj8.findTenMaxPrimes();
        try {
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
