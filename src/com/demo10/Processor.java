package com.demo10;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// ReEntrantlock is an alternative to using the synchronize keyword
// Without synchronized and lock the only way 2 threads setup to count from 1 to 1000 will reach 2000 is ifone thread finished before the other one
// Reentrant Lock implement the lock interface - one a thread has aquired a lock it can lock it again if it wants to and Renetrant lock keeps track of how long its been locked - 
// you have to unlock the thread the same number of times
// wait() and notify and method of the object class so every object has wait and notify

// There is an equivalent to wait() and notify when using reentrant block and it is await() and signal()
// These two methods are methods of the Condition class
// Condition gets the condition from the lock you are locking on
// You can only call await() and signal() after you have called lock - you need to lock the lock before you wan talk about waiting or signalling
// signalAll() wakes up all waiting thread

// HOW THIS CODE WORKS
// await unlocks the lock in one thread so that another thread can go in and unlock it
// lock will lock the thread and hands over control of the lock using await() and waits
// second thread wakes up after the sleep and it gets the lock
// Calling signal on the second thread wakes up the first thread
// After being woken up with the signal(), it must also get a way to aquire a lock so it can be unlocked


public class Processor {
	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	private void increment() {
		for(int i = 0; i< 10000 ; i++) {
			count++;
		}
	}
	
			
	public void firstThread() throws  InterruptedException {
		
		lock.lock();
		System.out.println("Waiting ...");
		condition.await();
		System.out.println("Woken Up!");
		
		try {
			increment();
			
		} finally {
			
			lock.unlock();
		}
		// Not adding a try block will not work if the method being synchronized throws an exception
		// The solutions is the add the method you want to synchronize in the try block and put the unlock method in a finally block
		
		
	}
	
	void secondThread() throws  InterruptedException {
		
		Thread.sleep(1000);
		lock.lock();
		
		System.out.println("Press return key");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		System.out.println("Got return key!");
		
		condition.signal();
		
		try {
			increment();
			
		} finally {
			
			lock.unlock();
		}
	}
	
	public void finished() {
		System.out.println("Count is: " + count);
	}


}
