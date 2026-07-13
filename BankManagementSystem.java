import classes.Bank;
import java.util.Scanner;

public class BankManagementSystem{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        Bank bank= new Bank();
        int choice;
        System.out.println("Welcome to the Bank Management System:");
        do{
        System.out.println("1.Create Account\n2.Search Account\n3.Display Account\n4.Deposit Money\n5.Withdraw Money\n6.Exit");
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
            System.out.println("Account Created Successfully");
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
        }
        }while(choice!=6);
    }
}