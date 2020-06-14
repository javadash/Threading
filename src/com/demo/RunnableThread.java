package com.demo;

public class RunnableThread implements Runnable {
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
		Thread learnThreads = new Thread(new RunnableThread());
		
		Thread learnThreads1 = new Thread(new RunnableThread());
		
		
		learnThreads.start();
		learnThreads1.start();
		
		
		
	}

}
