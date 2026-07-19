package classes;
import java.io.Serializable;
public class Customer implements Serializable{
    private int customerId;
    private String name;
    private int phoneNumber;
    private String address;
    // Account accountNumber;
    public Customer(String name, int phoneNumber, String address, int customerId){
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.customerId=customerId;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public int getPhoneNumber(){
        return phoneNumber;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getAddress(){
        return address;
    }
    public void setCustomerId(int customerId){
        this.customerId=customerId;
    }
    public int getCustomerId(){
        return customerId;
    }

    public void displayCustomer(){
        System.out.println("Customer Name: "+ name);
        System.out.println("Customer ID: "+ customerId);
        System.out.println("Customer Address: "+ address);
        System.out.println("Customer Phone Number: "+ phoneNumber);
    }
    public void updatePhone(int n){
    phoneNumber = n;
    System.out.println("Phone updated successfully.");
}
    public void updateAddress(String address){
    this.address = address;
    System.out.println("Address updated successfully.");
}
}