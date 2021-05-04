package com.example.safety.helper;

import android.location.Location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LocationViewModel extends ViewModel {

    private MutableLiveData<Location> locationMutableLiveData = new MutableLiveData<>();

    public void setLocationMutableLiveData(Location location){
        locationMutableLiveData.setValue(location);

    }

    public MutableLiveData<Location> getLocationMutableLiveData() {
        return locationMutableLiveData;
    }
}
