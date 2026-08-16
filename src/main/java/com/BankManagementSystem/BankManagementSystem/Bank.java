package com.BankManagementSystem.BankManagementSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Scope("singleton")
public class Bank {
    String sql;
    int rows;
    @Autowired
    private Account account;
    @Autowired
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
        rows=jdbcTemplate.update(sql,customerId, name, phone, address);
        if(rows>0){
            System.out.println("Customer created successfully");
        }
        sql="INSERT INTO accounts(account_number, customer_id, account_type, balance) VALUES (?,?,?,?)";
        rows=jdbcTemplate.update (sql, accountNumber, customerId, accountType, 0);
        if(rows>0){
            System.out.println("Account Created Successfully");
        }

    }
    public void searchAccount(int accountNumber){
        sql="Select * from customers a,  accounts b where account_number=? and a.customer_id=b.customer_id";
        RowMapper<Customer> mapper=new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Customer customer=new Customer(jdbcTemplate);
                customer.customerId=rs.getInt("customer_id");
                customer.name=rs.getString("name");
                customer.phoneNumber=rs.getInt("phone");
                customer.address=rs.getString("address");
                return customer;
            }

        };
        Customer customer=jdbcTemplate.queryForObject(sql, mapper, accountNumber);
        customer.displayCustomer();
    }
    public void displayAccount(int accountNumber) {

        sql = "SELECT a.customer_id, a.name, a.phone, a.address, " +
                "b.account_number, b.account_type, b.balance " +
                "FROM customers a JOIN accounts b ON a.customer_id = b.customer_id " +
                "WHERE b.account_number=?";

        RowMapper<Account> mapper = new RowMapper<Account>() {

            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {

                Account account = new Account(jdbcTemplate);

                account.accountNumber = rs.getInt("account_number");
                account.accountType = rs.getString("account_type");
                account.balance = rs.getInt("balance");

                Customer customer = new Customer(jdbcTemplate);

                customer.customerId = rs.getInt("customer_id");
                customer.name = rs.getString("name");
                customer.phoneNumber = rs.getInt("phone");
                customer.address = rs.getString("address");

                account.customer = customer;

                return account;
            }
        };

        Account account = jdbcTemplate.queryForObject(
                sql,
                mapper,
                accountNumber
        );

        account.customer.displayCustomer();

        System.out.println("Account Number: " + account.accountNumber);
        System.out.println("Account Type: " + account.accountType);
        System.out.println("Balance: " + account.balance);
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

    public void deleteAccount(int accountNumber) {

        sql = "SELECT customer_id FROM accounts WHERE account_number=?";
        Integer customerId = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                accountNumber
        );
        if (customerId == null) {
            System.out.println("Account not found");
            return;
        }
        sql = "DELETE FROM accounts WHERE account_number=?";
        rows = jdbcTemplate.update(sql, accountNumber);
        if (rows > 0) {
            sql = "DELETE FROM customers WHERE customer_id=?";
            rows = jdbcTemplate.update(sql, customerId);

            if (rows > 0) {
                System.out.println("Customer account deleted successfully");
            }
        }
    }

    public void displayAllAccounts() {
        sql = "SELECT a.customer_id, a.name, a.phone, a.address, " +
                "b.account_number, b.account_type, b.balance " +
                "FROM customers a JOIN accounts b ON a.customer_id = b.customer_id ";
        RowMapper<Account> mapper = new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                Account account = new Account(jdbcTemplate);
                account.accountNumber = rs.getInt("account_number");
                account.accountType = rs.getString("account_type");
                account.balance = rs.getInt("balance");

                Customer customer = new Customer(jdbcTemplate);

                customer.customerId = rs.getInt("customer_id");
                customer.name = rs.getString("name");
                customer.phoneNumber = rs.getInt("phone");
                customer.address = rs.getString("address");

                account.customer = customer;
                return account;
            }
        };
        List<Account> accounts = jdbcTemplate.query(sql, mapper);

        for (Account account : accounts) {

            account.customer.displayCustomer();

            System.out.println("Account Number: " + account.accountNumber);
            System.out.println("Account Type: " + account.accountType);
            System.out.println("Balance: " + account.balance);

            System.out.println("-----------------------------");
        }
    }

    public void transferMoney(int k, int l, int m) {
        account.transfer(k,l,m);
    }

    public void transactionsCounter(int o) {
        account.transactionCount(o);
    }

    public void miniStatement(int p) {
        sql = "SELECT a.customer_id, a.name, a.phone, a.address, " +
                "b.account_number, b.last_deposit,b.last_withdraw, b.balance " +
                "FROM customers a JOIN accounts b ON a.customer_id = b.customer_id " +
                "WHERE b.account_number=?";

        RowMapper<Account> mapper = new RowMapper<Account>() {

            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {

                Account account = new Account(jdbcTemplate);

                account.accountNumber = rs.getInt("account_number");
//                account.accountType = rs.getString("account_type");
                account.balance = rs.getInt("balance");
                account.last_deposit=rs.getInt("last_deposit");
                account.last_withdraw=rs.getInt("last_withdraw");
                Customer customer = new Customer(jdbcTemplate);

                customer.customerId = rs.getInt("customer_id");
                customer.name = rs.getString("name");
                customer.phoneNumber = rs.getInt("phone");
                customer.address = rs.getString("address");

                account.customer = customer;

                return account;
            }
        };

        Account account = jdbcTemplate.queryForObject(
                sql,
                mapper,
                p
        );

        account.customer.displayCustomer();

        System.out.println("Account Number: " + account.accountNumber);
//        System.out.println("Account Type: " + account.accountType);
        System.out.println("Balance: " + account.balance);
        System.out.println("Last Deposit amount: " + account.last_deposit);
        System.out.println("Last withdraw amount: " + account.last_withdraw);
    }
}
