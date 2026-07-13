package classes;
public class Bank{
    Account account=new Account();
    Customer obj=new Customer();
    public void createAccount(int customerId, String name ,int phone, String address, int accountNumber, String accountType){
        obj.customerId=customerId;
        obj.name=name;
        obj.phoneNumber=phone;
        obj.address=address;
        account.accountType=accountType;
        account.accountNumber=accountNumber;
    }
    public void searchAccount(int accountNumber){
        if(account.accountNumber==accountNumber){
            System.out.println("Account found.");
            obj.displayCustomer();
        }
    }
    public void displayAccount(int accountNumber){
        obj.displayCustomer();
        System.out.println("Account Number: "+account.accountNumber);
        System.out.println("Account Type: "+account.accountType);
        account.checkBalance();
    }
    public void depositMoney(int n, int accountNumber){
        account.deposit(n, accountNumber);
    }
    public void withdraw(int n, int accountNumber){
        account.withdraw(n,accountNumber);
    }
}