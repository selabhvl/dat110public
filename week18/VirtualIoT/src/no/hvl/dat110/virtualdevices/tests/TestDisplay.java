package no.hvl.dat110.virtualdevices.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import no.hvl.dat110.virtualdevices.display.DisplayActuator;

class TestDisplay {

	@Test
	void test() {
		
		DisplayActuator display = new DisplayActuator();
		
		display.write("TEST MESSAGE");
	}

}
