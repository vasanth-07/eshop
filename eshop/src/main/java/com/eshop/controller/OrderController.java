package com.eshop.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.eshop.model.Order;
import com.eshop.model.OrderItem;
import com.eshop.model.Product;
import com.eshop.model.User;
import com.eshop.service.OrderProcessor;
import com.eshop.util.LogFormatter;


/**
 * OrderController Class - Creates an order from the input file and sends it for processing.
 */
public class OrderController {
	
	
	private Logger logger = null;
	private Handler consoleHandler = null;
	private Handler fileHandler = null;
	
	/**
	 * Instantiates a new order processor.
	 */
	public OrderController()
	{
		logger = Logger.getLogger(this.getClass().getName());
		
		consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(new LogFormatter());
		
		try {
			fileHandler = new FileHandler("log/controller.log");
			fileHandler.setFormatter(new LogFormatter());
		} catch (SecurityException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		logger.addHandler(consoleHandler);
		logger.addHandler(fileHandler);
	}
	
	/**
	 * The main method.
	 *
	 * @param a[0] - File name containing the order details
	 * @param a[1] - User type (Possible values : EMPLOYEE, AFFILIATE, LOYALIST)
	 * @param a[2] - Optional field. To be used for verifying multiple user type combinations. (Possible values : EMPLOYEE, AFFILIATE, LOYALIST)
	 */
	public static void main(String a[])
	{
		OrderController controller = new OrderController();
		Order order = null;
		
		order = new Order();		
		User user = new User();
		
		order.setOrderId("Order1");
		
		user.setCustId("1234");
		user.setFirstName("Jack");
		user.setLastName("Sparrow");
		user.setAddress("address");		
		user.setLastTransactedDate(new Date());
		
		
		
		if(a.length < 2)
		{
			System.out.println("Mandatory input parameters missing. Usage: java -jar eshop.jar <order file name> <user type> <user type>");
			System.out.println("User type (Possible values : EMPLOYEE, AFFILIATE, LOYALIST)");
			user.setUserType(User.USER_TYPE.EMPLOYEE);
			user.setRegistrationDate(new Date());
			order.setUser(user);			
			order = controller.createOrder("input/order.csv",order);
		}
		else if(a.length > 2)
		{
			
			if(a[1].equals("LOYALIST") || a[2].equals("LOYALIST"))
			{
				String regDate = "2017-01-16";
				Date registrationDate = null;
				
			    try {
			    		registrationDate = new SimpleDateFormat("yyyy-MM-dd").parse(regDate);
				} catch (ParseException e) {					
					e.printStackTrace();
				} 
				
				user.setRegistrationDate(registrationDate);
			}
			else
			{
				user.setRegistrationDate(new Date());
			}
			
			if(a[1].equals("EMPLOYEE") || a[2].equals("EMPLOYEE"))
			{
				user.setUserType(User.USER_TYPE.EMPLOYEE);
				
			}
			else if(a[1].equals("AFFILIATE") || a[2].equals("AFFILIATE"))
			{
				user.setUserType(User.USER_TYPE.AFFILIATE);
				
			}
			else
			{
				user.setUserType(User.USER_TYPE.NONE);
			}
			
			order.setUser(user);
			System.out.println(""+user.getUserType());
			order = controller.createOrder(a[0],order);
			
			
		}			
		else if(a.length == 2)
		{
			if(a[1].equals("EMPLOYEE"))
			{
				user.setUserType(User.USER_TYPE.EMPLOYEE);
				user.setRegistrationDate(new Date());
				
			}
			else if(a[1].equals("AFFILIATE"))
			{
				user.setUserType(User.USER_TYPE.AFFILIATE);
				user.setRegistrationDate(new Date());
				
			}
			else if(a[1].equals("LOYALIST"))
			{
				
				String regDate = "2017-01-16";
				Date registrationDate = null;
				
			    try {
			    		registrationDate = new SimpleDateFormat("yyyy-MM-dd").parse(regDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			    user.setUserType(User.USER_TYPE.NONE);
				user.setRegistrationDate(registrationDate);
			}
			else
			{
				user.setUserType(User.USER_TYPE.NONE);
				user.setRegistrationDate(new Date());
				
			}
			
			order.setUser(user);
			System.out.println(""+user.getUserType());
			order = controller.createOrder(a[0],order);
		}
		
		OrderProcessor orderProcessor = new OrderProcessor();
		orderProcessor.processOrder(order);
		
	}
	
	/**
	 * Creates the order.
	 *
	 * @param orderFile the order file
	 * @param order the order
	 * @return the order
	 */
	private Order createOrder(String orderFile, Order order)
	{
		
		File file = new File(orderFile);
		logger.log(Level.INFO,"File "+orderFile+" exists -> "+file.exists());
		
		if(!file.exists())
		{
			logger.log(Level.INFO,"File "+orderFile+" does not exist -> Using input/order.csv");
			orderFile = "input/order.csv";
		}
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String orderItemData = null;
		
		Product product = null;
		OrderItem orderItem = null;
		double totalAmount = 0.0;
		ArrayList<OrderItem> itemList = new ArrayList<OrderItem>();
		
		StringTokenizer orderItemDetails = null;
		String productType = null;
		
		
		try {
			fileReader = new FileReader(orderFile);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		bufferedReader = new BufferedReader(fileReader);
		
		try {
			while((orderItemData = bufferedReader.readLine()) != null)
			{
				
				orderItemDetails = new StringTokenizer(orderItemData,",");
				
				while(orderItemDetails.hasMoreTokens())
				{
					product = new Product();
					orderItem = new OrderItem();
					
					product.setProductId(orderItemDetails.nextToken());
					
					product.setName(orderItemDetails.nextToken());
					
					product.setDescription(orderItemDetails.nextToken());
					
					
					productType = orderItemDetails.nextToken();
					
					if(productType.equals("GROCERY"))
					{
						product.setProductType(Product.PRODUCT_TYPE.GROCERY);
					}
					else if(productType.equals("ELECTRONICS"))
					{
						product.setProductType(Product.PRODUCT_TYPE.ELECTRONICS);
					}
					else if(productType.equals("MEDICAL"))
					{
						product.setProductType(Product.PRODUCT_TYPE.MEDICAL);
					}
					else
					{
						product.setProductType(Product.PRODUCT_TYPE.GROCERY);
					}
					product.setUnitPrice(new Double(orderItemDetails.nextToken()).doubleValue());
					
					orderItem.setProduct(product);
					orderItem.setQuantity(new Integer(orderItemDetails.nextToken()).intValue());
					orderItem.setItemCost(orderItem.getQuantity()*product.getUnitPrice());
					itemList.add(orderItem);
					totalAmount += orderItem.getQuantity()*product.getUnitPrice();
				}
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		order.setItemList(itemList);
		order.setTotalAmount(totalAmount);
		order.setDiscountAmount(0.0);
		order.setFinalAmount(totalAmount);
		
		return order;
	}

}
