package Menu;// last best ver: 10:20
import commandline.CommandExecutor;
import commandline.logIn;
import commandline.createAccount;
import commandline.commands;

import java.util.Scanner;

public class mainMenu {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        CommandExecutor executor = new CommandExecutor();
        commands command;

        int c;
        do{
            System.out.println("1. Sign in | 2. Register | 3. Close");
            c = scanner.nextInt();
            switch (c) {
                case 1 -> {
                    command = new logIn();
                }
                case 2 -> {
                    command = new createAccount();
                }
                default -> {command = null;}
            }
            scanner.nextLine();
            executor.setCommand(command);
            executor.executeCommand();
        }while (c!=3);



    }


}