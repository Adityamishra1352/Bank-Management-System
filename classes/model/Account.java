package classes.model;
import classes.exception.*;
import java.io.Serializable;
public class Account implements Serializable{
    private int accountNumber;
    private String accountType;
    private double balance=0.0;
    private double lastDeposit;
    private double lastWithdraw;
    private int transactionsCounter;
    private Customer customer;
    public void setCustomer(Customer customer){
        this.customer=customer;
    }
    public Customer getCustomer(){
        return customer;
    }
    public void setAccountNumber(int accountNumber){
        this.accountNumber=accountNumber;
    }
    public int getAccountNumber(){
        return accountNumber;
    }
    public void setBalance(double balance){
        this.balance=balance;
    }
    public double getBalance(){
        return balance;
    }
    public void setAccountType(String accountType){
        this.accountType=accountType;
    }
    public String getAccountType(){
        return accountType;
    }
    public void setLastDeposit(double lastDeposit){
        this.lastDeposit=lastDeposit;
    }
    public double getLastDeposit(){
        return lastDeposit;
    }
    public void setLastWithdraw(double lastWithdraw){
        this.lastWithdraw=lastWithdraw;
    }
    public double getLastWithdraw(){
        return lastWithdraw;
    }
    public int getTransactionsCounter(){
        return transactionsCounter;
    }
    public void deposit(int n) throws InvalidAmountException{
        if(n>0){
            lastDeposit=n;
            balance=balance+n;
            transactionsCounter++;
            System.out.println("Deposit Successful!");
        }
        else{
            throw new InvalidAmountException("Negative or Rs.0 cannot be added");
        }
    }
    public void withdraw(int n) throws InsufficientBalanceException{
            System.out.println("n: "+n);
            System.out.println("balance: "+balance);
        if(n<=balance){
            lastWithdraw=n;
            balance = balance-n;
            transactionsCounter++;
            System.out.println("Balance remaaining "+balance);
            System.out.println("Withdrawn "+n+" ruppess!!");
        }
        else{
            throw new InsufficientBalanceException("Amount to withdraw greater than the balance.");
        }
    }
    public void checkBalance(){
        System.out.println("Balance: " + balance);
    }
}