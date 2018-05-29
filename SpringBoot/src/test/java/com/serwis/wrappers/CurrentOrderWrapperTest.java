package com.serwis.wrappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by jakub on 23.05.2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CurrentOrderWrapperTest {
	@Test
	public void calculateValue()  {
		int quantity =5;
		double price =19.99;
		double totalValue = quantity * price;
		totalValue= Math.round(totalValue*100.0)/100.0;
		assertEquals(99.95,totalValue,0.001);
	}

}