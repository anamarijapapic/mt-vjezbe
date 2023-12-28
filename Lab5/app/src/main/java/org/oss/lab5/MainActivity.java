package org.oss.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.Manifest;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.Priority;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private TextView permissionStatus, longitude, latitude, address, city, country;
    private static final int REQUEST_CODE = 100;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionStatus = findViewById(R.id.permissionStatus);
        longitude = findViewById(R.id.longitude);
        latitude = findViewById(R.id.latitude);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);

        // 1. Check if Google Play Services are available
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (status != com.google.android.gms.common.ConnectionResult.SUCCESS) {
            googleApiAvailability.getErrorDialog(this, status, REQUEST_CODE).show();
            Log.d("MainActivity", "Google Play Services not available");
        }
        else {
            Log.d("MainActivity", "Google Play Services available");
        }

        // 2. Check if location permission is granted, if not request it
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("MainActivity", "Location permission not granted");
            permissionStatus.setText(R.string.location_permission_not_granted);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        }
        else {
            Log.d("MainActivity", "Location permission already granted");
            permissionStatus.setText(R.string.location_permission_already_granted);
            setupLocationTracking();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, R.string.location_permission_granted, Toast.LENGTH_SHORT).show();
            permissionStatus.setText(R.string.location_permission_granted);
            setupLocationTracking();
        }
        else{
            Toast.makeText(this, R.string.location_permission_not_granted, Toast.LENGTH_SHORT).show();
            permissionStatus.setText(R.string.location_permission_not_granted);
        }
    }

    @SuppressLint("MissingPermission")
    private void setupLocationTracking() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        geocoder = Geocoder.isPresent() ? new Geocoder(this) : null;

        // 3. Get last known location
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null) {
                Log.d("MainActivity", "Location: " + location);
                longitude.setText(String.valueOf(location.getLongitude()));
                latitude.setText(String.valueOf(location.getLatitude()));
                if (geocoder != null) {
                    try {
                        address.setText(geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0).getAddressLine(0));
                        city.setText(geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0).getLocality());
                        country.setText(geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0).getCountryName());
                    }
                    catch (Exception e) {
                        Log.d("MainActivity", "Exception: " + e.getMessage());
                    }
                }
            }
            else {
                Log.d("MainActivity", "Location is null");
            }
        });

        // 4. Configure settings for location updates - high accuracy, 10 seconds interval
        locationRequest = new LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                10000)
                .setWaitForAccurateLocation(false)
                .setMinUpdateIntervalMillis(5000)
                .build();

        // 5. Check device settings and prompt user to enable them if needed
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, locationSettingsResponse -> {
            // All location settings are satisfied. Initialize location requests here.
            Log.d("MainActivity", "Location settings satisfied");

            // 6. Subscribe to location updates
            fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    Log.d("MainActivity", "Location: " + locationResult.getLastLocation().toString());
                    longitude.setText(String.valueOf(locationResult.getLastLocation().getLongitude()));
                    latitude.setText(String.valueOf(locationResult.getLastLocation().getLatitude()));
                    if (geocoder != null) {
                        try {
                            address.setText(geocoder.getFromLocation(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude(), 1).get(0).getAddressLine(0));
                            city.setText(geocoder.getFromLocation(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude(), 1).get(0).getLocality());
                            country.setText(geocoder.getFromLocation(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude(), 1).get(0).getCountryName());
                        }
                        catch (Exception e) {
                            Log.d("MainActivity", "Exception: " + e.getMessage());
                        }
                    }
                }
            }, null);
        });

        task.addOnFailureListener(this, e -> {
            Log.d("MainActivity", "Location settings not satisfied");
            Log.d("MainActivity", "Exception: " + e.getMessage());
            if (e instanceof ResolvableApiException) {
                // Show the user a dialog.
                try {
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    resolvable.startResolutionForResult(MainActivity.this, REQUEST_CODE);
                } catch (IntentSender.SendIntentException sendEx) {
                    // Ignore the error.
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // The user accepted the location settings change. Proceed with location updates.
                setupLocationTracking();
            } else {
                // The user canceled the location settings change. Do nothing.
            }
        }
    }
}
