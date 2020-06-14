package com.demo9;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AppWaitNotify {
	public static void main(String[] args) {
		// producer is gonna add thinsg as fast as it can
		// When the list/queue is full then Put will wait until an items has been takn of the queue
		// If the queue is empty then take will wait until the list is puopulated to remove an item from the queue
		// This frees you from worrying from thread synchronization
		// Always best to avoid low level synchronization with the synchronized keyword
		// This is more high level
		
		
		Processor process = new Processor ();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					process.produce();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					process.consume();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join(); //wait until the thread finishes
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
