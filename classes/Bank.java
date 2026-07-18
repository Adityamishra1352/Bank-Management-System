package classes;
import java.util.ArrayList;
public class Bank{
    ArrayList<Account> accounts=new ArrayList<>();
    public void createAccount(int customerId, String name ,int phone, String address, int accountNumber, String accountType){
        Account account=new Account();
        Customer customer=new Customer();
        customer.customerId=customerId;
        customer.name=name;
        customer.phoneNumber=phone;
        customer.address=address;
        account.accountType=accountType;
        account.accountNumber=accountNumber;
        account.balance=0;
        account.customer=customer;
        for(Account acc:accounts){
            if(acc.accountNumber==accountNumber){
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
        if(acc.accountNumber==accountNumber){
            System.out.println("Account found.");
            acc.customer.displayCustomer();
        }
        }
    }
    public void displayAccount(int accountNumber){
        for(Account acc:accounts){
            if(acc.accountNumber==accountNumber){
        acc.customer.displayCustomer();
        System.out.println("Account Number: "+acc.accountNumber);
        System.out.println("Account Type: "+acc.accountType);
        acc.checkBalance();
            }
        }
    }
    public void depositMoney(int n, int accountNumber){
        for(Account acc:accounts){
            if(accountNumber==acc.accountNumber){
            acc.deposit(n, accountNumber);
            }
        }
    }
    public void withdraw(int n, int accountNumber){
        for(Account acc:accounts){
        if(accountNumber==acc.accountNumber){
        acc.withdraw(n,accountNumber);
        }
        }
    }
    public void updatePhone(int n, int accountNumber){
        for(Account acc: accounts){
            if(accountNumber==acc.accountNumber){
                acc.customer.updatePhone(n);
            }
        }
        
    }
    public void updateAddress( String address,int accountNumber){
        for(Account acc:accounts){
            if(accountNumber==acc.accountNumber){
                acc.customer.updateAddress(address);
            }
        }
    }
    public void deleteAccount(int accountNumber){
        for(int z=0;z<accounts.size();z++){
            if(accountNumber==accounts.get(z).accountNumber){
                accounts.remove(z);
                System.out.println("Account deleted successfuly!!");
            }
            else{
                System.out.println("Account not found!!");
            }
        }
    }
    public void displayAllAccounts(){
        for(Account acc:accounts){
            System.out.println("--------------------");
            System.out.println("Account: "+acc.accountNumber);
            System.out.println("Name: "+acc.customer.name);
            System.out.println("Balance: "+acc.balance);
        }
    }
    public void transferMoney(int sender, int receiver, int amount){
        for(Account acc:accounts){
            if(acc.accountNumber==sender){
                acc.withdraw(amount,sender);
            }
            else if(acc.accountNumber==receiver){
                acc.deposit(amount, receiver);
            }
            else{
                System.out.println("Sender or Receiver account not found");
            }
        }
    }
}