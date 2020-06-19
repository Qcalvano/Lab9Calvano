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
import java.util.Arrays;

enum PaymentType {cash, credit}

public class Transaction {

    //Class Level Variables - Protect the data
    private int transactionId;
    private Order order;
    private PaymentType pType;

    //Constructor Method
    public Transaction(int _transactionId, Order _order, PaymentType _pType){
        this.transactionId = _transactionId;
        this.order = _order;
        this.pType = _pType;
    }

    //Setters and Getters
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int _transactionId) {this.transactionId = _transactionId;}

    public Order getOrder() { return order; }
    public void setOrder(Order _order) {this.order = _order;}

    public PaymentType getPaymentType() { return pType; }
    public void setPaymentType(PaymentType _pType) {this.pType = _pType;}

    public static void listTransactions(ArrayList<Transaction> tList, ArrayList<Order> oList){
        for (Transaction trans: tList){
            Order order = trans.getOrder();
            System.out.println("\nTransaction ID: " + trans.getTransactionId());
            Order.printOrder(order);
            System.out.println("Payment Type: " + trans.getPaymentType());
            double totalPrice = 0.0;
            for (Menu menuItem : order.getOrderItem()) {
                totalPrice += menuItem.getPrice();
            }
            System.out.println("Total: $" + totalPrice);
            System.out.println();
        }
    }

}
