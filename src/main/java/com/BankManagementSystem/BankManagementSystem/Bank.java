package com.BankManagementSystem.BankManagementSystem;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Bank {
    String sql;
    private Account account;
    private Customer obj;
    private JdbcTemplate jdbcTemplate;
    public Bank(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    public void setObj(Customer obj) {
        this.obj = obj;
    }
    public void createAccount(int customerId, String name ,int phone, String address, int accountNumber, String accountType){
        sql="INSERT INTO customers VALUES (?,?,?,?)";
        jdbcTemplate.update(sql,customerId, name, phone, address);
        sql="INSERT INTO accounts(account_number, customer_id, account_type, balance) VALUES (?,?,?,?)";
        jdbcTemplate.update (sql, accountNumber, customerId, accountType, 0);
        System.out.println("Account Created Successfully");
    }
    public void searchAccount(int accountNumber){

        if(account.accountNumber==accountNumber){
            System.out.println("Account found.");
            obj.displayCustomer();
        }
    }
    public void displayAccount(int accountNumber){
        obj.displayCustomer();
        System.out.println("Account Number: "+account.accountNumber);
        System.out.println("Account Type: "+account.accountType);
        account.checkBalance();
    }
    public void depositMoney(int n, int accountNumber){
        account.deposit(n, accountNumber);
    }
    public void withdraw(int n, int accountNumber){
        account.withdraw(n,accountNumber);
    }
    public void updatePhone(int n, int accountNumber){
        obj.updatePhone(n, accountNumber);
    }
    public void updateAddress( String address, int accountNumber){
        obj.updateAddress( address, accountNumber);
    }
}
