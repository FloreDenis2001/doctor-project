package programareTest;

import mycode.execeptii.ExceptieProgramareLimita;
import mycode.model.Programare;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//"dd,mm,yyyy,hh,mm"
class ProgramareTest {

    @Test
    public void programareTest() throws ExceptieProgramareLimita {
        String dataDeInceput="14,12,2001,14,30";
        String dataDeSfarsit="17,12,2001,14,30";
        Programare programare=new Programare(dataDeInceput,dataDeSfarsit);


        String dataDeInceput2="17,12,2001,14,35";
        String dataDeSfarsit2="20,12,2001,12,20";
        Programare programare1=new Programare(dataDeInceput2,dataDeSfarsit2);

        System.out.println(programare.equals(programare1));
        System.out.println(programare1);
        System.out.println(programare);

        System.out.println(programare.compareTo(programare1));

    }

}