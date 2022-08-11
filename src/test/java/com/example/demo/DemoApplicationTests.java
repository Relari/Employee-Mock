package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DemoApplicationTests {

	private DemoController demoController;

	@BeforeEach
	void init() {
		demoController = new DemoController();
	}

	@Test
	void contextLoads() {
		assertEquals("Hello World", demoController.greeting());
	}

}
