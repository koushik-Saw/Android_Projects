package com.example.safety.helper;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

public final class LocationPermissionHelper {

    private static final int LOCATION_CODE = 123;
    private static final String LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;

    public static boolean hasLocationPermission(Activity activity){
        return activity.checkSelfPermission(LOCATION_PERMISSION) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestLocationPermission(Activity activity){
        activity.requestPermissions(new String[]{LOCATION_PERMISSION},LOCATION_CODE);
    }


}
