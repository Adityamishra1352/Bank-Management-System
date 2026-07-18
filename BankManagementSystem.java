import classes.Bank;
import java.util.Scanner;

public class BankManagementSystem{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        Bank bank= new Bank();
        int choice;
        System.out.println("Welcome to the Bank Management System:");
        do{
        System.out.println("1.Create Account\n2.Search Account\n3.Display Account\n4.Deposit Money\n5.Withdraw Money\n6.Update Phone number\n7.Update address\n8.Total Number of Accounts\n9.Delete Account");
        System.out.println("10.Display all Accounts\n11.Transfer Money\n12.Number of Transactions\n13.Mini Statement");
        System.out.println("14.Search Account by Name\n15.Bank Statistics\n16.Exit");
        choice=sc.nextInt();
        switch(choice){
            case 1:
            System.out.println("customerId: ");
            int customerId=sc.nextInt();
            sc.nextLine();
            System.out.println("name: ");
            String name=sc.nextLine();
            System.out.println("phone: ");
            int phone=sc.nextInt();
            sc.nextLine();
            System.out.println("address: ");
            String address=sc.nextLine();
            System.out.println("accountNumber: ");
int accountNumber = sc.nextInt();
sc.nextLine();          // <-- consume the Enter

System.out.println("accountType: ");
String accountType = sc.nextLine();
            bank.createAccount(customerId, name , phone, address,  accountNumber,  accountType);
            //System.out.println("Account Created Successfully");
            break;
            case 8:
                bank.totalAccounts();
                break;
            case 2: 
            System.out.println("Enter Account Number: ");
            int n=sc.nextInt();
            bank.searchAccount(n);
            break;
            case 3: 
            System.out.println("Enter Account Number: ");
            int a =sc.nextInt();
            bank.displayAccount(a);
            break;
            case 4:
                System.out.println("Enter your account number: ");
                int b=sc.nextInt();
                sc.nextLine();
                System.out.println("Enter the amount to deposit: ");
                int c=sc.nextInt();
                bank.depositMoney(c,b);
                break;
            case 5: System.out.println("Enter your account Number: ");
            int d=sc.nextInt();
            System.out.println("Enter the amount to withdraw:");
            int e=sc.nextInt();
            bank.withdraw(e,d);
            break;
            case 6: System.out.println("Enter your accoount number: ");
            int f=sc.nextInt();
            System.out.println("Enter your new phone number");
            int g=sc.nextInt();
            bank.updatePhone(g,f);
            break;
            case 7: System.out.println("Enter your account number: ");
            int h=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter your new address: ");
            String i=sc.nextLine();
            bank.updateAddress(i,h);
            break;
            case 9: System.out.println("Enter your account number:");
            int j=sc.nextInt();
            bank.deleteAccount(j);
            case 10: 
            bank.displayAllAccounts();
            break;
            case 11: 
            System.out.println("Enter the sender account number: ");
            int k=sc.nextInt();
            System.out.println("Enter the receiver's account number: ");
            int l=sc.nextInt();
            System.out.println("Enter the amount to transfer: ");
            int m=sc.nextInt();
            bank.transferMoney(k,l,m);
            break;
            case 12: 
            System.out.println("Enter your account number: ");
            int o=sc.nextInt();
            bank.transactionsCounter(o);
            break;
            case 13:
                System.out.println("Enter your account number: ");
                int p=sc.nextInt();
                bank.miniStatement(p);
                break;
            case 14: 
            System.out.println("Enter your name: ");
            sc.nextLine();
            String q=sc.nextLine();
            bank.searchAccountByName(q);
            break;
            case 15: 
            bank.bankStats();
            break;
        }
        }while(choice!=16);
    }
}