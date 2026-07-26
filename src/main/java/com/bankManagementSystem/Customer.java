package com.bankManagementSystem;

public class Customer{
    int customerId;
    String name;
    int phoneNumber;
    String address;
    Account accountNumber;

    public void displayCustomer(){
        System.out.println("Customer Name: "+ name);
        System.out.println("Customer ID: "+ customerId);
        System.out.println("Customer Address: "+ address);
        System.out.println("Customer Phone Number: "+ phoneNumber);
    }
//    public int updatePhone(int n){
//        phoneNumber=n;
//        return 1;
//    }
//    public int updateAddress(String n){
//        address=n;
//        return 1;
//    }
    public void updatePhone(int n, int accNumber){
        if(accountNumber.accountNumber == accNumber) {
            phoneNumber = n;
            System.out.println("Phone updated successfully.");
        }
    }
    public void updateAddress(String address, int accNumber){
        if(accountNumber.accountNumber == accNumber) {
            this.address = address;
            System.out.println("Address updated successfully.");
        }
    }
}
