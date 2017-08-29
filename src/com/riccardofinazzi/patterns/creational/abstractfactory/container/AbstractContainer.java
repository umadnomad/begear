package com.riccardofinazzi.patterns.creational.abstractfactory.container;

abstract class AbstractContainer implements Container {

	protected Size size;

	public Size getSize() {
		return size;
	}
}