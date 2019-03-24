package com.eshop.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.eshop.model.Order;
import com.eshop.model.Product;
import com.eshop.model.User;
import com.eshop.model.OrderItem;;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderProcessorTest.
 */
class OrderProcessorTest {
	
	/**
	 * Test employee order.
	 */
	@Test
	final void testEmployeeOrder() {
		
		Order order = this.createOrder();
		
		order.getUser().setUserType(User.USER_TYPE.EMPLOYEE);
		
		OrderProcessor orderProcessor = new OrderProcessor();
		Order finalOrder = orderProcessor.processOrder(order);
		
		
		assertEquals(585.91, finalOrder.getFinalAmount(), "Final Bill amount should be $585.91");
		
	}

	/**
	 * Test affiliate order.
	 */
	@Test
	final void testAffiliateOrder() {
		
		Order order = this.createOrder();
		
		order.getUser().setUserType(User.USER_TYPE.AFFILIATE);
		
		OrderProcessor orderProcessor = new OrderProcessor();
		Order finalOrder = orderProcessor.processOrder(order);
		
		
		assertEquals(717.36, finalOrder.getFinalAmount(), "Final Bill amount should be $717.36");
		
	}
	
	/**
	 * Test loyalist order.
	 */
	@Test
	final void testLoyalistOrder() {
		
		Order order = this.createOrder();
		
		
		
		String regDate = "2017-01-16";
		Date registrationDate = null;
		
	    try {
	    		registrationDate = new SimpleDateFormat("yyyy-MM-dd").parse(regDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		order.getUser().setRegistrationDate(registrationDate);
		
		OrderProcessor orderProcessor = new OrderProcessor();
		Order finalOrder = orderProcessor.processOrder(order);
		
		
		assertEquals(750.22, finalOrder.getFinalAmount(), "Final Bill amount should be $750.22");
		
	}
	
	/**
	 * Test combo order.
	 */
	@Test
	final void testComboOrder() {
		
		Order order = this.createOrder();
		
		order.getUser().setUserType(User.USER_TYPE.EMPLOYEE);
		
		String regDate = "2017-01-16";
		Date registrationDate = null;
		
	    try {
	    		registrationDate = new SimpleDateFormat("yyyy-MM-dd").parse(regDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		order.getUser().setRegistrationDate(registrationDate);
		
		OrderProcessor orderProcessor = new OrderProcessor();
		Order finalOrder = orderProcessor.processOrder(order);
		
		
		assertEquals(585.91, finalOrder.getFinalAmount(), "Final Bill amount should be $585.91");
		
	}
	
	/**
	 * Creates the order.
	 *
	 * @return the order
	 */
	private Order createOrder()
	{
		Order order = null;
		
		order = new Order();
		
		double totalAmount = 0.0;
		
		User user = new User();
		
		order.setOrderId("Order1");
		
		user.setCustId("1234");
		user.setFirstName("Jack");
		user.setLastName("Sparrow");
		user.setAddress("address");
		user.setUserType(User.USER_TYPE.NONE);
		user.setRegistrationDate(new Date());
		user.setLastTransactedDate(new Date());
		
		order.setUser(user);
		
		Product p1 = new Product();
		p1.setProductId("p1");
		p1.setName("Milk Powder");
		p1.setDescription("InfaGrow Milk Powder");
		p1.setProductType(Product.PRODUCT_TYPE.GROCERY);
		p1.setUnitPrice(80.30);
		
		Product p2 = new Product();
		p2.setProductId("p2");
		p2.setName("Cabbage");
		p2.setDescription("Australian Cabbage");
		p2.setProductType(Product.PRODUCT_TYPE.GROCERY);
		p2.setUnitPrice(1.05);
		
		Product p3 = new Product();
		p3.setProductId("p3");
		p3.setName("Home Theatre");
		p3.setDescription("Onkyo Home Theatre 7.1");
		p3.setProductType(Product.PRODUCT_TYPE.ELECTRONICS);
		p3.setUnitPrice(650);
		
		Product p4 = new Product();
		p4.setProductId("p4");
		p4.setName("Eye Drops");
		p4.setDescription("ClearView Eye Drops");
		p4.setProductType(Product.PRODUCT_TYPE.MEDICAL);
		p4.setUnitPrice(7.23);
		
		ArrayList<OrderItem> itemList = new ArrayList<OrderItem>();
		
		OrderItem item1 = new OrderItem();
		item1.setProduct(p1);
		item1.setQuantity(2);
		item1.setItemCost(item1.getQuantity()*p1.getUnitPrice());
		itemList.add(item1);
		totalAmount += item1.getQuantity()*p1.getUnitPrice();
		
		OrderItem item2 = new OrderItem();
		item2.setProduct(p2);
		item2.setQuantity(5);
		item2.setItemCost(item2.getQuantity()*p2.getUnitPrice());
		itemList.add(item2);
		totalAmount += item2.getQuantity()*p2.getUnitPrice();
		
		OrderItem item3 = new OrderItem();
		item3.setProduct(p3);
		item3.setQuantity(1);
		item3.setItemCost(item3.getQuantity()*p3.getUnitPrice());
		itemList.add(item3);
		totalAmount += item3.getQuantity()*p3.getUnitPrice();
		
		OrderItem item4 = new OrderItem();
		item4.setProduct(p4);
		item4.setQuantity(1);
		item4.setItemCost(item4.getQuantity()*p4.getUnitPrice());
		itemList.add(item4);
		totalAmount += item4.getQuantity()*p4.getUnitPrice();
		
		order.setItemList(itemList);
		order.setTotalAmount(totalAmount);
		order.setDiscountAmount(0.0);
		order.setFinalAmount(totalAmount);
		
		return order;
	}

	
}
