package com.demo2;

import java.util.Scanner;


// Method to shutdown thread gracefully
public class Processor extends Thread {
	private volatile boolean running = true; // volatile used to mark a Java variable as "being stored in main memory
	public void run() {
		while (running) {
			System.out.println("Hello");
			
			try {
				Thread.sleep(100);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		running = false;
	}
	
	
	public static void main (String[] args) {
		Processor proc1 = new Processor();
		proc1.start();
		
		Scanner input = new Scanner(System.in);
		input.nextLine();
		
		proc1.shutdown();
	}

}
