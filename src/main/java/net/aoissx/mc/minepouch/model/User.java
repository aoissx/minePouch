package net.aoissx.mc.minepouch.model;

public class User {
    private String uuid;
    private int size;

    public User(String uuid, int size){
        this.uuid = uuid;
        this.size = size;
    }

    public User(){

    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
