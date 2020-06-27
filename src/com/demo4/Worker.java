package com.demo4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Beware of the object that the synchronized object is called on
// It may be called on the main class object which means there is only one lock and another threads will have to wait to aquire the lock the same lock even if they are different methods
// You can synchronize on methods to solve this 
// So synchronised methods vs Synchronized objects
// In a class with multiple methods havbing multiple lock will prevent not 2 object from locking an object at the same time

public class Worker {
	
	private Object lock1 = new Object();
	private Object lock2 = new Object(); 
	
	private List<Integer> list1 =  new ArrayList<>();
	private List<Integer> list2 =  new ArrayList<>();
	Random random = new Random();
	
	public void stageOne() {
		synchronized(lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			list1.add(random.nextInt(100));
		}
	}
	
	public void stageTwo() {
		synchronized(lock2) {
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			list2.add(random.nextInt(100));
		}
	}
	public void process() {
		for(int i=0; i <1000; i++) {
			stageOne();
			stageTwo();
		}
	}
	
	
	public void main() {
		System.out.println("Starting ...");
		long start = System.currentTimeMillis();
		Thread t1 = new Thread(new Runnable () {
			
			public void run() {
				process();
			}
		});
		
		Thread t2 = new Thread(new Runnable () {
			
			public void run() {
				process();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time taken: " + (end - start));
		System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
	}

}
