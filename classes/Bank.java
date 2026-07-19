package classes;
import java.util.ArrayList;
public class Bank{
    public Bank() {
    accounts = fileManager.loadAccounts();
}
    private ArrayList<Account> accounts;
private FileManager fileManager = new FileManager();
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
        // accounts.add(account);
        accounts.add(account);
        fileManager.saveAccounts(accounts);
        System.out.println("Account created successfully.");
        
    }
    public void totalAccounts(){
        System.out.println("Total Accounts: "+accounts.size());
    }
    public void searchAccount(int accountNumber) throws InvalidAccountException{
        boolean searchFound=false;
        for(Account acc:accounts){
        if(acc.getAccountNumber()==accountNumber){
            System.out.println("Account found.");
            acc.getCustomer().displayCustomer();
        }
        }
        if(searchFound==false){
            throw new InvalidAccountException("Account Not Found!!");
        }
    }
    public void displayAccount(int accountNumber) throws InvalidAccountException{
        for(Account acc:accounts){
            if(acc.getAccountNumber()==accountNumber){
        acc.getCustomer().displayCustomer();
        System.out.println("Account Number: "+acc.getAccountNumber());
        System.out.println("Account Type: "+acc.getAccountType());
        acc.checkBalance();
            }
            else{
                throw new InvalidAccountException("Account Not Found!!");
            }
        }
    }
    public void depositMoney(int n, int accountNumber) throws InvalidAccountException{
        for(Account acc:accounts){
            if(accountNumber==acc.getAccountNumber()){
                try{
                    acc.deposit(n);
                    fileManager.saveAccounts(accounts);
                }
                catch(InvalidAmountException e){
                    System.out.println(e.getMessage());
                }
            
            }
            else{
                throw new InvalidAccountException("Account Not Found!!");
            }
        }
    }
    public void withdraw(int n, int accountNumber) throws InvalidAccountException{
        for(Account acc:accounts){
        if(accountNumber==acc.getAccountNumber()){
        try{
            acc.withdraw(n);
            fileManager.saveAccounts(accounts);
        }
        catch(InsufficientBalanceException e){
            System.out.println(e.getMessage());
        }
        }
        else{
            throw new InvalidAccountException("Account not found!!");
        }
        }
    }
    public void updatePhone(int n, int accountNumber) throws InvalidAccountException{
        for(Account acc: accounts){
            if(accountNumber==acc.getAccountNumber()){
                acc.getCustomer().updatePhone(n);
                fileManager.saveAccounts(accounts);
            }
            else{
                throw new InvalidAccountException("Account not found!!");
            }
        }
        
    }
    public void updateAddress( String address,int accountNumber) throws InvalidAccountException{
        for(Account acc:accounts){
            if(accountNumber==acc.getAccountNumber()){
                acc.getCustomer().updateAddress(address);
                fileManager.saveAccounts(accounts);
            }
            else{
                throw new InvalidAccountException("Account not found!!");
            }
        }
    }
    public void deleteAccount(int accountNumber) throws InvalidAccountException{
        boolean found=false;
        for(int z=0;z<accounts.size();z++){
            if(accountNumber==accounts.get(z).getAccountNumber()){
                accounts.remove(z);
                fileManager.saveAccounts(accounts);
                found=true;
                // System.out.println("Account deleted successfuly!!");
            }
        }
        if(found==true){
            System.out.println("Account deleted successfuly!!");
        }
        else{
                throw new InvalidAccountException("Account not found!!");
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
    public void transferMoney(int sender, int receiver, int amount) throws InvalidAccountException{
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
                throw new InvalidAccountException("Sender or Receiver account not found");
            }
        }
        if(senderExists==true && receiverExists==true){
        for(Account acc: accounts){
            if(acc.getAccountNumber()==sender){
                try{
                    acc.withdraw(amount);
                    fileManager.saveAccounts(accounts);
                }
                catch(InsufficientBalanceException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(acc.getAccountNumber()==receiver){
                try{
                    acc.deposit(amount);
                    fileManager.saveAccounts(accounts);
                }
                catch(InvalidAmountException e){
                    System.out.println(e.getMessage());
                }
                
            }
        }
        }
    }
    public void transactionsCounter(int accountNumber) throws InvalidAccountException{
        boolean tranFound=false;
        for(Account acc: accounts){
            if(acc.getAccountNumber()==accountNumber){
                tranFound=true;
                System.out.println("Number of transactions: "+acc.getTransactionsCounter());
            }
        }
        if(tranFound==false){
            throw new InvalidAccountException("Account Not found");
        }
    }
    public void miniStatement(int accountNumber) throws InvalidAccountException{
        boolean miniFound=false;
        for(Account acc: accounts){
            if(acc.getAccountNumber()==accountNumber){
                miniFound=true;
                System.out.println("Current Balance: "+acc.getBalance());
                System.out.println("Last Deposit: "+acc.getLastDeposit());
                System.out.println("Last Withdraw: "+acc.getLastWithdraw());
            }
        }
        if(miniFound==false){
            throw new InvalidAccountException("Account not found");
        }
    }
    public void searchAccountByName(String name) throws InvalidAccountException{
        boolean nameFound=false;
        for(Account acc: accounts){
            if(acc.getCustomer().getName().equalsIgnoreCase(name)){
                System.out.println("Account found.");
                nameFound=true;
                acc.getCustomer().displayCustomer();
            }
        }
        if(nameFound==false){
            throw new InvalidAccountException("Account not found");
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