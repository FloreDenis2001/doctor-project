package mycode.view;

import mycode.controller.ControllerUser;
import mycode.model.Client;
import mycode.model.Programare;
import mycode.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ViewClient {
    private Client client;
    private Scanner scanner;
    private ControllerUser controllerUser;

    public ViewClient() {
        this.client = new Client("Iancu", "Rares");
        scanner = new Scanner(System.in);
        controllerUser = new ControllerUser();
    }

    public void meniu() {
        System.out.println("---------------------Meniu------------------");

        System.out.println("1. Pentru a face o programare noua");
        System.out.println("2. Pentru a anula o programare");
        System.out.println("3. Pentru a vedea istoricu programarilor ");
        System.out.println("4. Pentru a filtra istoricu ");
        System.out.println("5. Pentru delogare");

        System.out.println("--------------------------------------------");

    }

    public void play() {
        boolean running = true;
        int choose;
        while (running) {
            meniu();
            choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1:
                    adaugaProgramare();
                    break;
                case 2:
                    anuleazaProgramare();
                    break;
                case 3:
                    controllerUser.showProgramari(client.getLastName(), client.getFirstName());
                    break;
                case 4:
                    //todo arata programari in functie de perioada 3/6/12 luni
                    filtruprogramare();
                    break;
                case 5:
                    running = false;
                    break;
            }
        }
    }
public void anuleazaProgramare(){
    System.out.println("Data Programarii : ");
    int an = Integer.parseInt(scanner.nextLine());
    int luna = Integer.parseInt(scanner.nextLine());
    int zi = Integer.parseInt(scanner.nextLine());
    int hh = Integer.parseInt(scanner.nextLine());
    int min = Integer.parseInt(scanner.nextLine());
    LocalDateTime localDateTime=LocalDateTime.of(an,luna,zi,hh,min);
    Programare p=new Programare(localDateTime,LocalDateTime.now());
    controllerUser.removeProgramare(client.getId(),p);
}
    public void filtruprogramare() {
        System.out.println("Alege perioada 3/6/9 : ");
        int choose = Integer.parseInt(scanner.nextLine());

        List<Programare> filtru = controllerUser.filtrareProgramari(client, choose);
        for (Programare x : filtru) {
            System.out.println(x);
        }
    }

    public void adaugaProgramare() {
        System.out.println("Selecteaza doctorul : ");
        System.out.println("\nNume : ");
        String nume = scanner.nextLine();
        System.out.println("\nPrenume: ");
        String prenume = scanner.nextLine();
        User t = new User(nume, prenume);
        System.out.println("\nSelecteaza anul : ");
        int an = Integer.parseInt(scanner.nextLine());
        System.out.println("Luna : ");
        int luna = Integer.parseInt(scanner.nextLine());
        System.out.println("Ziua :");
        int zi = Integer.parseInt(scanner.nextLine());

        List<Programare> orelib = controllerUser.oreLibere(t, LocalDate.of(an, luna, zi));

        for (Programare x : orelib) {
            System.out.println(x);
        }


        System.out.println("Selecteaza ora dorita : ");
        int oraDorita = Integer.parseInt(scanner.nextLine());
        System.out.println("Selecteaza ora final : ");
        int orafinal = Integer.parseInt(scanner.nextLine());

        controllerUser.addProgramareToDoctor(t.getId(), new Programare(LocalDateTime.of(an, luna, zi, oraDorita, 00), LocalDateTime.of(an, luna, zi, orafinal, 00)));

    }


}
