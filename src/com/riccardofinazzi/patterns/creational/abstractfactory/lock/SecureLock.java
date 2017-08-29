package com.riccardofinazzi.patterns.creational.abstractfactory.lock;

public class SecureLock implements Lock {

	@Override public void access() {
		System.out.println("Iris scan required");
	}
}