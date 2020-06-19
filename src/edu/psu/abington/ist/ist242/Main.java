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

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    int cCount = 1;
    //Declare and initialize the order count...
    int orderCount = 1;
    //Declare and initialize a transaction count...
    int transCount = 1;

    public static void main(String[] args) {

        Main main = new Main();

        final char EXIT_CODE = 'E';
        final char CUST_CODE = 'C';
        final char MENU_CODE = 'M';
        final char ORDE_CODE = 'O';
        final char TRAN_CODE = 'T';
        final char CUST_PRNT = 'P';
        final char HELP_CODE = '?';
        char userAction;
        final String PROMPT_ACTION = "Add 'C'ustomer, 'P'rint Customer, List 'M'enu, Add 'O'rder, List 'T'ransaction or 'E'xit: ";
        ArrayList<Customer> cList = new ArrayList<>();
        ArrayList<Menu> mList = new ArrayList<>();
        ArrayList<Order> oList = new ArrayList<>();
        ArrayList<Transaction> tList = new ArrayList<>();

        //Order order1 = new Order(1);
        //Transaction trans1 = new Transaction(1, order1, PaymentType.cash);

        Menu menu1 = new Menu(1, "Plain", 9.99);
        Menu menu2 = new Menu(2, "Meat", 12.99);
        Menu menu3 = new Menu(3, "Extra", 11.99);
        Menu menu4 = new Menu(4, "Veg",11.99);


        mList.add(menu1);
        mList.add(menu2);
        mList.add(menu3);
        mList.add(menu4);

        //oList.add(order1);
        //tList.add(trans1);

        userAction = getAction(PROMPT_ACTION);

        while (userAction != EXIT_CODE) {
            switch(userAction) {
                case CUST_CODE : cList.add(main.addCustomer());
                    break;
                case CUST_PRNT : Customer.printCustomer(cList);
                    break;
                case MENU_CODE : Menu.listMenu(mList);
                    break;
                case ORDE_CODE : oList.add(main.addOrders(mList, cList, tList));
                    break;
                case TRAN_CODE : Transaction.listTransactions(tList, oList);
                    break;

            }

            userAction = getAction(PROMPT_ACTION);
        }
    }

    public static char getAction(String prompt) {
        Scanner scnr = new Scanner(System.in);
        String answer = "";
        System.out.println(prompt);
        answer = scnr.nextLine().toUpperCase() + " ";
        char firstChar = answer.charAt(0);
        return firstChar;
    }

    public Customer addCustomer(){
        Customer cust = new Customer(cCount++);
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please Enter your Name: ");
        cust.setCustomerName(scnr.nextLine());
        System.out.println("Please Enter your Phone: ");
        cust.setCustomerPhoneNumber(scnr.nextLine());
        return cust;
    }

    public Order addOrders(ArrayList<Menu> mList, ArrayList<Customer> cList, ArrayList<Transaction> tList) {
        Order order = new Order(orderCount++);
        Scanner scnr = new Scanner(System.in);
        ArrayList<Menu> menuItems = new ArrayList<>();
        boolean isOnMenu = false;
        boolean notDoneOrdering = true;
        String orderItem;
        String userInput;
        do {
            System.out.println("What would you like to order? (Enter item name or ID)");
            orderItem = scnr.next();
            for (Menu menuItem : mList) {
                if (orderItem.equals(menuItem.getmenuItem()) || orderItem.equals(menuItem.getmenuId())) {
                    menuItems.add(menuItem);
                    isOnMenu = true;
                    break;
                }
            }
            if (!isOnMenu) {
                System.out.println("Item is not on the menu.");
            }

            boolean validPrompt = true;
            boolean getOut = false;
            while (validPrompt) {
                System.out.println("Are you finished ordering? (y/n)");
                userInput = scnr.next().toLowerCase();
                if (userInput.equals("y")) {
                    notDoneOrdering = false;
                    validPrompt = false;
                    getOut = true;
                } else if (userInput.equals("n")) {
                    validPrompt = false;
                }
                else {
                    System.out.println("ERROR: Invalid input.");
                }
            }
            boolean orderIsCorrect = false;
            while (!orderIsCorrect && getOut) {
                System.out.println("Is this order correct?");
                Menu.listMenu(menuItems);
                System.out.println("\n(y/n)");
                String orderConfirmation = scnr.next().toLowerCase();
                if (orderConfirmation.equals("y")) {
                    notDoneOrdering = false;
                    orderIsCorrect = true;
                } else if (orderConfirmation.equals("n")) {
                    System.out.println("Would you like to restart your order? (y)");
                    if (scnr.next().startsWith("y") || scnr.next().startsWith("Y")) {
                        menuItems.clear();
                        notDoneOrdering = true;
                        orderIsCorrect = true;
                    }
                }
                else {
                    System.out.println("ERROR: Invalid input.");
                }



            }


        } while (notDoneOrdering);

        order.setOrderItem(menuItems);

        boolean isCustomer = false;
        do {
            //Do the same as above for customers...
            System.out.println("Who is ordering?");
            String userName = scnr.next();
            for (Customer currentCust : cList) {
                if (userName.equals(currentCust.getCustomerName())) {
                    order.setCust(currentCust);
                    isCustomer = true;
                    break;
                }
            }
            if (!isCustomer) {
                System.out.println("Customer does not exist.");
            }
        } while (isCustomer == false);

        ///*//FIXME Payment Type...
        String payment;
        //Create a transaction object for this order...
        Transaction trans = new Transaction(transCount, order, PaymentType.cash);
        boolean paymentTaken = false;

         do {
             //Ask the user for Transaction type...
             System.out.println("Please choose a payment method (Enter the Number): \n1.Cash\n2.Credit");
             payment = scnr.next();
             if (payment.equals("1") || payment.equalsIgnoreCase("cash")) {
                 trans.setPaymentType(PaymentType.cash);
                 paymentTaken = true;
             }
             else if (payment.equals("2") || payment.equalsIgnoreCase("credit")) {
                 trans.setPaymentType(PaymentType.credit);
                 paymentTaken = true;
             }
             else {
                 System.out.println("ERROR: Invalid payment option. Please try again.");
             }

         } while (paymentTaken == false);

         //Add the transaction to the Transaction arrayList...
         tList.add(trans);
         //Increment the transaction number for the next order.
         transCount++;
         //*/
        //Return the order object...
        return order;
    }
}
