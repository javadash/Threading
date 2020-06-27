package com.demo12;

import java.util.concurrent.Semaphore;

public class Connection {
	private int connections = 0;
	private static Connection instance = new Connection(); 
	private Semaphore sem = new Semaphore(10); // used to limit the number of connection
	// To make a connection you have to aquire one fo these permits
	// When one connection is finished it will release the permit and another connection can aquire a connection
	// Sermaphore object constructor also have a fairness parameter - by adding the - true - argument to a semaphore
	// whichever thread that calls aquire first will be first one to get a permit when it becomes available
	private Connection() {
	}
	public static Connection getInstance() {
		return instance;
	}
	
	public void connect() {
		try {
			sem.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		
		// this block of code handles a case where making a connection do connect throws an exception
		// even if doconnect throws an exception then we will be able to release a permit
		try {
			doConnect();
		} finally {
			sem.release();
		}
	}
	
	public void doConnect() {
		
		
		
		synchronized (this) {
			connections++;
			System.out.println("Current connection: " + connections);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (this) {
			connections--;
		}
	}
}
