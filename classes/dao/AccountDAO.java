package classes.dao;
import classes.util.*;
import classes.exception.*;
import classes.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AccountDAO{

    String sql;
    int rowsUpdated=0;
    int count = 0;

    public void deposit(int n, int accountNumber) throws InvalidAccountException{
        try{
            Connection con=DBConnection.getConnection();
            sql="UPDATE accounts SET balance=balance+?, transactions_counter=transactions_counter+1, last_deposit=? WHERE customer_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, n);
            ps.setInt(2, n);
            ps.setInt(3, accountNumber);
            rowsUpdated=ps.executeUpdate();
            if(rowsUpdated>0){
                System.out.println("Deposit Successful!!");
            }
            else{
                throw new InvalidAccountException("Account not found!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void withdraw(int n, int accountNumber) throws InvalidAccountException, InsufficientBalanceException{
        try{
            Connection con= DBConnection.getConnection();
            sql="SELECT balance from accounts where account_number=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountNumber);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                double balance=rs.getDouble("balance");
                if(balance<n){
                    throw new InsufficientBalanceException("Balance less than withdraw amount!");
                }
                else{
                    sql="UPDATE accounts SET balance=balance-?, transactions_counter=transactions_counter+1, last_withdraw=? WHERE account_number=?";
                    PreparedStatement ua=con.prepareStatement(sql);
                    ua.setInt(1, n);
                    ua.setInt(2, n);
                    ua.setInt(3, accountNumber);
                    int rowsUpdated=ua.executeUpdate();
                    if(rowsUpdated>0){
                        System.out.println("Withdrawn: "+n);
                    }
                }
            }
            else{
                throw new InvalidAccountException("Account not found!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void transferMoney(int sender, int receiver, int amount) throws InvalidAccountException{
        try{
            Connection con = DBConnection.getConnection();
            sql="SELECT COUNT(1) as count FROM accounts where account_number IN (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, sender);
            ps.setInt(2, receiver);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                count= rs.getInt("count");
                if(count==2){
                    String updateSql="Update accounts SET balance= balance- ?, transactions_counter=transactions_counter+1, last_withdraw=? where account_number=?";
                    PreparedStatement tw=con.prepareStatement(updateSql);
                    tw.setInt(1, amount);
                    tw.setInt(2, amount);
                    tw.setInt(3, sender);
                    rowsUpdated=tw.executeUpdate();
                    if(rowsUpdated>0){
                        System.out.println("Withdraw success!!");
                    }
                    String depositSql="Update accounts set balance= balance+?, transactions_counter=transactions_counter+1, last_deposit=? WHERE account_number= ?";
                    PreparedStatement td=con.prepareStatement(depositSql);
                    td.setInt(1, amount);
                    td.setInt(2, amount);
                    td.setInt(3, receiver);
                    rowsUpdated=td.executeUpdate();
                    if(rowsUpdated>0){
                        System.out.println("Deposit Success!!");
                    }
                    // con.commit();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void transactionsCounter(int accountNumber) throws InvalidAccountException{
        try{
            Connection con = DBConnection.getConnection();
            sql="SELECT transactions_counter from accounts where account_number=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,accountNumber);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                System.out.println("Number of transactions: "+rs.getInt("transactions_counter"));
            }
            else{
                throw new InvalidAmountException("Account not found!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void miniStatement(int accountNumber) throws InvalidAccountException{
        try{
            Connection con = DBConnection.getConnection();
            sql="SELECT balance, last_deposit, last_withdraw from accounts where account_number= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountNumber);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                System.out.println("Current Balance: "+rs.getInt("balance"));
                System.out.println("Last Deposit: "+rs.getInt("last_deposit"));
                System.out.println("Last Withdraw: "+rs.getInt("last_withdraw"));
            }
            else{
                throw new InvalidAccountException("Account not found!!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}