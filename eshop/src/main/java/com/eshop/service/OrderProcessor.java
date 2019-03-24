package com.eshop.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.eshop.model.Order;
import com.eshop.model.OrderItem;
import com.eshop.model.Product;
import com.eshop.model.User;

public class OrderProcessor {
	
	public Order processOrder(Order order)
	{
		
		displayOrder(order);
		
		
		Order finalOrder = null;
		
		System.out.println("Processing order. Checking for discounts to apply...");
		
		//if user is an affiliate, discount 30% from total amount
		if(order.getUser().getUserType().equals(User.USER_TYPE.EMPLOYEE))
		{
			
			/*
			while(orderIterator.hasNext())
			{
				orderItem = (OrderItem) orderIterator.next();
				if(!orderItem.getProduct().getProductType().equals(Product.PRODUCT_TYPE.GROCERY))
				{
					updatedOrderItem = orderItem;
					updatedOrderItem.setItemCost(orderItem.getItemCost()-(orderItem.getItemCost()*0.3));
					updatedItemList.add(updatedOrderItem);
					finalOrder.setTotalAmount(order.getTotalAmount()-(order.getTotalAmount()*0.3));
				}
				else
				{
					updatedItemList.add(orderItem);
				}
				
			}
			*/
			System.out.println("Customer Type : "+""+order.getUser().getUserType()+", applying 30% discount on all items except GROCERY");
			
			finalOrder = applyPercentageDiscount(order,0.3);
			//displayOrder(finalOrder);
			
			
		}
		//if user is an affiliate, discount 10% from total amount
		else if(order.getUser().getUserType().equals(User.USER_TYPE.AFFILIATE))
		{
			System.out.println("Customer Type : "+""+order.getUser().getUserType()+", applying 10% discount on all items except GROCERY");
			finalOrder = applyPercentageDiscount(order,0.1);
		}
		//if user is a member for more than 2 years, discount 5% from total amount
		else
		{
			System.out.println("Checking loyal customer...");
			Calendar today = Calendar.getInstance();
			today.setTime(new Date());
			
			
			/*
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			System.out.println("Today : "+sdf.format(new Date()));
			
			System.out.println("Regn Date : "+sdf.format(order.getUser().getRegistrationDate()));
			*/
			
			Calendar registeredDate = Calendar.getInstance();
			registeredDate.setTime(order.getUser().getRegistrationDate());
			
			int yearsDiff = today.get(Calendar.YEAR) - registeredDate.get(Calendar.YEAR);
			int monthsDiff = today.get(Calendar.MONTH) - registeredDate.get(Calendar.MONTH);
			
			int totalMonthsDiff = yearsDiff*12+monthsDiff;
			
			
			/*
			System.out.println("Years : "+yearsDiff);
			System.out.println("months : "+monthsDiff);
			
			
			System.out.println("Months : "+totalMonthsDiff);
			*/
			
			if(totalMonthsDiff > 24)
			{
				System.out.println("Customer has been registered with us for : "+""+totalMonthsDiff+" months, applying 5% discount on all items except GROCERY");
				finalOrder = applyPercentageDiscount(order,0.05);	
			}
		}
		
		//For each $100 in amount, discount $5 from total amount
		//Check if none of the criteria above were met.
		if(finalOrder == null)
		{
			System.out.println("No percentage discounts apply to bill. Checking for dollar discount...");
			finalOrder = order;
		}
		finalOrder = applyDollarDiscount(finalOrder,5);
		/*
		long numberOfHundreds = Math.round(order.getTotalAmount())/100;
		
		if(numberOfHundreds > 0)
		{
			//Check if none of the criteria above were met.
			if(finalOrder == null)
			{
				finalOrder = order;
			}
			finalOrder.setTotalAmount(order.getTotalAmount()-(numberOfHundreds*5));
		}
		*/
		
		displayOrder(finalOrder);
		
		return finalOrder;
		
	}
	
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
			/*
			System.out.println("Before Discount");
			
			System.out.println(serialNo+". "+orderItem.getProduct().getDescription()+"\t"+orderItem.getQuantity()+"\t"+orderItem.getItemCost());
			
			System.out.println("Discount Amount : "+order.getDiscountAmount());
			System.out.println("Final Amount : "+order.getFinalAmount());
			*/
			
			System.out.println("Before Discount");
			
			System.out.println(serialNo+". "+orderItem.getProduct().getDescription()+"\t"+orderItem.getQuantity()+"\t"+orderItem.getItemCost());
			
			
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
				
				System.out.println("After Discount");
				System.out.println(serialNo+". "+updatedOrderItem.getProduct().getDescription()+"\t"+updatedOrderItem.getQuantity()+"\t"+updatedOrderItem.getItemCost());
				
				/*
				System.out.println("After Discount");
				System.out.println(serialNo+". "+updatedOrderItem.getProduct().getDescription()+"\t"+updatedOrderItem.getQuantity()+"\t"+updatedOrderItem.getItemCost());
				
				System.out.println("Discount Amount : "+totalDiscountAmount);
				System.out.println("Final Amount : "+updatedFinalAmount);
				*/
				
			}
			else
			{
				updatedItemList.add(orderItem);
				updatedFinalAmount += orderItem.getItemCost();
				
				System.out.println("After Discount");
				System.out.println(serialNo+". "+orderItem.getProduct().getDescription()+"\t"+orderItem.getQuantity()+"\t"+orderItem.getItemCost());
				
				/*
				System.out.println("After Discount");
				System.out.println(serialNo+". "+orderItem.getProduct().getDescription()+"\t"+orderItem.getQuantity()+"\t"+orderItem.getItemCost());
				
				System.out.println("Discount Amount : "+totalDiscountAmount);
				System.out.println("Final Amount : "+updatedFinalAmount);
				*/
				
			}
			
			
			
			
		}
		
		finalOrder.setItemList(updatedItemList);
		finalOrder.setDiscountAmount(totalDiscountAmount);
		finalOrder.setFinalAmount(Math.round(updatedFinalAmount*100.0)/100.0);
		
		System.out.println("Total discount applied after percentage discount offers :"+totalDiscountAmount);
		
		//displayOrder(finalOrder);
		
		return finalOrder;
		
	}
	
	protected Order applyDollarDiscount(Order order, double discountValue)
	{
		long numberOfHundreds = Math.round(order.getTotalAmount())/100;
		
		double discountAmount = 0.0;
		
		/*
		System.out.println("Before Discount");
		
		//System.out.println(orderItem.getProduct().getDescription()+"\t"+orderItem.getQuantity()+"\t"+orderItem.getItemCost());
		
		System.out.println("Discount Amount : "+order.getDiscountAmount());
		System.out.println("Final Amount : "+order.getFinalAmount());
		*/
		
		if(numberOfHundreds > 0)
		{
			
			//Check if none of the criteria above were met.
			
			discountAmount = numberOfHundreds*discountValue;
			System.out.println("Total order value more than $100. Dollar discount for the order value of $"+order.getTotalAmount()+" is $"+discountAmount);
			
			order.setDiscountAmount(order.getDiscountAmount()+discountAmount);
			
			order.setFinalAmount(Math.round((order.getFinalAmount()-discountAmount)*100.0)/100.0);
			System.out.println("Total discount applied after dollar discount offers :"+order.getDiscountAmount());
			
			
		}
		
		/*
		System.out.println("After Discount");
		
		//System.out.println(orderItem.getProduct().getDescription()+"\t"+orderItem.getQuantity()+"\t"+orderItem.getItemCost());
		
		System.out.println("Discount Amount : "+order.getDiscountAmount());
		System.out.println("Final Amount : "+order.getFinalAmount());
		*/
		
		
		
		return order;
		
	}
	
	private void displayOrder(Order order)
	{
		System.out.println("******Bill Details******");
		System.out.println("Order Date : "+order.getUser().getLastTransactedDate());
		System.out.println("Order Id : "+order.getOrderId());
		System.out.println("Purchased By : "+order.getUser().getFirstName()+" "+order.getUser().getLastName());
		
		Iterator<OrderItem> orderIterator = order.getItemList().iterator();
		OrderItem orderItem = null;
		
		int serialNo = 0;
		
		while(orderIterator.hasNext())
		{
			++serialNo;
			orderItem = (OrderItem) orderIterator.next();
			
			System.out.println(serialNo+". "+orderItem.getProduct().getDescription()+"\t"+orderItem.getQuantity()+"\t"+orderItem.getItemCost());
			
		}
		
		System.out.println("Purchase Amount : "+order.getTotalAmount());
		System.out.println("Discount Amount : "+order.getDiscountAmount());
		System.out.println("Final Amount : "+order.getFinalAmount());
		System.out.println("******End of Bill******");
		
	}

}

