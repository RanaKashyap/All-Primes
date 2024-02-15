# All-Primes


compile a .java file:  
locate the directory where the file is located  
type ***"javac primes.java"***  
then type ***"Java primes"***  
the program will start to compile.

When attempting to tackle this problem, I was not sure where to begin as I had no prior experience with multithreading.
So I decided to find primes using the square root algorithm.  
Let's showcase an example to better understand the technique used to identify id a number is prime.  
Suppose you are wondering if 20 is a prime number, you would start from 2 and increment as you go to see if it is divisible by any until you reach the square root of (20-1) which would be the square root of 19. But during the first check, you can see 20 is divisible by 2 so it can not be a prime.

I implemented this technique into a method which was in a class called primeList. Then created eight primeList objects for each thread. While doing this I found it to be inefficient but could not figure out any other way to have threads run in parallel. I divided the numbers into eight threads based on run times. I tried to make sure all work was evenly divided between threads. 

While this approach was not the best and more optimizations could have been made to lower runtime, it satisfied the assignment requirements. However, I am not satisfied with the result so I aim to improve my knowledge of multithreading and improve my results.
