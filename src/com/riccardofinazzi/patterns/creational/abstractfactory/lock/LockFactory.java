package com.riccardofinazzi.patterns.creational.abstractfactory.lock;

import com.riccardofinazzi.patterns.creational.abstractfactory.AbstractFactory;
import com.riccardofinazzi.patterns.creational.abstractfactory.container.Container;

public class LockFactory extends AbstractFactory {

	public Lock getLock( String itemType) {
		String uppercaseItemType = itemType.toUpperCase();
		System.out.println("Factory is creating a container to hold " + uppercaseItemType);
		switch( uppercaseItemType) {
		case "GOLD":
		case "DIAMONDS":
		case "WEAPONS":
			return new SecureLock();
		case "ELECTRONIC DEVICES":
			return new StandardLock();
		default:
			return new DirtyCheapLock();
		}
	}

	@Override public Container getContainer( int l, int w, int h) {
		return null;
	}
}