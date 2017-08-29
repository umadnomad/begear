package com.riccardofinazzi.patterns.creational.abstractfactory.container;

import com.riccardofinazzi.patterns.creational.abstractfactory.AbstractFactory;
import com.riccardofinazzi.patterns.creational.abstractfactory.lock.Lock;

public class ContainerFactory extends AbstractFactory {

	public Container getContainer( int l, int w, int h) {
		int volume = l * w * h;
		System.out.println("ContainerFactory is creating a container capable of storing " + volume + " m3");
		if( volume > 50)
			return new BigContainer();
		else if( volume < 10) {
			return new SmallContainer();
		} else {
			return new MediumContainer();
		}
	}

	@Override public Lock getLock( String itemType) {
		return null;
	}
}