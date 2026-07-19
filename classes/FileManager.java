package classes;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;
public class FileManager{
    public void saveAccounts(ArrayList<Account> accounts){
        try(FileOutputStream output=new FileOutputStream("accounts.dat");
            ObjectOutputStream oos=new ObjectOutputStream(output)
            ){ 
            oos.writeObject(accounts);
            System.out.println("Printing in file success");
        }catch(IOException e){
            System.out.println("Error writing in the file");
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    public ArrayList<Account> loadAccounts() {
        try (
            FileInputStream file = new FileInputStream("accounts.dat");
            ObjectInputStream input = new ObjectInputStream(file)) {
            ArrayList<Account> accounts =(ArrayList<Account>) input.readObject();
            System.out.println("Accounts Loaded Successfully.");
            return accounts;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved data found.");
        }
        return new ArrayList<>();
    }
}