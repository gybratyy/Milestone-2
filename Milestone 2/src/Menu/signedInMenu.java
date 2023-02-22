package Menu;

import commandline.*;

import java.util.Scanner;

public class signedInMenu {
    static Scanner scanner = new Scanner(System.in);
    public static void menu() throws Exception {
        CommandExecutor executor = new CommandExecutor();
        commands command=null;

        int ch;

        do{
            System.out.println("1. My account");
            System.out.println("2. Manage Profile and Password");
            System.out.println("3. Deposit the amount");
            System.out.println("4. Withdraw amount");
            System.out.println("5. Transfer the amount");
            System.out.println("6. Close account");
            System.out.println("7. Log out");

            ch = scanner.nextInt();

            switch (ch) {
                case 1 -> command = new myProfile();
                case 2 -> command = new manageAccount();
                case 3 -> command = new deposit();
                case 4 -> command = new withdraw();
                case 5 -> command = new transfer();
                case 6 -> command = new closeAccount();
                   /* return;*/
            }

            scanner.nextLine();
            executor.setCommand(command);
            executor.executeCommand();
        }while(ch!=7);
    }
}
