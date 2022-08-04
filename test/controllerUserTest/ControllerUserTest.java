package controllerUserTest;

import mycode.controller.ControllerUser;
import mycode.execeptii.ExceptieProgramareLimita;
import mycode.model.Blocare;
import mycode.model.Doctor;
import mycode.model.Programare;
import mycode.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ControllerUserTest {
    @Test
    public void afisareProgramari() throws ExceptieProgramareLimita {
        ControllerUser controllerUser = new ControllerUser();

//        controllerUser.showProgramari("Zimbru","Flaviu");

//        System.out.println("//////////////////////////////////////////");


//        controllerUser.blocareOre(new User("Flore", "Denis"), LocalDate.of(2019, 11, 12), 9, 10);

//        System.out.println("//////////////////////////////////////////");

        /*List<Programare> programareTreeSet = controllerUser.programriZi(new User("Flore", "Denis"), LocalDate.of(2019, 11, 12));
        for (Programare t : programareTreeSet) {
            System.out.println(t + "\n");
        }*/

//        System.out.println("///////////////////////////////////////////");

        /*List<Programare> oreLibereTest = controllerUser.oreLibere(new User("Flore", "Denis"), LocalDate.of(2019, 11, 12));
        for (Programare x : oreLibereTest) {
            System.out.println(x + "\n");
        }*/


        TreeSet<Programare> programares=new TreeSet<>();
        programares.add(new Programare("2021,10,10,13,45","2021,10,10,15,00"));
         assertEquals(false, programares.add(new Programare("2021,10,10,13,45","2021,10,10,15,00")));
        assertThrows(ExceptieProgramareLimita.class,()-> programares.add(new Programare("2021,10,10,17,45","2021,10,10,15,00")));
    }


    @Test
    public void addProgramareTest() throws ExceptieProgramareLimita{
        ControllerUser controllerUser=new ControllerUser();
        controllerUser.addProgramareToDoctor(1,new Programare("2022,07,31,12,00","2022,07,31,14,15"));
        assertEquals(true,controllerUser.verifyProgramare(1,new Programare("2022,07,31,12,00","2022,07,31,14,15")));
    }

    @Test
    public void blocareProgramareTest() throws  ExceptieProgramareLimita{
        ControllerUser controllerUser=new ControllerUser();
        controllerUser.addBlocareToDoctor(1,new Blocare("2022,07,31,12,00","2022,07,31,14,15"));
        assertEquals(true,controllerUser.verifyBlocare(1,new Blocare("2022,07,31,12,00","2022,07,31,14,15")));
    }

    @Test
    public void blocareOreTest()throws ExceptieProgramareLimita{
        ControllerUser controllerUser=new ControllerUser();
        controllerUser.addProgramareToDoctor(1,new Programare("2022,07,31,14,00","2022,07,31,16,15"));
        controllerUser.programriZi(new User("Flore","Denis"),LocalDate.of(2022,07,31));
        controllerUser.blocareOre(controllerUser.findById(1),LocalDate.of(2022,07,31),10,11);
        assertEquals(true,controllerUser.verifyBlocare(1,new Blocare("2022,07,31,10,00","2022,07,31,11,00")));
    }

    @Test
    public void removeTest() throws ExceptieProgramareLimita{
        ControllerUser controllerUser=new ControllerUser();
        controllerUser.removeProgramare(7,new Programare("2022,11,12,13,20","2022,11,12,14,35"));
        assertEquals(false,controllerUser.verifyProgramare(7,new Programare("2022,11,12,13,20","2022,11,12,14,35")));
    }




}