package Bank;


import java.util.Scanner;

import Menu.*;
import utilpack.*;

public class bank_system {
     static utilinter foo = new utilmeth();
     static int chold;

     static {
         try {
             chold = foo.cardnumgen();
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
     }

     static int num;




    static Scanner scanner = new Scanner(System.in);


    public static void create_user() throws Exception{
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter passoword:");
        String password = scanner.nextLine();
        foo.create(username,password,chold+1);

        num = cardnumHolder.getCardNum(username,password);
        signedInMenu.menu();

    }
    public static void login() throws Exception{
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter passoword:");
        String password = scanner.nextLine();
        foo.signin(username,password);
        num = cardnumHolder.getCardNum(username,password);
        signedInMenu.menu();


    }
    public static void manage() throws Exception{
        System.out.println("1. Username  |  2. Password");
        int m = scanner.nextInt();
        scanner.nextLine();
        if(m==1){
            System.out.println("Enter current username:");
            String cname = scanner.nextLine();
            foo.change_username(num,cname);
        }
        else{
            System.out.println("Enter current password:");
            String cpass = scanner.nextLine();
            foo.change_password(num,cpass);
        }
    }
    public static void my_account()throws Exception{
        foo.myacc(num);
    }
    public static void deposit()throws Exception{
        System.out.println("Enter amount you want to deposit:");
        int sum = scanner.nextInt();
        scanner.nextLine();
        foo.depo(num,sum);
    }
    public static void withdraw()throws Exception{
        System.out.println("Enter amount you want to withdraw:");
        int sum = scanner.nextInt();
        scanner.nextLine();
        foo.withdraw(num,sum);

    }
    public static void transfer() throws Exception{
        System.out.println("Enter requisites:");
        int rec = scanner.nextInt();

        System.out.println("Enter amount of money:");
        int sum = scanner.nextInt();
        if(sum<0){
            System.out.println("Negative sum can't be sent");
            return;
        }
        else {foo.transfer(num,rec,sum);}
        scanner.nextLine();



    }
    public static void close_account() throws Exception{
        foo.delete(num);
        signedInMenu.menu();
    }
    public static void restore_account() throws Exception{
        foo.restore(num);
    }

}