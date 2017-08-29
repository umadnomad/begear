package com.riccardofinazzi.patterns.creational.abstractfactory.lock;

public class DirtyCheapLock implements Lock {

	@Override public void access() {
		System.out.println("Lever action to open");
	}
}