package com.example.magiclaundry.Map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.magiclaundry.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_map, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapview) as SupportMapFragment

        mapFragment.getMapAsync(this)

        // Inflate the layout for this fragment

        return rootView
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val marker = LatLng(37.56, 126.97)
        mMap.addMarker(MarkerOptions()
            .position(marker)
            .title("현재 위치(가상)")
            .snippet("가상의 현재 주소입니다."))
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(15F))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(37.56, 126.97), 15f)
        mMap.moveCamera(cameraUpdate)
    }
}