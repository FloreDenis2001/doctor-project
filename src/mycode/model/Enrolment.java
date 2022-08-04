package mycode.model;

import mycode.execeptii.ExceptieProgramareLimita;

public class Enrolment implements Comparable<Enrolment> {
    private int enrolId;
    private int doctorId;
    private int clientId;
    private Programare programare;

    public Enrolment(int enrolId, int doctorId, int clientId, Programare programare) {
        this.enrolId = enrolId;
        this.doctorId = doctorId;
        this.clientId = clientId;
        this.programare = programare;
    }

    public Enrolment(String text) throws ExceptieProgramareLimita {
        String[] path = text.split("-");
        this.enrolId = Integer.parseInt(path[0]);
        this.doctorId = Integer.parseInt(path[1]);
        this.clientId = Integer.parseInt(path[2]);
        this.programare = new Programare(path[3], path[4]);
    }

    public int getEnrolId() {
        return enrolId;
    }

    public void setEnrolId(int enrolId) {
        this.enrolId = enrolId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Programare getProgramare() {
        return programare;
    }

    public void setProgramare(Programare programare) {
        this.programare = programare;
    }

    @Override
    public String toString() {
        String text = "Enrol Id : " + this.enrolId + "\n";
        text += "Doctor Id : " + this.doctorId + "\n";
        text += "Client Id : " + this.clientId + "\n";
        text += " " + this.programare + "\n";
        return text;
    }

    @Override
    public boolean equals(Object o) {
        return this.doctorId == ((Enrolment) o).getDoctorId();
    }


    @Override
    public int compareTo(Enrolment o) {
        if (this.programare.getDataInceput().compareTo(o.getProgramare().getDataInceput()) > 0) {
            return 1;
        } else if (this.programare.getDataInceput().compareTo(o.getProgramare().getDataInceput()) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
