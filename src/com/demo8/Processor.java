package com.demo8;

import java.util.Random;
import java.util.Scanner;


// synchronized - method will not run until it has aquired the intrinsic lock of the object
// wait() - doesnt consume a lot of system resources, it hands over control of the lock that the synchronized block is locked on, you can only call it within synchronized methods
// Thread that wait has been called on on will not resume until  - must run another thread called/locked on the same object and call a method called notify - must be possible to regain control of the lock 

public class Processor {
	public void produce() throws  InterruptedException {
		synchronized(this) {
			System.out.println("Producer thread running .....!");
			wait();
			System.out.println("Resumed.");
			
		}
	}
	
	// notify can only called within a synchronized code block
	// notify will notify the first thread that is waiting on the lock to wake up 
	void consume() throws  InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		
		synchronized(this){
			System.out.println("Waiting for return key.");
			scanner.nextLine();
			System.out.println("Return key pressed");
			notify();
		}
	}


}
