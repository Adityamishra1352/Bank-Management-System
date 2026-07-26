package com.bankManagementSystem;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bank {
    private Account account;
    private Customer obj;

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setObj(Customer obj) {
        this.obj = obj;
    }
        public void createAccount(int customerId, String name ,int phone, String address, int accountNumber, String accountType){
            obj.customerId=customerId;
            obj.name=name;
            obj.phoneNumber=phone;
            obj.address=address;
            account.accountType=accountType;
            account.accountNumber=accountNumber;
            obj.accountNumber = account;
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
        public void updatePhone(int n, int accountNumber){
            obj.updatePhone(n, accountNumber);
        }
        public void updateAddress( String address, int accountNumber){
            obj.updateAddress( address, accountNumber);
        }
}
