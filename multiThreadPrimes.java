import java.util.Arrays;
import java.lang.Math;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class primeList 
{
    int[] nums = new int[100000001];
    int[] maxTenPrimes = new int[10];
    long sum = 0;
    int totalPrimesFound = 0;

    public void sol(int start, int end)
    {
        for(int i=start; i<=end; i++)
        {
            nums[i] = isPrime(i);
        }
    }


    public int isPrime(int num) 
    {
        int temp = 2;
        for(int i=0; i< Math.sqrt(num-1); i++)
        {
            if((num % temp == 0) && (num != temp) && (nums[temp]==1))
            {
                return 0;
            }else{temp++;}
        }
        return 1;
    }

    public void calcSum()
    {
        for(int i=2; i<nums.length; i++)
        {
            if(nums[i] == 1)
            {
                sum += i;
            }
        }
    }

    public void calcTotalPrimesFound()
    {
        for(int i=2; i<nums.length; i++)
        {
            if(nums[i] == 1)
            {
                totalPrimesFound++;
            }
        }
    }

    public void findTenMaxPrimes()
    {
        int value = 9;
        for(int i=(nums.length-1); i>2; i--)
        {
            if(value < 0){break;}
            if(nums[i] == 1)
            {
                maxTenPrimes[value] = i;
                value--;
            }
        }
    }

    public long getSum(){return this.sum;}
    public int getTotalPrimesFound(){return this.totalPrimesFound;}
    public int[] getMaxTenPrimes(){return this.maxTenPrimes;}
}

public class multiThreadPrimes {

    public static void main(String[] args) throws Exception
    {   
        long startTime = System.currentTimeMillis();

        primeList obj1 = new primeList();

        Thread t1 = new Thread(new Runnable()
            {
                public void run()
                {
                    obj1.sol(2, 12500000);
                }
            });
        
        Thread t2 = new Thread(new Runnable()
        {
            public void run()
            {
                obj1.sol(12500001, 25000000);
            }
        });
        Thread t3 = new Thread(new Runnable()
            {
                public void run()
                {
                    obj1.sol(25000001, 37500000);
                }
            });
        
        Thread t4 = new Thread(new Runnable()
        {
            public void run()
            {
                obj1.sol(37500001, 50000000);
            }
        });
        Thread t5 = new Thread(new Runnable()
            {
                public void run()
                {
                    obj1.sol(50000001, 62500000);
                }
            });
        
        Thread t6 = new Thread(new Runnable()
        {
            public void run()
            {
                obj1.sol(62500001, 75000000);
            }
        });
        Thread t7 = new Thread(new Runnable()
            {
                public void run()
                {
                    obj1.sol(75000001, 87500000);
                }
            });
        
        Thread t8 = new Thread(new Runnable()
        {
            public void run()
            {
                obj1.sol(87500001, 100000000);
            }
        });
        
        

        System.out.println("Starting threads");
        t1.start(); t2.start(); t3.start(); t4.start(); t5.start(); t6.start(); t7.start(); t8.start();

        System.out.println("Program is still going...");
        t1.join(); t2.join(); t3.join(); t4.join(); t5.join(); t6.join(); t7.join(); t8.join();
        
        System.out.println("Threads finished being executed. \nPrinting values to primes.txt");
        long endTime = System.currentTimeMillis(); 
        long totalTime = endTime - startTime;
        double timeInSeconds = totalTime / 1000.0;

        obj1.calcSum();
        obj1.calcTotalPrimesFound();
        obj1.findTenMaxPrimes();

        try {
            FileWriter myWriter = new FileWriter("primes.txt");
            myWriter.write("Execution time: " + (timeInSeconds) + "s    Total Primes Found: " + obj1.getTotalPrimesFound() + "    Sum: " + obj1.getSum() );
            myWriter.write("\n");
            int[] maxPrimes = obj1.getMaxTenPrimes();
            for(int i=0; i<10; i++){
                myWriter.write(maxPrimes[i] + " ");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("    Program finished!\n    Check primes.txt");
    }
}
