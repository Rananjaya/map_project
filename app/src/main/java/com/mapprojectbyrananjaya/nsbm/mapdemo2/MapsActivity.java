package com.mapprojectbyrananjaya.nsbm.mapdemo2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
//import android.location.LocationListener;

import com.google.android.gms.location.LocationListener;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;

    private GoogleApiClient Client;
    private LocationRequest LocationRequest;
    private Location lastlocation;
    private Marker currentlocationMarker;
    public static final int REQUEST_LOCATION_CODE=99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){

            //call check location permisson mathod
            checkLocationPermission();
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case REQUEST_LOCATION_CODE:
                if(grantResults.length > 0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED){

                    //peermisson is granted
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){

                        if(Client==null){
                            buildGoogleApiClient();//call the method buildgooleapi
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                }
                else{//permission is denied this will occur

                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_LONG).show();
                }
                return;
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
 mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);

        }

        // Add a marker to NSBM Green university bulidings

        LatLng NSBMMAIN = new LatLng(6.820878, 80.040652);
        LatLng schcom = new LatLng(6.819995, 80.039379);
        LatLng schbus = new LatLng(6.820606, 80.039003);
        LatLng swimpool = new LatLng(6.821929, 80.039895);
        LatLng Admin = new LatLng(6.821168, 80.039568);
        LatLng VsitiersPark = new LatLng(6.821769, 80.041541);
        LatLng scheng = new LatLng(6.821099, 80.039018);
        LatLng cantieen= new LatLng(6.821206,80.037957);
        LatLng hostal= new LatLng(6.821019,80.038174);
        LatLng gate= new LatLng(6.821415,80.041543);
        LatLng lib= new LatLng(6.820636,80.039766);
        LatLng plg= new LatLng(6.822286,80.040752);


        mMap.addMarker(new MarkerOptions()
                .position(NSBMMAIN).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title("NSBM Main Building"));
        mMap.addMarker(new MarkerOptions()
                .position(schcom)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title("NSBM School Of Computing"));
        mMap.addMarker(new MarkerOptions()
                .position(schbus)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title("NSBM School Of Business"));
        mMap.addMarker(new MarkerOptions().
                position(swimpool)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title("NSBM Swiming pool"));
        mMap.addMarker(new MarkerOptions().
                position(Admin)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title("NSBM Administration"));
        mMap.addMarker(new MarkerOptions().
                position(VsitiersPark)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title("NSBM Visiters Car Park"));
        mMap.addMarker(new MarkerOptions()
                .position(scheng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title("NSBM School of Engineering"));
        mMap.addMarker(new MarkerOptions()
                .position(cantieen)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title("NSBM Main Canteen"));
        mMap.addMarker(new MarkerOptions()
                .position(hostal)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title("NSBM Main Hostal"));
        mMap.addMarker(new MarkerOptions()
                .position(gate)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title("NSBM Main Hostal"));
        mMap.addMarker(new MarkerOptions()
                .position(lib)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title("NSBM Laibary"));
        mMap.addMarker(new MarkerOptions()
                .position(plg)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title("NSBM Playgrouund"));

        //very imp


       // mMap.addMarker(new MarkerOptions().position(loc).title("NSBM school of computing"));
       mMap.moveCamera(CameraUpdateFactory.newLatLng(plg));
        //zoom location
       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(6.819995, 80.039379), 17.0f));
    }
    public void onClick(View v)
    {



        switch(v.getId())
        {
            case R.id.B_search:
                EditText tf_location =  findViewById(R.id.TF_location);
                String location = tf_location.getText().toString();
                List<Address> addressList;


                if(!location.equals(""))
                {
                    Geocoder geocoder = new Geocoder(this);

                    try {
                        addressList = geocoder.getFromLocationName(location, 5);

                        if(addressList != null)
                        {
                            for(int i = 0;i<addressList.size();i++)
                            {
                                LatLng latLng = new LatLng(addressList.get(i).getLatitude() , addressList.get(i).getLongitude());
                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(latLng);
                                markerOptions.title(location);
                                mMap.addMarker(markerOptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;

        }
    }
    protected synchronized void buildGoogleApiClient(){

        Client= new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        Client.connect();

    }

    @Override
    public void onLocationChanged(Location location) {//that method passing the location the user enterd
        //get the location and that is assien to lastlocation veriyable
        lastlocation=location;
        if(currentlocationMarker != null){
            // / check the markers is avilabe in map and remove the marker that is avilabe in map

         currentlocationMarker.remove();// that is remove marker from map.
        }

        //get Lat and Lon of current location
        LatLng LatLang = new LatLng(location.getLatitude(),location.getLongitude());

        MarkerOptions markerOptions=new MarkerOptions();// create new marker option
        markerOptions.position(LatLang);//set postion
        markerOptions.title("your current Location");//set title
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));//set icon and color

        //assing above values to current location marker.
        currentlocationMarker=mMap.addMarker(markerOptions);

     //move the camera to location

       // mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLang));
        //zoom the camera zoom value is 10
       // mMap.animateCamera(CameraUpdateFactory.zoomBy(10));


        if(Client != null){// stop the location updates after setting current location that location

            LocationServices.FusedLocationApi.removeLocationUpdates(Client,this);

        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

      LocationRequest=new LocationRequest();
      //set Intervel in milisecondes
        LocationRequest.setInterval(1000);
        LocationRequest.setFastestInterval(1000);
        LocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);


        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
        //fused location api =to get current location of the user.
        LocationServices.FusedLocationApi.requestLocationUpdates(Client,LocationRequest,this);


        }

    }
    public boolean checkLocationPermission(){//check the location permisson if the location is granted

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
///if this is true it is needs to rquest permission
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_CODE);

            }
            else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_CODE);
            }

        }
        return false;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
