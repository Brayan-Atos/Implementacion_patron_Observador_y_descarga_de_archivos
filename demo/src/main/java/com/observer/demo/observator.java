package com.observer.demo;

public class observator implements Iobservator{

	@Override
	public void update(String s) {
		System.out.println("Download the "+ s +" file..........." );
		
	}

}
