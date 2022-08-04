package mycode.model;

import mycode.execeptii.ExceptieProgramareLimita;

import javax.sound.sampled.Port;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Programare implements Comparable<Programare> {
    protected LocalDateTime dataInceput;
    protected LocalDateTime dataSfarsit;

    public Programare(LocalDateTime dataInceput, LocalDateTime dataSfarsit) {
        this.dataInceput = dataInceput;
        this.dataSfarsit = dataSfarsit;
    }

    public Programare(String dateDeInceput, String dateDeSfarsit) throws ExceptieProgramareLimita {
        int yy = Integer.parseInt(dateDeInceput.split(",")[0]);
        int mm = Integer.parseInt(dateDeInceput.split(",")[1]);
        int dd = Integer.parseInt(dateDeInceput.split(",")[2]);
        int hh = Integer.parseInt(dateDeInceput.split(",")[3]);
        int min = Integer.parseInt(dateDeInceput.split(",")[4]);


        int yys = Integer.parseInt(dateDeSfarsit.split(",")[0]);
        int mms = Integer.parseInt(dateDeSfarsit.split(",")[1]);
        int dds = Integer.parseInt(dateDeSfarsit.split(",")[2]);
        int hhs = Integer.parseInt(dateDeSfarsit.split(",")[3]);
        int mins = Integer.parseInt(dateDeSfarsit.split(",")[4]);

        if (LocalDateTime.of(yy, mm, dd, hh, min).compareTo(LocalDateTime.of(yys, mms, dds, hhs, mins)) < 0) {
            this.dataInceput = LocalDateTime.of(yy, mm, dd, hh, min);
            this.dataSfarsit = LocalDateTime.of(yys, mms, dds, hhs, mins);
        } else {
            throw new ExceptieProgramareLimita("Datele nu sunt valide ! ");
        }
    }


    public LocalDateTime getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(LocalDateTime dataInceput) {
        this.dataInceput = dataInceput;
    }

    public LocalDateTime getDataSfarsit() {
        return dataSfarsit;
    }

    public void setDataSfarsit(LocalDateTime dataSfarsit) {
        this.dataSfarsit = dataSfarsit;
    }


    public Duration getDuration() {
        return Duration.between(dataInceput, dataSfarsit);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy HH:mm:ss a");
        if (LocalDateTime.now().compareTo(dataSfarsit) < 0) {
            String text = "Programrea incepe la : " + dataInceput.format(formatter) + "\n";
            text += "Se sfarseste la : " + dataSfarsit.format(formatter) + "\n";
            text += "Dureaza :" + this.getDuration().toMinutes() + " minutes";
            return text;
        } else {
            String text = "A inceput  la : " + dataInceput.format(formatter) + "\n";
            text += "S-a sfarsit la : " + dataSfarsit.format(formatter) + "\n";
            text += "Dureaza :" + this.getDuration().toMinutes() + " minutes";
            return text;
        }

    }

    @Override
    public boolean equals(Object o) {
        Programare x = (Programare) o;
        if (x.getDataInceput().compareTo(this.dataSfarsit) > 0) {
            return false;
        }
        if (x.getDataSfarsit().compareTo(this.dataInceput) < 0) {
            return false;
        }
        return true;
    }


    @Override
    public int compareTo(Programare o) {

        if (this.equals(o)) {

            return 0;
        }
        if (this.getDataInceput().compareTo(o.getDataInceput()) > 0) {
            return 1;
        } else if (this.getDataInceput().compareTo(o.getDataInceput()) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
