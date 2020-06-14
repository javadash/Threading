package com.demo7;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AppBlockingQueue {
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	
	public static void main(String[] args) {
		// producer is gonna add thinsg as fast as it can
		// When the list/queue is full then Put will wait until an items has been takn of the queue
		// If the queue is empty then take will wait until the list is puopulated to remove an item from the queue
		// This frees you from worrying from thread synchronization
		// Always best to avoid low level synchronization with the synchronized keyword
		// This is more high level
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					producer();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					consumer();
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
	
	private static void producer() throws  InterruptedException {
		Random random = new Random();
		while(true) {
			queue.put(random.nextInt(100));
		}
		
	}
	private static void consumer() throws  InterruptedException {
		Random random = new Random();
		while(true) {
			Thread.sleep(100);
			// We are removing items from the queue once out of every 10 times
			if (random.nextInt(10) == 0) {
				Integer value = queue.take();
				System.out.println("Taken value: " + value + "; Queue size is: " + queue.size());
			}
		}
	}

}
