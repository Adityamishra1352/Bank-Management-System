package classes;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bank{
    String sql;
    int rowsUpdated=0;
    int count = 0;
    public void createAccount(int customerId, String name ,int phone, String address, int accountNumber, String accountType) throws InvalidAccountException{
        try{
            sql="SELECT COUNT(1) AS counter FROM accounts where account_number = ?";
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountNumber);

            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                throw new Exception("Account already exists!!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        sql="INSERT INTO customers VALUES(?,?,?,?)";
        try{
            Connection con = DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.setString(2, name);
        ps.setInt(3, phone);
        ps.setString(4, address);

        ps.executeUpdate();
        System.out.println("Customer inserrted successfully");
        sql="INSERT INTO accounts(account_number, customer_id, account_type, balance) VALUES (?,?,?,?)";
            PreparedStatement as=con.prepareStatement(sql);
            as.setInt(1, accountNumber);
            as.setInt(2, customerId);
            as.setString(3, accountType);
            as.setInt(4, 0);

            as.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }        
    }
    public void totalAccounts(){
        sql="SELECT count(*) as total from customers";
        try{
            Connection con = DBConnection.getConnection();
        PreparedStatement totalAccounts=con.prepareStatement(sql);
        ResultSet rs=totalAccounts.executeQuery();
        if(rs.next()){
            System.out.println("Total Accounts: "+rs.getInt("total"));
        }
        else{
            System.out.println("No Accounts found");
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void searchAccount(int accountNumber) throws InvalidAccountException{
        sql="SELECT customer_id, name, phone, address from customers where customer_id = ?";
        try{
            Connection con=DBConnection.getConnection();
            PreparedStatement searchAcc=con.prepareStatement(sql);
            searchAcc.setInt(1, accountNumber);
            ResultSet rs=searchAcc.executeQuery();
            if(rs.next()){
                System.out.println("Customer_id: "+rs.getInt("customer_id"));
                System.out.println("Customer Name: "+rs.getString("name"));
                System.out.println("Customer Phone: "+rs.getInt("phone"));
                System.out.println("Customer Address: "+rs.getString("address"));
            }
            else{
                throw new InvalidAccountException("Account Not Found!!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void displayAccount(int accountNumber) throws InvalidAccountException{
        try{
            Connection con = DBConnection.getConnection();
            sql="SELECT a.customer_id, a.name, a.address, a.phone, b.account_number, b.account_type, b.balance from customers a JOIN accounts b on a.customer_id = b.customer_id where a.customer_id = ?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,accountNumber);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                System.out.println("Customerr ID: "+rs.getInt("customer_id"));
                System.out.println("Customer Name: "+rs.getString("name"));
                System.out.println("Customer Address: "+rs.getString("address"));
                System.out.println("Customer Phone Number: "+rs.getInt("phone"));
                System.out.println("Customer Account Number: "+rs.getInt("account_number"));
                System.out.println("Customer Account Type: "+rs.getString("account_type"));
                System.out.println("Customer Balanxe: "+rs.getDouble("balance"));
            }else{
                throw new InvalidAccountException("Account Not Found!!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void depositMoney(int n, int accountNumber) throws InvalidAccountException{
        try{
            Connection con=DBConnection.getConnection();
            sql="UPDATE accounts SET balance=balance+? WHERE customer_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, n);
            ps.setInt(2, accountNumber);
            rowsUpdated=ps.executeUpdate();
            if(rowsUpdated>0){
                System.out.println("Deposit Successful!!");
            }
            else{
                throw new InvalidAccountException("Account not found!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void withdraw(int n, int accountNumber) throws InvalidAccountException, InsufficientBalanceException{
        try{
            Connection con= DBConnection.getConnection();
            sql="SELECT balance from accounts where account_number=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountNumber);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                double balance=rs.getDouble("balance");
                if(balance<n){
                    throw new InsufficientBalanceException("Balance less than withdraw amount!");
                }
                else{
                    sql="UPDATE accounts SET balance=balance-? WHERE account_number=?";
                    PreparedStatement ua=con.prepareStatement(sql);
                    ua.setInt(1, n);
                    ua.setInt(2, accountNumber);
                    int rowsUpdated=ua.executeUpdate();
                    if(rowsUpdated>0){
                        System.out.println("Withdrawn: "+n);
                    }
                }
            }
            else{
                throw new InvalidAccountException("Account not found!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void updatePhone(int n, int accountNumber) throws InvalidAccountException{
        // for(Account acc: accounts){
        //     if(accountNumber==acc.getAccountNumber()){
        //         acc.getCustomer().updatePhone(n);
        //     }
        //     else{
        //         throw new InvalidAccountException("Account not found!!");
        //     }
        // }

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
                sql="UPDATE customers SET phone=? WHERE customer_id = ?";
                PreparedStatement up = con.prepareStatement(sql);
                up.setInt(1, n);
                up.setInt(2, accountNumber);
                rowsUpdated=up.executeUpdate();
                if(rowsUpdated>0){
                    System.out.println("Phone number updated successfully!");
                }
            }
        }    
        catch(Exception e){
            e.printStackTrace();
        }
    }
    // public void updateAddress( String address,int accountNumber) throws InvalidAccountException{
    //     for(Account acc:accounts){
    //         if(accountNumber==acc.getAccountNumber()){
    //             acc.getCustomer().updateAddress(address);
    //         }
    //         else{
    //             throw new InvalidAccountException("Account not found!!");
    //         }
    //     }
    // }
    // public void deleteAccount(int accountNumber) throws InvalidAccountException{
    //     boolean found=false;
    //     for(int z=0;z<accounts.size();z++){
    //         if(accountNumber==accounts.get(z).getAccountNumber()){
    //             accounts.remove(z);
    //             found=true;
    //             // System.out.println("Account deleted successfuly!!");
    //         }
    //     }
    //     if(found==true){
    //         System.out.println("Account deleted successfuly!!");
    //     }
    //     else{
    //             throw new InvalidAccountException("Account not found!!");
    //         }
    // }
    // public void displayAllAccounts(){
    //     for(Account acc:accounts){
    //         System.out.println("--------------------");
    //         System.out.println("Account: "+acc.getAccountNumber());
    //         System.out.println("Name: "+acc.getCustomer().getName());
    //         System.out.println("Balance: "+acc.getBalance());
    //     }
    // }
    // public void transferMoney(int sender, int receiver, int amount) throws InvalidAccountException{
    //     boolean senderExists=false;
    //     boolean receiverExists=false;
    //     for(Account acc:accounts){
    //         if(acc.getAccountNumber()==sender){
    //             // acc.withdraw(amount);
    //             senderExists=true;
    //         }
    //         else if(acc.getAccountNumber()==receiver){
    //             // acc.deposit(amount);
    //             receiverExists=true;
    //         }
    //         else{
    //             throw new InvalidAccountException("Sender or Receiver account not found");
    //         }
    //     }
    //     if(senderExists==true && receiverExists==true){
    //     for(Account acc: accounts){
    //         if(acc.getAccountNumber()==sender){
    //             try{
    //                 acc.withdraw(amount);
    //             }
    //             catch(InsufficientBalanceException e){
    //                 System.out.println(e.getMessage());
    //             }
    //         }
    //         else if(acc.getAccountNumber()==receiver){
    //             try{
    //                 acc.deposit(amount);
    //             }
    //             catch(InvalidAmountException e){
    //                 System.out.println(e.getMessage());
    //             }
                
    //         }
    //     }
    //     }
    // }
    // public void transactionsCounter(int accountNumber) throws InvalidAccountException{
    //     boolean tranFound=false;
    //     for(Account acc: accounts){
    //         if(acc.getAccountNumber()==accountNumber){
    //             tranFound=true;
    //             System.out.println("Number of transactions: "+acc.getTransactionsCounter());
    //         }
    //     }
    //     if(tranFound==false){
    //         throw new InvalidAccountException("Account Not found");
    //     }
    // }
    // public void miniStatement(int accountNumber) throws InvalidAccountException{
    //     boolean miniFound=false;
    //     for(Account acc: accounts){
    //         if(acc.getAccountNumber()==accountNumber){
    //             miniFound=true;
    //             System.out.println("Current Balance: "+acc.getBalance());
    //             System.out.println("Last Deposit: "+acc.getLastDeposit());
    //             System.out.println("Last Withdraw: "+acc.getLastWithdraw());
    //         }
    //     }
    //     if(miniFound==false){
    //         throw new InvalidAccountException("Account not found");
    //     }
    // }
    // public void searchAccountByName(String name) throws InvalidAccountException{
    //     boolean nameFound=false;
    //     for(Account acc: accounts){
    //         if(acc.getCustomer().getName().equalsIgnoreCase(name)){
    //             System.out.println("Account found.");
    //             nameFound=true;
    //             acc.getCustomer().displayCustomer();
    //         }
    //     }
    //     if(nameFound==false){
    //         throw new InvalidAccountException("Account not found");
    //     }
    // }
    // public void bankStats(){
    //     if(accounts.isEmpty()){
    //         System.out.println("No accounts exist!!");
    //         return;
    //     }
    //     double totalMoney=0.0;
    //     double highestBalance=0.0;
    //     double lowestBalance = accounts.get(0).getBalance();
    //     for(Account acc:accounts){
    //         totalMoney=totalMoney+acc.getBalance();
    //         if(highestBalance<acc.getBalance()){
    //             highestBalance=acc.getBalance();
    //         }
    //         if(lowestBalance>acc.getBalance()){
    //             lowestBalance=acc.getBalance();
    //         }
    //     }
    //     System.out.println("Total Accounts: "+accounts.size());
    //     System.out.println("Total Money in Bank: "+totalMoney);
    //     System.out.println("Average Balance: "+totalMoney/accounts.size());
    //     System.out.println("Highest Balance: "+highestBalance);
    //     System.out.println("Lowest Balance: "+lowestBalance);
    // }
}