package com.BankManagementSystem.BankManagementSystem;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Customer {
    private final JdbcTemplate jdbcTemplate;
    int customerId;
    String name;
    int phoneNumber;
    String address;
    Account accountNumber;
    String sql;

    public Customer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void displayCustomer(){
        System.out.println("Customer Name: "+ name);
        System.out.println("Customer ID: "+ customerId);
        System.out.println("Customer Address: "+ address);
        System.out.println("Customer Phone Number: "+ phoneNumber);
    }
    public void updatePhone(int n, int accNumber){
        sql="UPDATE customers set phone =? where customer_id =(SELECT customer_id from Accounts where account_number=?)";
        int rows=jdbcTemplate.update(sql, n, accNumber);
        if(rows>0) {
            System.out.println("Phone updated successfully.");
        }else{
            System.out.println("Account not found");
        }
    }
    public void updateAddress(String address, int accNumber){
        sql="UPDATE customers set address =? where customer_id =(SELECT customer_id from Accounts where account_number=?)";
        int rows=jdbcTemplate.update(sql, address, accNumber);
        if(rows>0) {
            System.out.println("Address updated successfully.");
        }else{
            System.out.println("Account not found");
        }
    }
}
