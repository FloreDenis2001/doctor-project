package mycode.view;

import mycode.controller.ControllerUser;

import java.util.Scanner;


public class ViewLogin {
    private ControllerUser controllerUsers;
    private Scanner scanner;

    public ViewLogin() {
        this.controllerUsers = new ControllerUser();
        this.scanner = new Scanner(System.in);
    }

    public void meniu() {
        System.out.println("-------------------LOGIN-----------------");
        System.out.println("          Apasa 1 pentru a te loga");
        System.out.println("          Apasa 2 pentru a iesi");
        System.out.println("-----------------------------------------");
    }

    public void play() {
        boolean running = true;
        int choose;
        while (running) {
            meniu();
            choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1:
                    login();
                    break;
                case 2:
                    running = false;
                    break;
            }
        }
    }

    public void login() {
        System.out.println("Introduceti username-ul : ");
        String username = scanner.nextLine();
        System.out.println("Introduceti parola : ");
        String password = scanner.nextLine();
//      User x = controlerUsers.verifyAccount(password, username);
//        if (x instanceof Client) {
//            ViewClient client = new ViewClient((Client) x);
//            client.play();
//        }
    }

}
