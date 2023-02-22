package utilpack;
import java.sql.*;
import java.util.Scanner;


import Singleton.*;

public class utilmeth implements utilinter {

    singleton db = singleton.getInstance();
    Scanner scanner = new Scanner(System.in);

    
/*

    int chold = 400043000;
*/


    @Override
    public void create(String name, String passw, int card) throws Exception {
        db.connect();
        String query = "insert into users(username,password,cardnumber,balance) values('" + name + "','" + passw + "','"+card+"',0);";
        db.execute(query);
        db.disconnect();

    }
    @Override
    public void myacc(int num) throws Exception{
        db.connect();
        String query = "select * from users where cardnumber='"+num+"'";
        ResultSet r = db.result(query);

        if(r.next()) {

            System.out.println("ID: " + r.getInt("id"));
            System.out.println("Username: " + r.getString("username"));
            System.out.println("Card Number: " + r.getInt("cardnumber"));
            System.out.println("Balance: " + r.getInt("balance"));

        }
        db.disconnect();
    }
    @Override
    public void signin(String name, String passw) throws Exception {
        db.connect();
        String query = "select * from users where username='"+name+"'and password='"+passw+"';";
        String queryf = "select * from frozen where username='"+name+"'and password='"+passw+"';";
        String queryr = "select cardnumber from frozen where username='"+name+"'and password='"+passw+"';";
        ResultSet r = db.result(query);
        ResultSet rf = db.result(queryf);
        ResultSet rr = db.result(queryr);
        if(r.next()){
            System.out.println("Welcome, "+name);

        } else if (rf.next()) {
            System.out.println("Your account is deactivated. Do you want to restore it?");
            System.out.println("1. Yes  |  2. No");
            int c = scanner.nextInt();
            if(c==1){
                if(rr.next()) {
                    int n=rr.getInt("cardnumber");
                    restore(n);
                }
            }
            else{
                return;
            }
        } else{
            System.out.println("Wrong username or password");
        }
        scanner.nextLine();
        db.disconnect();
    }

    @Override
    public void depo(int num, int sum) throws Exception {
        db.connect();
        int current=0;
        String bal = "select balance from users where cardnumber='"+num+"';";
        ResultSet r = db.result(bal);
        if(r.next()){
        current = r.getInt("balance");
        }
        int fin = current+sum;
        String balup = "update users set balance='"+fin+"' where cardnumber='"+num+"';";
        db.execute(balup);
        db.disconnect();
    }
    @Override
    public void withdraw(int num,int sum) throws SQLException {
        db.connect();
        int current=0;
        int fin =0;
        String bal = "select balance from users where cardnumber='"+num+"';";
        ResultSet r = db.result(bal);
        if(r.next()){
            current = r.getInt("balance");
        }
        if(current==0 || current<sum){
            System.out.println("ERROR: Low balance");
        }
        else{
            fin = current-sum;
            String baldown = "update users set balance='"+fin+"' where cardnumber='"+num+"';";
            db.execute(baldown);
        }
        db.disconnect();
    }
    @Override
    public void transfer(int num,int num1,int sum)throws Exception{
        //num1 = dest acc
        //num = current acc
        db.connect();
        String query = "select * from users where cardnumber='"+num1+"';";
        ResultSet r = db.result(query);
        if (r.next()){
            withdraw(num,sum);
            depo(num1,sum);
        }
        else{
            System.out.println("ERROR: User does not exist");
        }
        db.disconnect();
    }

    @Override
    public void delete(int num) throws Exception {
        db.connect();

        String getfromusers = "select * from users where cardnumber='"+num+"';";
        String addtofreeze = "";
        ResultSet r = db.result(getfromusers);
        if(r.next()){
            addtofreeze = "insert into frozen(id,username,password,cardnumber,balance) values('"+r.getInt("id")+"','"+r.getString("username")+"','"+r.getString("password")+"','"+r.getInt("cardnumber")+"','"+r.getInt("balance")+"');";
        }
        db.execute(addtofreeze);
        String cut = "delete from users where cardnumber='"+num+"';";
        db.execute(cut);
        System.out.println("Account deleted(to be able to use your account, it must be restored).");
        db.disconnect();




    }
    @Override
    public void restore(int num) throws Exception{
        db.connect();
        String getfromfrozen = "select * from frozen where cardnumber='"+num+"';";
        String addtoactive = "";
        ResultSet r = db.result(getfromfrozen);
        if(r.next()){
            addtoactive = "insert into users(id,username,password,cardnumber,balance) values('"+r.getInt("id")+"','"+r.getString("username")+"','"+r.getString("password")+"','"+r.getInt("cardnumber")+"','"+r.getInt("balance")+"');";
        }
        db.execute(addtoactive);
        String cut = "delete from frozen where cardnumber='"+num+"';";
        db.execute(cut);
        System.out.println("Account restored successfully.");
        db.disconnect();
    }

    @Override
    public void change_password(int num, String password) throws Exception {
        db.connect();
        String changep = "select password from users where cardnumber='"+num+"';";
        ResultSet r = db.result(changep);
        if(r.next()) {
            if (password.equals(r.getString("password"))) {
                System.out.println("Enter new password:");
                String newpassword = scanner.nextLine();
                String q = "update users set password='" + newpassword + "' where cardnumber='"+num+"';";
                db.execute(q);
            } else {
                System.out.println("ERROR: Wrong password");
            }
        }
        db.disconnect();
        myacc(num);

    }

    @Override
    public void change_username(int num, String name) throws Exception {
        db.connect();
        String changeu = "select username from users where cardnumber='"+num+"';";
        ResultSet r = db.result(changeu);
        if(r.next()){
            if(name.equals(r.getString("username"))){
                System.out.println("Enter new username:");
                String newusername = scanner.nextLine();
                String q = "update users set username='"+newusername+"' where cardnumber='"+num+"';";
                db.execute(q);
            }
        else{
            System.out.println("ERROR: Wrong username");
        }
        }
        db.disconnect();
        myacc(num);
    }

    @Override
    public int cardnumgen() throws Exception { //last created user card number (i wil use this to create next user and generate new card numbers)
        db.connect();
        String query = "select cardnumber from users order by id desc limit 1;";
        ResultSet r =db.result(query);
        int c=0;
        if(r.next()){
            c=r.getInt("cardnumber");
        }
        return c;
    }
}
