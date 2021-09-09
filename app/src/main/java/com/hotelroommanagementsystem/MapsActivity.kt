package com.hotelroommanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hotelroommanagementsystem.Model.LatitudeLongitude

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var lstLatitudeLongitude = ArrayList<LatitudeLongitude>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    //Load multiple locations
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        lstLatitudeLongitude.add(LatitudeLongitude(27.743862949263193, 85.37717372249253, "Hamro Book Shop"))

        for (location in lstLatitudeLongitude) {
            mMap.addMarker(
                MarkerOptions().position(LatLng(location.latitude, location.longitude))
                    .title(location.markerName)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            )
        }
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(LatLng(27.743862949263193, 85.37717372249253),16F), 4000, null
        )
        mMap.uiSettings.isZoomControlsEnabled = true
    }
}