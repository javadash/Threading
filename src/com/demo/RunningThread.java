package com.demo;

public class RunningThread extends Thread {
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println("Hello " + i);
			
			try {
				Thread.sleep(100);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		RunningThread learnThreads = new RunningThread();
		learnThreads.start();
		
		
		RunningThread learnThreads1 = new RunningThread();
		learnThreads1.start();
	}
}
