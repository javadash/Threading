package com.demo14;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// InterruptedException is a flaw in multithreading design  in Java - you have to catch it at every turn
// There are 2 methods for interrupring threads - Calling Interrupt on Thread and Calling Cancel on Future
// Calling interrupt on a thread set a flag interrupted which can be checked using Thread.currentThread().isInterrupted()
 
public class AppInterrupting {
	
	public static void main(String[] args) {
		
		System.out.println("Starting.");

		
		
		
		
		
		/*		METHOD 2 - INTERRUPT THREAD USING FUTURE.CANCEL()
 * 		WAYS OF TELLING THE THREAD TO STOP RUNNING
 *
 */		
		ExecutorService exec = Executors.newCachedThreadPool();
		Future<?> fu = exec.submit(new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				for(int i=0; i< 1E8; i++) {
					Random ran = new Random();
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						System.out.println("We've been interrupted");
						break;
					}
					
					
					Math.sin(ran.nextDouble());
				}
				return null;
			}
			
		});
		
		
		// Shutdown does not kill the program instead it initiates an orderly shutdown
		// program terminates after thread finished rather then when it still alive
		exec.shutdown();
		try {
			Thread.sleep(500);
			
			
			// cancel will cancel the thread if its already running
			// if set to true it wills et the interrupted flag
			fu.cancel(true); 
			exec.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
 		
		
		
				
				
/*		METHOD 1 - INTERRUPT THREAD
 * 		WAYS OF TELLING THE THREAD TO STOP RUNNING
 * 		USING THREAD.INTERRUPT
 * 
 * 
 * 
 * 
		Thread t = new Thread( new Runnable() {
			
			public void run() {
				for(int i=0; i< 1E8; i++) {
					Random ran = new Random();
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						System.out.println("We've been interrupted");
						break;
					}
					
					
					Math.sin(ran.nextDouble());
				}
				
			}
		});
		
		t.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.interrupt(); // attemots to interrupt the thread
		
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		System.out.println("Finished");
		
	}

}
