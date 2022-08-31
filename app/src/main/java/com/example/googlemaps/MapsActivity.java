package com.example.googlemaps;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemaps.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        GoogleMapOptions options = new GoogleMapOptions();
        options.mapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = SupportMapFragment.newInstance(options);
        getSupportFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();

        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ifto = new LatLng(-10.19916, -48.31166);
        mMap.addMarker(new MarkerOptions()
                        .position(ifto)
                        .title("IFTO - Instituto Federal do Tocantins - Palmas"))
                .setIcon(BitmapDescriptorFactory
                        .fromResource(R.drawable.pino_de_localizacao__2_));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ifto));

        LatLng iftoArg = new LatLng(-7.1814258, -48.1959622);
        mMap.addMarker(
                new MarkerOptions()
                        .position(iftoArg)
                        .title("Araguaína")
                        .draggable(true)
                        .snippet("IFTO - Instituto Federal do Tocantins - Araguaína")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(0);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(iftoArg, 5));
        mMap.setMinZoomPreference(5);
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Integer count = (Integer) marker.getTag();
        if (count != null) {
            count++;
            marker.setTag(count);
            marker.setTitle("Marcador clicado " + count + " vezes");
        }

        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}