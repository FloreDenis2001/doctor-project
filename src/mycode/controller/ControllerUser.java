package mycode.controller;

import com.sun.source.tree.Tree;
import mycode.model.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class ControllerUser {

    private Map<User, TreeSet<Programare>> istoricProgramari;
    private List<User> users;
    private List<Programare> programares;
    private List<Enrolment> enrolments;

    public ControllerUser() {

        istoricProgramari = new TreeMap<>();
        users = new ArrayList<>();
        programares = new ArrayList<>();
        enrolments = new ArrayList<>();


        this.loadUser();
        this.loadEnrolment();
        this.populateTable();
    }

    public void populateTable() {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            TreeSet<Programare> programares1 = new TreeSet<>();
            for (int j = 0; j < enrolments.size(); j++) {
                if (user instanceof Doctor) {
                    if (user.getId() == enrolments.get(j).getDoctorId()) {
                        Programare x = enrolments.get(j).getProgramare();
                        programares1.add(x);
                    }
                }
                if (user instanceof Client) {
                    if (user.getId() == enrolments.get(j).getClientId()) {
                        Programare x = enrolments.get(j).getProgramare();
                        programares1.add(x);
                    }
                }
            }
            this.istoricProgramari.put(user, programares1);
        }
    }

    public void showProgramari(String nume, String prenume) {
        TreeSet<Programare> programaresshow = this.istoricProgramari.get(new User(nume, prenume));
        Iterator<Programare> it = programaresshow.iterator();
        while (it.hasNext()) {
            Programare x = it.next();
            System.out.println(x + "\n");
        }

    }

    public void loadUser() {
        try {
            File f = new File("C:\\Users\\flore\\Desktop\\mycode\\LocalDateTime\\src\\mycode\\res\\user");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                switch (line.split(",")[5]) {
                    case "doctor":
                        this.users.add(new Doctor(line));
                        break;
                    case "secretara":
                        this.users.add(new Secretara(line));
                        break;
                    case "client":
                        this.users.add(new Client(line));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEnrolment() {
        try {
            File f = new File("C:\\Users\\flore\\Desktop\\mycode\\LocalDateTime\\src\\mycode\\res\\enrolment");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                this.enrolments.add(new Enrolment(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //todo verificare account
    public User verifyAccount(String password, String username) {
        Iterator<User> userIterator = this.istoricProgramari.keySet().iterator();
        while (userIterator.hasNext()) {
            User x = userIterator.next();
            if (x.getPassword().compareTo(password) == 0) {
                if (x.getUsername().compareTo(username) == 0) {
                    return x;
                }
            }
        }
        return null;
    }

    //todo anulare programare
    public void removeProgramare(int id, Programare p) {
        User t = findById(id);
        if (t instanceof Doctor) {
            this.istoricProgramari.get(t).remove(p);
        }
    }

    //todo arata programarea  din ziua respectiva
    public List<Programare> programriZi(User x, LocalDate date) {
        TreeSet<Programare> programares = this.istoricProgramari.get(x);
        List<Programare> programari = new ArrayList<>();
        for (Programare t : programares) {

            if (t instanceof Blocare == false) {
                if (t.getDataInceput().getDayOfYear() == date.getDayOfYear()) {

                    programari.add(t);

                }
            }
        }

        Collections.sort(programari);
        return programari;
    }

    //todo metoda ce returneaza  locurile  libere din program
    public List<Programare> oreLibere(User x, LocalDate date) {
        List<Programare> oreOcupate = programriZi(x, date);
        List<Programare> oreFree = new ArrayList<>();

        LocalDateTime inceput = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 9, 00);


        for (int i = 0; i < oreOcupate.size(); i++) {
            oreFree.add(new Liber(inceput, oreOcupate.get(i).getDataInceput()));
            inceput = oreOcupate.get(i).getDataSfarsit();
        }


        oreFree.add(new Liber(inceput, LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 17, 00)));


        return oreFree;
    }

    //todo nextIdEnrolment
    public int nextEnrolmentId() {
        return this.enrolments.size() + 1;
    }


    //todo metoda blocare ore

    public void blocareOre(User x, LocalDate date, int oraInceput, int oraFinal) {
        LocalDateTime inceputBlocare = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), oraInceput, 00);
        LocalDateTime sfarsitBlocare = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), oraFinal, 00);
        Blocare p = new Blocare(inceputBlocare, sfarsitBlocare);
        addBlocareToDoctor(x.getId(), p);
    }


    //todo Blocare Program Doctor
    public void addBlocareToDoctor(int id, Blocare b) {
        User x = findById(id);
        if (x instanceof Doctor) {
            this.istoricProgramari.get(x).add(b);
        }
    }


    //todo findById
    public User findById(int id) {
        for (User x : users) {
            if (x.getId() == id) {
                return x;
            }
        }
        return null;
    }

    //todo ADD PROGRAMARE
    public void addProgramareToDoctor(int id, Programare p) {
        User t = findById(id);
        if (t instanceof Doctor) {
            this.istoricProgramari.get(t).add(p);
        }
    }

    //todo verificare programare
    public boolean verifyProgramare(int id, Programare t) {
        User x = findById(id);
        TreeSet<Programare> programares = this.istoricProgramari.get(x);
        for (Programare p : programares) {
            if (p.getDataInceput().equals(t.getDataInceput())) {
                if (p.getDataSfarsit().equals(t.getDataSfarsit())) {
                    return true;
                }

            }
        }
        return false;
    }


    //todo verificare blocare
    public boolean verifyBlocare(int id, Blocare t) {
        User x = findById(id);
        TreeSet<Programare> programares = this.istoricProgramari.get(x);
        for (Programare p : programares) {
            if (p.getDataInceput().equals(t.getDataInceput())) {
                if (p.getDataSfarsit().equals(t.getDataSfarsit())) {
                    return true;
                }

            }
        }
        return false;
    }

   //todo filtrareProgramari 3/6/12 luni
    public List<Programare> filtrareProgramari(User x ,long p) {
        TreeSet<Programare> programareTreeSet = this.istoricProgramari.get(x);
        List<Programare> programareList = new ArrayList<>();
        LocalDateTime time=LocalDateTime.now();
        for (Programare t : programareTreeSet) {
//        if(t.getDataInceput().getMonth().compareTo(time.minusMonths(p))>0);
        }
        return programareList;
    }
}




