package com.demo12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


// Semaphore used to limit resources
// In this example we are limit the number of connections

public class AppSemaphore {
	public static void main(String[] args) throws Exception {
		Connection.getInstance().connect();
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i = 0; i <200; i++) {
			executor.submit(new Runnable() {
				public void run() {
					Connection.getInstance().connect();
				}
			});
		}
		
		executor.shutdown(); // shutdown all threads when they have finished running
		
		executor.awaitTermination(1, TimeUnit.DAYS); // wait until they have all finished running
	}

}
