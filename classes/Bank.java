package classes;

import classes.dao.AccountDAO;
import classes.dao.CustomerDAO;
import classes.exception.*;
import classes.model.*;
import classes.util.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bank{
    private AccountDAO accountDAO = new AccountDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    String sql;
    int rowsUpdated=0;
    int count = 0;

    public void createAccount(int customerId, String name ,int phone, String address, int accountNumber, String accountType){
        try{
            customerDAO.createAccount(customerId, name , phone, address,  accountNumber,  accountType);
        } catch(InvalidAccountException e){
            System.out.println(e.getMessage());
        }     
    }

    public void totalAccounts(){
        customerDAO.totalAccounts();        
    }
    public void searchAccount(int accountNumber) {
        try{
            customerDAO.searchAccount(accountNumber);
        }        
        catch(InvalidAccountException e){
                System.out.println(e.getMessage());
            }
    }
    public void displayAccount(int accountNumber){
        try{
            customerDAO.displayAccount(accountNumber);
        }
        catch(InvalidAccountException e){
                System.out.println(e.getMessage());
        }
            
    }
    public void depositMoney(int n, int accountNumber){
        try{
        accountDAO.deposit(n, accountNumber);
        }
        catch(InvalidAccountException e){
                System.out.println(e.getMessage());
        }
    }
    public void withdraw(int n, int accountNumber){
        try{
            accountDAO.withdraw(n, accountNumber);
        }catch(Exception e){
                System.out.println(e.getMessage());
        }
    }
    public void updatePhone(int n, int accountNumber){
        try{
                customerDAO.updatePhone(n, accountNumber);
            }
            catch(InvalidAccountException e){
                System.out.println(e.getMessage());
            }
    }
    public void updateAddress( String address,int accountNumber){
        try{
            customerDAO.updateAddress(address,accountNumber);
        }
        catch(InvalidAccountException e){
                System.out.println(e.getMessage());
        }
    }
    public void deleteAccount(int accountNumber) throws InvalidAccountException{
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
                String deleteSql="DELETE FROM accounts where customer_id = ?";
                PreparedStatement ad=con.prepareStatement(deleteSql);
                ad.setInt(1, accountNumber);
                rowsUpdated=ad.executeUpdate();
                if(rowsUpdated>0){
                    System.out.println("Account deleted successfully!");
                }
                sql="DELETE FROM customers WHERE customer_id = ?";
                PreparedStatement ud = con.prepareStatement(sql);
                ud.setInt(1, accountNumber);
                rowsUpdated=ud.executeUpdate();
                if(rowsUpdated>0){
                    System.out.println("Customer deleted successfully!");
                }
            }
        }    
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void displayAllAccounts(){
        try{
            customerDAO.displayAllAccounts();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void transferMoney(int sender, int receiver, int amount){
        try{
            accountDAO.transferMoney(sender, receiver, amount);
        }
        catch(InvalidAccountException e){
                System.out.println(e.getMessage());
            }
    }
    public void transactionsCounter(int accountNumber){
        try{
            accountDAO.transactionsCounter(accountNumber);
        }
        catch(InvalidAccountException e){
                System.out.println(e.getMessage());
            }
    }
    public void miniStatement(int accountNumber){
        try{
            accountDAO.miniStatement(accountNumber);
        }
        catch(InvalidAccountException e){
                System.out.println(e.getMessage());
            }
    }
    public void searchAccountByName(String name){
        try{
            customerDAO.searchAccountByName(name);
        }catch(InvalidAccountException e){
                System.out.println(e.getMessage());
            }
    }
    public void bankStats() {

    try {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT balance FROM accounts";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        int count = 0;
        double totalMoney = 0;
        double highestBalance = 0;
        double lowestBalance = Double.MAX_VALUE;

        while(rs.next()){

            double balance = rs.getDouble("balance");

            totalMoney += balance;

            count++;

            if(balance > highestBalance){
                highestBalance = balance;
            }

            if(balance < lowestBalance){
                lowestBalance = balance;
            }

        }

        if(count == 0){
            throw new InvalidAccountException("No accounts found");
        }

        System.out.println("Total Accounts: " + count);
        System.out.println("Total Money in Bank: " + totalMoney);
        System.out.println("Average Balance: " + (totalMoney/count));
        System.out.println("Highest Balance: " + highestBalance);
        System.out.println("Lowest Balance: " + lowestBalance);

    }
    catch(Exception e){
        e.printStackTrace();
    }
}
}