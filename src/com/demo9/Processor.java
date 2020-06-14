package com.demo9;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;


// This is doing the same thing as lesson 7 but its more low level using synchronized and wait and notfiy and the collection is not thread safe

// Here we show how to share data within threads

// Wait notify for sharing data between threads

// synchronized - method will not run until it has aquired the intrinsic lock of the object
// wait() - doesnt consume a lot of system resources, it hands over control of the lock that the synchronized block is locked on, you can only call it within synchronized methods
// Thread that wait has been called on on will not resume until  - must run another thread called/locked on the same object and call a method called notify - must be possible to regain control of the lock 
// You need to call wait and notify on the object that you're locking on
public class Processor {
	private LinkedList<Integer> list  = new LinkedList<>();
	private final int LIMIT = 10;
	
	private Object lock = new Object();
	
 	public void produce() throws  InterruptedException {
		int value = 0;
		while (true) {
			synchronized(lock) {
				while(list.size() == LIMIT) {
					lock.wait();
				}
				list.add(value++);
				lock.notify();
			}
		}
	}
	
	// notify can only called within a synchronized code block
	// notify will notify the first thread that is waiting on the lock to wake up 
	void consume() throws  InterruptedException {
		while(true) {
			synchronized(lock) {
				
				while(list.size() == 0) {
					lock.wait();
				}
				System.out.print("List size is: " + list.size());
				int value = list.removeFirst();
				System.out.println("; value is: " + value);
				lock.notify();
			}
			Thread.sleep(new Random().nextInt(1000));
		}
	}


}
