package edu.psu.abington.ist.ist242;
/*
Project: Lab 9
Purpose Details: Pizza ordering application
Course: IST 242
Author: Joe Oakes, Quenten Calvano
Date Developed: 3/14/19
Last Date Changed: 6/12/2020
Rev: 2
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Order extends ArrayList<Order> {
    //Class Level Variables - Protect the data
    private int orderId;
    private Customer cust;
    private ArrayList<Menu> menuItem;

    //Constructor Method
    public Order(int _orderId){
        this.orderId = _orderId;
    }

    //Setters and Getters
    //Order ID...
    public int getorderId() {
        return orderId;
    }
    public void setorderId(int _orderId) {
        this.orderId = _orderId;
    }
    //Customer...
    public Customer getCust() {
        return cust;
    }
    public void setCust(Customer cust) {
        this.cust = cust;
    }
    //Menu Item...
    public ArrayList<Menu> getOrderItem() {
        return menuItem;
    }
    public void setOrderItem(ArrayList<Menu> menuItem) {
        this.menuItem = menuItem;
    }

    public static void printOrder(Order order){

        System.out.println("Order Id: " + order.getorderId());
        System.out.println("Order Name: " + order.getCust().getCustomerName());
        System.out.println("Pizza(s) Ordered: ");
        Menu.listMenu(order.getOrderItem());
    }
}
