package classes;
public class Customer{
    int customerId;
    String name;
    int phoneNumber;
    String address;

    public void displayCustomer(){
        System.out.println("Customer Name: "+ name);
        System.out.println("Customer ID: "+ customerId);
        System.out.println("Customer Address: "+ address);
        System.out.println("Customer Phone Number: "+ phoneNumber);
    }
    public int updatePhone(int n){
        phoneNumber=n;
        return 1;
    }
    public int updateAddress(String n){
        address=n;
        return 1;
    }
}