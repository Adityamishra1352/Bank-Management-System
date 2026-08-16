package com.BankManagementSystem.BankManagementSystem;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Scope("prototype")
public class Account {
    private final JdbcTemplate jdbcTemplate;
    int accountNumber;
    String accountType;
    double balance=0.0;
    Customer customer;
    String sql;
    int last_withdraw;
    int last_deposit;
    public Account(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void deposit(int n, int accountNo){
        if(n>0){
            sql="UPDATE accounts set balance=balance+?, transactions_counter=transactions_counter+1, last_deposit=? where account_number=?";
            int rows=jdbcTemplate.update(sql,n,n, accountNo);
            if(rows>0){
                System.out.println("Money deposited successfully");
            }
            else{
                System.out.println("Account Not Found");
            }
        }
        else{
            System.out.println("Amount to deposit cannot be 0");
        }

    }
    public void withdraw(int n, int accountNo){
        if(n>0){
            sql="SELECT balance from accounts where account_number=?";
            Double balance=jdbcTemplate.queryForObject(sql, Double.class, accountNo);
            if(balance==null){
                System.out.println("Account not found");
                return;
            }
            System.out.println("Current Balance: "+balance);
            if(n<=balance){
                sql="UPDATE accounts set balance=balance-?, last_withdraw=?, transactions_counter=transactions_counter+1 WHERE account_number=?";
                int rows=jdbcTemplate.update(sql,n,n,accountNo);
                if(rows>0){
                    System.out.println("Withdrawn: "+n);
                    System.out.println("Current Balance: "+(balance-n));
                }
            }
            else{
                System.out.println("Insufficient balance.");
                System.out.println("Current Balance: "+ balance);
            }
        }
        else{
            System.out.println("Amount to withdraw cannot be 0");
        }
    }
    public void checkBalance(){
        System.out.println("Balance: " + balance);
    }

    public void transfer(int k, int l, int m) {
        sql="SELECT COUNT(*) FROM accounts where account_number in (?,?)";
        Integer count=jdbcTemplate.queryForObject(sql, Integer.class,k,l);

        if(count==2){
            sql="UPDATE accounts set balance=balance-?,last_withdraw=?, transactions_counter=transactions_counter+1 WHERE account_number=?";
            int rows=jdbcTemplate.update(sql,m,m,k);
            if(rows==1){
                sql="UPDATE accounts set balance=balance+?,last_deposit=?, transactions_counter=transactions_counter+1 where account_number=?";
                int rows2=jdbcTemplate.update(sql,m,m,l);
                if(rows2==1){
                    System.out.println("Transfer amount successfull");
                }else{
                    sql="UPDATE accounts set balance = balance +? where account_number=?";
                    jdbcTemplate.update(sql,m,k);
                }
            }else{
                System.out.println("Transfer failed");
            }
        }else{
            System.out.println("Account not found");
        }
    }

    public void transactionCount(int o) {
        sql="SELECT transactions_counter from accounts where account_number=?";
        Integer count=jdbcTemplate.queryForObject(sql, Integer.class, o);
        System.out.println("Number of transactions on your account: "+count);
    }
}
