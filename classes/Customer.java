package classes;
public class Customer{
    int customerId;
    String name;
    int phoneNumber;
    String address;
    Account accountNumber;

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