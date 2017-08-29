package com.riccardofinazzi.patterns.creational.abstractfactory;

import com.riccardofinazzi.patterns.creational.abstractfactory.container.Container;
import com.riccardofinazzi.patterns.creational.abstractfactory.lock.Lock;

public class AbstractFactoryPatternDemo {

	public static void main( String[] args) {

		AbstractFactory containerFactory = FactoryProducer.getFactory("CONTAINER");

		Container c1 = containerFactory.getContainer(3, 2, 1);
		Container c2 = containerFactory.getContainer(4, 3, 2);
		Container c3 = containerFactory.getContainer(5, 4, 3);

		Container[] containerArr = { c1, c2, c3 };
		for( Container in : containerArr) {
			System.out.println("-----");
			System.out.println(in.getClass());
			System.out.println(in.getSize());
		}

		System.out.println("-----");

		AbstractFactory lockFactory = FactoryProducer.getFactory("LOCK");

		Lock l1 = lockFactory.getLock("gold");
		Lock l2 = lockFactory.getLock("diamonds");
		Lock l3 = lockFactory.getLock("electronic devices");
		Lock l4 = lockFactory.getLock("stuff");

		Lock[] lockArr = { l1, l2, l3, l4 };
		for( Lock in : lockArr) {
			System.out.println("-----");
			System.out.println(in.getClass());
			in.access();
		}
	}
}