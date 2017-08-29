package com.riccardofinazzi.patterns.creational.factory;

interface Container {

	void access();
}

class SecureContainer implements Container {

	@Override public void access() {
		System.out.println("Iris scan required");
	}
}

class StandardContainer implements Container {

	@Override public void access() {
		System.out.println("Key required");
	}
}

class DirtyCheapContainer implements Container {

	@Override public void access() {
		System.out.println("Lever action to open");
	}
}

class ContainerFactory {

	public static Container getContainer( String itemType) {
		String uppercaseItemType = itemType.toUpperCase();
		System.out.println("Factory is creating a container to hold " + uppercaseItemType);
		switch( uppercaseItemType) {
		case "GOLD":
		case "DIAMONDS":
		case "WEAPONS":
			return new SecureContainer();
		case "ELECTRONIC DEVICES":
			return new StandardContainer();
		default:
			return new DirtyCheapContainer();
		}
	}
}

public class FactoryPatternDemo {

	public static void main( String[] args) {
		Container c1 = ContainerFactory.getContainer("gold");
		Container c2 = ContainerFactory.getContainer("diamonds");
		Container c3 = ContainerFactory.getContainer("electronic devices");
		Container c4 = ContainerFactory.getContainer("stuff");

		Container[] arr = { c1, c2, c3, c4 };
		for( Container in : arr) {
			System.out.println("-----");
			System.out.println(in.getClass());
			in.access();
		}
	}
}
