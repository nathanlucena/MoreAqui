package com.example.n2;

public class ObjectData {
    private String ID, TYPE , SIZE, STATUS, LATITUDE, LONGITUDE, PHONE;

    public String getID() {
        return ID;
    }

    public String getLATITUDE() {
        return LATITUDE;
    }

    public String getLONGITUDE() {
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

    public void setLATITUDE(String LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    public void setLONGITUDE(String LONGITUDE) {
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
