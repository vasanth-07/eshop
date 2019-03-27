package com.eshop.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.eshop.model.Order;
import com.eshop.service.OrderProcessor;

class OrderControllerTest {

	@Test
	void testEmployeeOrderController() {
		
		String paramList[] = new String[2];
		paramList[0] = "input/employee.csv";
		paramList[1] = "EMPLOYEE";
		
		OrderController orderController = new OrderController();
		Order finalOrder = orderController.generateOrder(paramList);
		
		
		assertEquals(585.91, finalOrder.getFinalAmount(), "Final Bill amount should be $585.91");
		
	}
	
	@Test
	void testAffiliateOrderController() {
		
		String paramList[] = new String[2];
		paramList[0] = "input/affiliate.csv";
		paramList[1] = "AFFILIATE";
		
		OrderController orderController = new OrderController();
		Order finalOrder = orderController.generateOrder(paramList);
		
		
		assertEquals(717.36, finalOrder.getFinalAmount(), "Final Bill amount should be $717.36");
		
	}
	
	@Test
	void testLoyalistOrderController() {
		
		String paramList[] = new String[2];
		paramList[0] = "input/loyalist.csv";
		paramList[1] = "LOYALIST";
		
		OrderController orderController = new OrderController();
		Order finalOrder = orderController.generateOrder(paramList);
		
		
		assertEquals(750.22, finalOrder.getFinalAmount(), "Final Bill amount should be $750.22");
		
	}
	
	@Test
	void testComboOrderController() {
		
		String paramList[] = new String[3];
		paramList[0] = "input/loyalist.csv";
		paramList[1] = "LOYALIST";
		paramList[2] = "EMPLOYEE";
		
		OrderController orderController = new OrderController();
		Order finalOrder = orderController.generateOrder(paramList);
		
		
		assertEquals(585.91, finalOrder.getFinalAmount(), "Final Bill amount should be $585.91");
		
	}
	
	@Test
	void testOrderController() {
		
		String paramList[] = new String[2];
		paramList[0] = "";
		paramList[1] = "";
		
		
		OrderController orderController = new OrderController();
		Order finalOrder = orderController.generateOrder(paramList);
		
		
		assertEquals(1335.28, finalOrder.getFinalAmount(), "Final Bill amount should be $1335.28");
		
	}

}
