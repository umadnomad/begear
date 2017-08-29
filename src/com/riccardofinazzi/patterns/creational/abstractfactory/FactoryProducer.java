package com.riccardofinazzi.patterns.creational.abstractfactory;

import com.riccardofinazzi.patterns.creational.abstractfactory.container.ContainerFactory;
import com.riccardofinazzi.patterns.creational.abstractfactory.lock.LockFactory;

public class FactoryProducer {

	public static AbstractFactory getFactory( String choice) {

		switch(choice.toUpperCase()) {
		case "CONTAINER":
			return new ContainerFactory();
		case "LOCK":
			return new LockFactory();
		default:
			return null;
		}
	}
}