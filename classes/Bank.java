package classes;
import java.util.ArrayList;
public class Bank{
    private ArrayList<Account> accounts=new ArrayList<>();
    public void createAccount(int customerId, String name ,int phone, String address, int accountNumber, String accountType){
        Account account=new Account();
        Customer customer=new Customer(name,phone,address,customerId);
        // customer.customerId=customerId;
        // customer.setCustomerId(customerId);
        // customer.name=name;
        // customer.setName(name);
        // customer.phoneNumber=phone;
        // customer.setPhoneNumber(phone);
        // customer.address=address;
        // customer.setAddress(address);
        // account.accountType=accountType;
        account.setAccountType(accountType);
        // account.accountNumber=accountNumber;
        account.setAccountNumber(accountNumber);
        // account.balance=0;
        account.setBalance(0);
        // account.customer=customer;
        account.setCustomer(customer);
        for(Account acc:accounts){
            if(acc.getAccountNumber()==accountNumber){
                System.out.println("Account already exists!!");
                return;
            }
        }
        accounts.add(account);
        
    }
    public void totalAccounts(){
        System.out.println("Total Accounts: "+accounts.size());
    }
    public void searchAccount(int accountNumber){
        for(Account acc:accounts){
        if(acc.getAccountNumber()==accountNumber){
            System.out.println("Account found.");
            acc.getCustomer().displayCustomer();
        }
        }
    }
    public void displayAccount(int accountNumber){
        for(Account acc:accounts){
            if(acc.getAccountNumber()==accountNumber){
        acc.getCustomer().displayCustomer();
        System.out.println("Account Number: "+acc.getAccountNumber());
        System.out.println("Account Type: "+acc.getAccountType());
        acc.checkBalance();
            }
        }
    }
    public void depositMoney(int n, int accountNumber){
        for(Account acc:accounts){
            if(accountNumber==acc.getAccountNumber()){
            acc.deposit(n);
            }
        }
    }
    public void withdraw(int n, int accountNumber){
        for(Account acc:accounts){
        if(accountNumber==acc.getAccountNumber()){
        acc.withdraw(n);
        }
        }
    }
    public void updatePhone(int n, int accountNumber){
        for(Account acc: accounts){
            if(accountNumber==acc.getAccountNumber()){
                acc.getCustomer().updatePhone(n);
            }
        }
        
    }
    public void updateAddress( String address,int accountNumber){
        for(Account acc:accounts){
            if(accountNumber==acc.getAccountNumber()){
                acc.getCustomer().updateAddress(address);
            }
        }
    }
    public void deleteAccount(int accountNumber){
        boolean found=false;
        for(int z=0;z<accounts.size();z++){
            if(accountNumber==accounts.get(z).getAccountNumber()){
                accounts.remove(z);
                found=true;
                // System.out.println("Account deleted successfuly!!");
            }
        }
        if(found==true){
            System.out.println("Account deleted successfuly!!");
        }
        else{
                System.out.println("Account not found!!");
            }
    }
    public void displayAllAccounts(){
        for(Account acc:accounts){
            System.out.println("--------------------");
            System.out.println("Account: "+acc.getAccountNumber());
            System.out.println("Name: "+acc.getCustomer().getName());
            System.out.println("Balance: "+acc.getBalance());
        }
    }
    public void transferMoney(int sender, int receiver, int amount){
        boolean senderExists=false;
        boolean receiverExists=false;
        for(Account acc:accounts){
            if(acc.getAccountNumber()==sender){
                // acc.withdraw(amount);
                senderExists=true;
            }
            else if(acc.getAccountNumber()==receiver){
                // acc.deposit(amount);
                receiverExists=true;
            }
            else{
                System.out.println("Sender or Receiver account not found");
            }
        }
        if(senderExists==true && receiverExists==true){
        for(Account acc: accounts){
            if(acc.getAccountNumber()==sender){
                acc.withdraw(amount);
            }
            else if(acc.getAccountNumber()==receiver){
                acc.deposit(amount);
            }
        }
        }
    }
    public void transactionsCounter(int accountNumber){
        for(Account acc: accounts){
            if(acc.getAccountNumber()==accountNumber){
                System.out.println("Number of transactions: "+acc.getTransactionsCounter());
            }
        }
    }
    public void miniStatement(int accountNumber){
        for(Account acc: accounts){
            if(acc.getAccountNumber()==accountNumber){
                System.out.println("Current Balance: "+acc.getBalance());
                System.out.println("Last Deposit: "+acc.getLastDeposit());
                System.out.println("Last Withdraw: "+acc.getLastWithdraw());
            }
        }
    }
    public void searchAccountByName(String name){
        for(Account acc: accounts){
            if(acc.getCustomer().getName().equalsIgnoreCase(name)){
                System.out.println("Account found.");
                acc.getCustomer().displayCustomer();
            }
        }
    }
    public void bankStats(){
        if(accounts.isEmpty()){
            System.out.println("No accounts exist!!");
            return;
        }
        double totalMoney=0.0;
        double highestBalance=0.0;
        double lowestBalance = accounts.get(0).getBalance();
        for(Account acc:accounts){
            totalMoney=totalMoney+acc.getBalance();
            if(highestBalance<acc.getBalance()){
                highestBalance=acc.getBalance();
            }
            if(lowestBalance>acc.getBalance()){
                lowestBalance=acc.getBalance();
            }
        }
        System.out.println("Total Accounts: "+accounts.size());
        System.out.println("Total Money in Bank: "+totalMoney);
        System.out.println("Average Balance: "+totalMoney/accounts.size());
        System.out.println("Highest Balance: "+highestBalance);
        System.out.println("Lowest Balance: "+lowestBalance);
    }
}