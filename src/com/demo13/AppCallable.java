package com.demo13;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Callable interface is a parameterized class
// The type Callable takes in the type you want to return from your running Thread
// Future & Callable allow you to get return results from threads and allow Threads to throw exceptions
// Future allows you to get the result from your running thread
// Future also has some method that allow you to cancel you thread
// There is a method that tell you whether you thread has finished or not
// Setting void is as parameter for Callable does not return a value of


public class AppCallable {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		Future<Integer> future = executor.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception{
				Random random = new Random();
				int duration = random.nextInt(4000);
				
				if(duration > 2000) {
					throw new IOException("Sleeping for too long");
				}
				System.out.println("Starting ....");
				
				try{
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Finished.");
				
				return duration;
			}
		});
		
		executor.shutdown();
		
		// Before calling get you could call awaitTermination on the eexecutor in order to wait for the Thread to finish
		// If you dont call await Termination Future.get() will block and wait for your execution to finish
 		
		try {
			System.out.println("Result is: " + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.print(e);
			IOException ex = (IOException)e.getCause(); // used the get the cause of the exception
			System.out.println(ex.getMessage());
		}
	}
}
