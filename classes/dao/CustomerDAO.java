package classes.dao;
import classes.util.*;
import classes.exception.*;
import classes.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO{
    String sql;
    int rowsUpdated=0;
    int count = 0;
    public void createAccount(int customerId, String name ,int phone, String address, int accountNumber, String accountType) throws InvalidAccountException{
        try{
            sql="SELECT COUNT(1) AS counter FROM accounts where account_number = ?";
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountNumber);

            ResultSet rs=ps.executeQuery();
            if(rs.next()){
            count = rs.getInt("counter");

                if(count > 0){
                    throw new InvalidAccountException("Account already exists!");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        sql="INSERT INTO customers VALUES(?,?,?,?)";
        try{
            Connection con = DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.setString(2, name);
        ps.setInt(3, phone);
        ps.setString(4, address);

        ps.executeUpdate();
        System.out.println("Customer inserrted successfully");
        sql="INSERT INTO accounts(account_number, customer_id, account_type, balance) VALUES (?,?,?,?)";
            PreparedStatement as=con.prepareStatement(sql);
            as.setInt(1, accountNumber);
            as.setInt(2, customerId);
            as.setString(3, accountType);
            as.setInt(4, 0);

            as.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        } 
    }
    public void totalAccounts(){
        sql="SELECT count(*) as total from customers";
        try{
            Connection con = DBConnection.getConnection();
        PreparedStatement totalAccounts=con.prepareStatement(sql);
        ResultSet rs=totalAccounts.executeQuery();
        if(rs.next()){
            System.out.println("Total Accounts: "+rs.getInt("total"));
        }
        else{
            System.out.println("No Accounts found");
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void searchAccount(int accountNumber) throws InvalidAccountException{
        sql="SELECT customer_id, name, phone, address from customers where customer_id = ?";
        try{
            Connection con=DBConnection.getConnection();
            PreparedStatement searchAcc=con.prepareStatement(sql);
            searchAcc.setInt(1, accountNumber);
            ResultSet rs=searchAcc.executeQuery();
            if(rs.next()){
                System.out.println("Customer_id: "+rs.getInt("customer_id"));
                System.out.println("Customer Name: "+rs.getString("name"));
                System.out.println("Customer Phone: "+rs.getInt("phone"));
                System.out.println("Customer Address: "+rs.getString("address"));
            }
            else{
                throw new InvalidAccountException("Account Not Found!!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void displayAccount(int accountNumber) throws InvalidAccountException{
        try{
            Connection con = DBConnection.getConnection();
            sql="SELECT a.customer_id, a.name, a.address, a.phone, b.account_number, b.account_type, b.balance from customers a JOIN accounts b on a.customer_id = b.customer_id where a.customer_id = ?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,accountNumber);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                System.out.println("Customerr ID: "+rs.getInt("customer_id"));
                System.out.println("Customer Name: "+rs.getString("name"));
                System.out.println("Customer Address: "+rs.getString("address"));
                System.out.println("Customer Phone Number: "+rs.getInt("phone"));
                System.out.println("Customer Account Number: "+rs.getInt("account_number"));
                System.out.println("Customer Account Type: "+rs.getString("account_type"));
                System.out.println("Customer Balanxe: "+rs.getDouble("balance"));
            }else{
                throw new InvalidAccountException("Account Not Found!!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void updatePhone(int n, int accountNumber) throws InvalidAccountException{
        try{
            Connection con = DBConnection.getConnection();
            sql="SELECT COUNT(1) as count from customers where customer_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountNumber);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                count=rs.getInt("count");
            }
            else{
                throw new InvalidAccountException("Account not found");
            }
            if(count==1){
                sql="UPDATE customers SET phone=? WHERE customer_id = ?";
                PreparedStatement up = con.prepareStatement(sql);
                up.setInt(1, n);
                up.setInt(2, accountNumber);
                rowsUpdated=up.executeUpdate();
                if(rowsUpdated>0){
                    System.out.println("Phone number updated successfully!");
                }
            }
        }    
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void updateAddress( String address,int accountNumber) throws InvalidAccountException{
        try{
            Connection con = DBConnection.getConnection();
            sql="SELECT COUNT(1) as count from customers where customer_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountNumber);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                count=rs.getInt("count");
            }
            else{
                throw new InvalidAccountException("Account not found");
            }
            if(count==1){
                sql="UPDATE customers SET address=? WHERE customer_id = ?";
                PreparedStatement up = con.prepareStatement(sql);
                up.setString(1, address);
                up.setInt(2, accountNumber);
                rowsUpdated=up.executeUpdate();
                if(rowsUpdated>0){
                    System.out.println("Address updated successfully!");
                }
            }
        }    
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void displayAllAccounts(){
        try{
            Connection con = DBConnection.getConnection();
            sql="SELECT a.account_number, b.name, a.balance from customers b JOIN accounts a ON a.customer_id=b.customer_id";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                System.out.println("--------------------");
                System.out.println("Account Numberr: "+rs.getInt("account_number"));
                System.out.println("Customer Name: "+rs.getString("name"));
                System.out.println("Balance: "+rs.getInt("balance"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void searchAccountByName(String name) throws InvalidAccountException{
        try{
            Connection con = DBConnection.getConnection();
            sql="SELECT customer_id from customers where name=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                displayAccount(rs.getInt("customer_id"));
            }
            else{
                throw new InvalidAccountException("Account not found!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}