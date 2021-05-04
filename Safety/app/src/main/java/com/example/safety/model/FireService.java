package com.example.safety.model;

public class FireService {

    private String fireAddress;
    private String fireNumber;

    public FireService() {
    }

    public FireService(String fireAddress, String fireNumber) {
        this.fireAddress = fireAddress;
        this.fireNumber = fireNumber;
    }

    public String getFireAddress() {
        return fireAddress;
    }

    public void setFireAddress(String fireAddress) {
        this.fireAddress = fireAddress;
    }

    public String getFireNumber() {
        return fireNumber;
    }

    public void setFireNumber(String fireNumber) {
        this.fireNumber = fireNumber;
    }
}
