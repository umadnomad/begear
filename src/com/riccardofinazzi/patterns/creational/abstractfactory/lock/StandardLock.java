package com.riccardofinazzi.patterns.creational.abstractfactory.lock;

public class StandardLock implements Lock {

	@Override public void access() {
		System.out.println("Key required");
	}
}