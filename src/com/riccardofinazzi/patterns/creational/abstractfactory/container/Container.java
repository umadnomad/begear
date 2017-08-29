package com.riccardofinazzi.patterns.creational.abstractfactory.container;

public interface Container {

	enum Size {
		BIG, MEDIUM, SMALL
	}

	Size getSize();
}
