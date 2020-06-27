package com.demo3;


// Synchronized - you dont need to declare a variable as volative and running something in synchronized block guarantees the variable is visible to all threads
// Every object in java has an intrinsic lock - monitor lock
// To call a method synchronized - you have to aquire the intrinsic lock - only on threead can aquire the in intrinisc lock at a time

// Join method on thread is used to wait for the process/thread to finish before you can can get meaningful results and continue processing

public class AppSynchronized {
	private int count = 0;
	
	public synchronized void increment() {
		count++;
	}
	
	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10000; i++) {
					increment();
				}
			}
		});
		
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10000; i++) {
					increment();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Count is : " + count);
	}
	
	public static void main (String[] args) {
		AppSynchronized app = new AppSynchronized();
		
		app.doWork();
	
	}

}
