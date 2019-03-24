package com.eshop.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.eshop.model.Order;
import com.eshop.model.OrderItem;
import com.eshop.model.Product;
import com.eshop.model.User;
import com.eshop.util.LogFormatter;


/**
 * OrderProcessor Class - Receives an order, processes it item by item 
 * and applies discount where applicable.
 */
public class OrderProcessor {
	
	
	private Logger logger = null;
	private Handler consoleHandler = null;
	private Handler fileHandler = null;
	
	/**
	 * Instantiates a new order processor.
	 */
	public OrderProcessor()
	{
		logger = Logger.getLogger(this.getClass().getName());
		
		consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(new LogFormatter());
		
		try {
			fileHandler = new FileHandler("log/order.log");
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
	 * Process order.
	 *
	 * @param order - the order to be processed
	 * @return the order - the processed order
	 */
	public Order processOrder(Order order)
	{
		
		displayOrder(order);
		
		
		Order finalOrder = null;
		
		logger.log(Level.INFO,"Processing order. Checking for discounts to apply...");
		
		//if user is an affiliate, discount 30% from total amount
		if(order.getUser().getUserType().equals(User.USER_TYPE.EMPLOYEE))
		{			
			
			logger.log(Level.INFO,"Customer Type : "+""+order.getUser().getUserType()+", applying 30% discount on all items except GROCERY");
			
			finalOrder = applyPercentageDiscount(order,0.3);	
			
		}
		//if user is an affiliate, discount 10% from total amount
		else if(order.getUser().getUserType().equals(User.USER_TYPE.AFFILIATE))
		{
			logger.log(Level.INFO,"Customer Type : "+""+order.getUser().getUserType()+", applying 10% discount on all items except GROCERY");
			finalOrder = applyPercentageDiscount(order,0.1);
		}
		//if user is a member for more than 2 years, discount 5% from total amount
		else
		{
			logger.log(Level.INFO,"Checking loyal customer...");
			Calendar today = Calendar.getInstance();
			today.setTime(new Date());
			
			Calendar registeredDate = Calendar.getInstance();
			registeredDate.setTime(order.getUser().getRegistrationDate());
			
			int yearsDiff = today.get(Calendar.YEAR) - registeredDate.get(Calendar.YEAR);
			int monthsDiff = today.get(Calendar.MONTH) - registeredDate.get(Calendar.MONTH);
			
			int totalMonthsDiff = yearsDiff*12+monthsDiff;		
			
			if(totalMonthsDiff > 24)
			{
				logger.log(Level.INFO,"Customer has been registered with us for : "+""+totalMonthsDiff+" months, applying 5% discount on all items except GROCERY");
				finalOrder = applyPercentageDiscount(order,0.05);	
			}
		}
		
		//For each $100 in amount, discount $5 from total amount
		//Check if none of the criteria above were met.
		if(finalOrder == null)
		{
			logger.log(Level.INFO,"No percentage discounts apply to bill. Checking for dollar discount...");
			finalOrder = order;
		}
		finalOrder = applyDollarDiscount(finalOrder,5);
		
		
		displayOrder(finalOrder);
		
		return finalOrder;
		
	}
	
	/**
	 * Apply percentage discount.
	 *
	 * @param order the order
	 * @param discountPercentage the discount percentage
	 * @return the order
	 */
	protected Order applyPercentageDiscount(Order order, double discountPercentage)
	{
		Order finalOrder = null;
		ArrayList <OrderItem>updatedItemList = null;
		Iterator <OrderItem>orderIterator = null;
		
		OrderItem orderItem = null;
		OrderItem updatedOrderItem = null;
		
		updatedItemList = new ArrayList<OrderItem>();
		finalOrder = order;
		orderIterator = order.getItemList().iterator();
		
		double updatedFinalAmount = 0.0;
		double itemCost = 0.0;
		double updatedItemCost = 0.0;
		
		double discountAmount = 0.0;
		double totalDiscountAmount = 0.0;
		
		int serialNo = 0;
		 
		
		
		while(orderIterator.hasNext())
		{
			orderItem = (OrderItem) orderIterator.next();
			
			++serialNo;
			
			logger.log(Level.INFO,"Before Discount");
			
			logger.log(Level.INFO,serialNo+". "+orderItem.getProduct().getDescription()+"\t"+orderItem.getQuantity()+"\t"+orderItem.getItemCost());
			
			
			if(!orderItem.getProduct().getProductType().equals(Product.PRODUCT_TYPE.GROCERY))
			{
				updatedOrderItem = orderItem;
				itemCost = orderItem.getItemCost();
				
				discountAmount = itemCost*discountPercentage;
				
				updatedItemCost = itemCost - discountAmount;
				
				updatedOrderItem.setItemCost(updatedItemCost);
				updatedItemList.add(updatedOrderItem);
				
				totalDiscountAmount += discountAmount;
				updatedFinalAmount += updatedItemCost;
				
				logger.log(Level.INFO,"After Discount");
				logger.log(Level.INFO,serialNo+". "+updatedOrderItem.getProduct().getDescription()+"\t"+updatedOrderItem.getQuantity()+"\t"+updatedOrderItem.getItemCost());
								
			}
			else
			{
				updatedItemList.add(orderItem);
				updatedFinalAmount += orderItem.getItemCost();
				
				logger.log(Level.INFO,"After Discount");
				logger.log(Level.INFO,serialNo+". "+orderItem.getProduct().getDescription()+"\t"+orderItem.getQuantity()+"\t"+orderItem.getItemCost());
				
			}
			
			
			
			
		}
		
		finalOrder.setItemList(updatedItemList);
		finalOrder.setDiscountAmount(totalDiscountAmount);
		finalOrder.setFinalAmount(Math.round(updatedFinalAmount*100.0)/100.0);
		
		logger.log(Level.INFO,"Total discount applied after percentage discount offers :"+totalDiscountAmount);
		
		
		return finalOrder;
		
	}
	
	/**
	 * Apply dollar discount.
	 *
	 * @param order - the order to be processed
	 * @param discountValue the discount value
	 * @return the order - processed order
	 */
	protected Order applyDollarDiscount(Order order, double discountValue)
	{
		long numberOfHundreds = Math.round(order.getTotalAmount())/100;
		
		double discountAmount = 0.0;
		
		if(numberOfHundreds > 0)
		{
			
			//Check if none of the criteria above were met.
			
			discountAmount = numberOfHundreds*discountValue;
			logger.log(Level.INFO,"Total order value more than $100. Dollar discount for the order value of $"+order.getTotalAmount()+" is $"+discountAmount);
			
			order.setDiscountAmount(order.getDiscountAmount()+discountAmount);
			
			order.setFinalAmount(Math.round((order.getFinalAmount()-discountAmount)*100.0)/100.0);
			logger.log(Level.INFO,"Total discount applied after dollar discount offers :"+order.getDiscountAmount());
			
			
		}
		
		
		return order;
		
	}
	
	/**
	 * Display order.
	 *
	 * @param order the order
	 */
	private void displayOrder(Order order)
	{
		
		Iterator<OrderItem> orderIterator = order.getItemList().iterator();
		OrderItem orderItem = null;
		
		int serialNo = 0;
		
		StringBuffer orderItemDetails = new StringBuffer();
		
		while(orderIterator.hasNext())
		{
			++serialNo;
			orderItem = (OrderItem) orderIterator.next();		
			
			orderItemDetails.append(serialNo+". "+orderItem.getProduct().getDescription()+"\t"+orderItem.getQuantity()+"\t"+orderItem.getItemCost()+"\n");
			
		}
		
		logger.log(Level.INFO,"*******************Start of Bill*********************"+"\n"+
				"Order Date : "+order.getUser().getLastTransactedDate()+"\n"+
				"Order Id : "+order.getOrderId()+"\n"+
				"Purchased By : "+order.getUser().getFirstName()+" "+order.getUser().getLastName()+"\n"+
				"--------------------------Start of Item List----------------------------------"+"\n"+
				orderItemDetails.toString()+"\n"+
				"--------------------------End of Item List----------------------------------"+"\n"+
				"Purchase Amount : "+order.getTotalAmount()+"\n"+
				"Discount Amount : "+order.getDiscountAmount()+"\n"+
				"Final Amount : "+order.getFinalAmount()+"\n"+
				"*******************End of Bill*********************"
				
				);		
	}

}

