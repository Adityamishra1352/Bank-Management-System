package classes;
public class Account{
    int accountNumber;
    String accountType;
    double balance=0.0;
    Customer customer;
    public void deposit(int n, int accountNo){
        if(accountNumber==accountNo){
        if(n>=0){
            balance=balance+n;
            System.out.println("Deposit Successful!");
        }
        else{
            System.out.println("Negative or Rs.0 cannot be added");
        }
        }
        else{
            System.out.println("Account Not Found");
        }
    }
    public void withdraw(int n, int accountNo){
        if(accountNumber==accountNo){
            System.out.println("n: "+n);
            System.out.println("balance: "+balance);
        if(n<=balance){
            balance = balance-n;
            System.out.println("Balance remaaining "+balance);
            System.out.println("Withdrawn "+n+" ruppess!!");
        }
        else{
            System.out.println("Amount to withdraw greater than the balance.");
        }
        }
        else{
            System.out.println("Account Not Found.");
        }
    }
    public void checkBalance(){
        System.out.println("Balance: " + balance);
    }
}