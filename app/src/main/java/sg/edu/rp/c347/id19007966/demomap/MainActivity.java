package sg.edu.rp.c347.id19007966.demomap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {

    Button buttonNormal, buttonTerrain, buttonSatellite;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng poi_CausewayPoint = new LatLng(1.436065, 103.786263);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_CausewayPoint, 15));

                UiSettings uiSettings = map.getUiSettings();
                uiSettings.setCompassEnabled(true);
                uiSettings.setZoomControlsEnabled(true);
            }
        });

        buttonNormal = findViewById(R.id.buttonNormal);
        buttonTerrain = findViewById(R.id.buttonTerrain);
        buttonSatellite = findViewById(R.id.buttonSatellite);

        buttonNormal.setOnClickListener(this::onClickOfBtn);
        buttonTerrain.setOnClickListener(this::onClickOfBtn);
        buttonSatellite.setOnClickListener(this::onClickOfBtn);
    }

    public void onClickOfBtn(View btn) {
        int id = btn.getId();
        int mapTypeId;
        switch (id) {
            case R.id.buttonNormal:
                mapTypeId = GoogleMap.MAP_TYPE_NORMAL;
                break;
            case R.id.buttonTerrain:
                mapTypeId = GoogleMap.MAP_TYPE_TERRAIN;
                break;
            case R.id.buttonSatellite:
                mapTypeId = GoogleMap.MAP_TYPE_SATELLITE;
                break;
            default:
                throw new IllegalStateException("Unexpected button id: " + id);
        }
        if (map != null) {
            map.setMapType(mapTypeId);
        }
    }
}