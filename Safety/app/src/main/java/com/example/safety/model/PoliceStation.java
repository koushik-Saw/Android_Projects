package com.example.safety.model;

public class PoliceStation {

    private String policeStationName;
    private String policeStationAddress;
    private String policeStationNumber;

    public PoliceStation() {
    }

    public PoliceStation(String policeStationName, String policeStationAddress, String policeStationNumber) {
        this.policeStationName = policeStationName;
        this.policeStationAddress = policeStationAddress;
        this.policeStationNumber = policeStationNumber;
    }

    public String getPoliceStationName() {
        return policeStationName;
    }

    public void setPoliceStationName(String policeStationName) {
        this.policeStationName = policeStationName;
    }

    public String getPoliceStationAddress() {
        return policeStationAddress;
    }

    public void setPoliceStationAddress(String policeStationAddress) {
        this.policeStationAddress = policeStationAddress;
    }

    public String getPoliceStationNumber() {
        return policeStationNumber;
    }

    public void setPoliceStationNumber(String policeStationNumber) {
        this.policeStationNumber = policeStationNumber;
    }
}
