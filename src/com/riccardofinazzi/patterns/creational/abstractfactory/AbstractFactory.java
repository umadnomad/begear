package com.riccardofinazzi.patterns.creational.abstractfactory;

import com.riccardofinazzi.patterns.creational.abstractfactory.container.Container;
import com.riccardofinazzi.patterns.creational.abstractfactory.lock.Lock;

public abstract class AbstractFactory {

	public abstract Container getContainer( int l, int w, int h);

	public abstract Lock getLock( String itemType);
}