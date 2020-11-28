package com.example.n2;

public class ObjectDatabase {
    private String ID, TYPE , SIZE, STATUS, PHONE;
    private double LATITUDE, LONGITUDE;

    public String getID() {
        return ID;
    }

    public double getLATITUDE() {
        return LATITUDE;
    }

    public double getLONGITUDE() {
        return LONGITUDE;
    }

    public String getPHONE() {
        return PHONE;
    }

    public String getSIZE() {
        return SIZE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setLATITUDE(double LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    public void setLONGITUDE(double LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public void setSIZE(String SIZE) {
        this.SIZE = SIZE;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public final String toString(){
        String ans = "Imovel: " + this.TYPE + ", Tamanho: " + this.SIZE
                + ", Contato: " + this.PHONE + ", (" + this.STATUS + ")";
        return ans;
    }
}
