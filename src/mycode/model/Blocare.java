package mycode.model;

import mycode.execeptii.ExceptieProgramareLimita;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Blocare extends Programare{
    public Blocare(LocalDateTime dataInceput, LocalDateTime dataSfarsit) {
        super(dataInceput, dataSfarsit);
    }

    public Blocare(String dateDeInceput, String dateDeSfarsit) throws ExceptieProgramareLimita {
        super(dateDeInceput, dateDeSfarsit);
    }

    @Override
    public String toString(){   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy HH:mm:ss a");

        String text = "Blocat de la : " + dataInceput.format(formatter) + "\n";
        text += "Pana la : " + dataSfarsit.format(formatter) + "\n";
        text += "Dureaza :" + this.getDuration().toMinutes() + " minutes";


        return text;}
}
