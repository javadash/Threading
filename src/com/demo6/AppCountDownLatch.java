package com.demo6;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class Processor implements Runnable {
	private CountDownLatch latch;
	public Processor (CountDownLatch latch) {
		this.latch = latch;
	}
	
	public void run() {
		try {
			Thread.sleep(3000);;
		} catch( InterruptedException e) {
			e.printStackTrace();
		}
		
		latch.countDown();
	}
}



public class AppCountDownLatch {
	
	
	// CountDownLatch allows you to count down from a particular number you specify
	// Lets one or more threads wait until you reach a countdown of 0
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3);
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for(int i = 0; i<3; i++) {
			executor.submit(new Processor(latch));
		}
		
		try {
			latch.await();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
