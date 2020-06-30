package com.example.magiclaundry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.example.magiclaundry.Auth.LoginActivity
import com.example.magiclaundry.Auth.MyActivity
import com.example.magiclaundry.Favorite.FavoriteActivity
import com.example.magiclaundry.Map.MapFragment
import com.example.magiclaundry.Map.MapsActivity
import com.example.magiclaundry.detailFragment.FragmentAapter
import com.example.magiclaundry.detailFragment.detailActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.list_viewpager

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    internal lateinit var viewpager: ViewPager
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mapFragment = MapFragment()
        supportFragmentManager.beginTransaction().replace(R.id.map_content,mapFragment).commit()

        val fragmentAapter  = FragmentAapter(supportFragmentManager)
        list_viewpager.adapter = fragmentAapter
        bottom_navigation.setOnNavigationItemSelectedListener(this)
        auth = FirebaseAuth.getInstance()

        val img = arrayOf(
            R.drawable.ic_water,
            R.drawable.ic_steam,
            R.drawable.ic_tailor,
            R.drawable.ic_shirts,
            R.drawable.ic_outdoor,
            R.drawable.ic_laundry_machine
        )
        val text = arrayOf(
            "물세탁",
            "다림질",
            "옷 수선",
            "와이 셔츠",
            "아웃도어",
            "특수 세탁"
        )

        val gridviewAdapter = GridviewAdapter(this, img, text)
        gridview.adapter = gridviewAdapter

        gridview.setOnItemClickListener{
            adapterView, View ,i , l ->
            var intent = Intent(this, detailActivity::class.java)
            startActivity(intent)
        }

        viewpager = findViewById(R.id.viewpager) as ViewPager

        val adapter = ViewPagerAdapter(this)
        viewpager.adapter = adapter

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_home ->{
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_favorite ->{
                var intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
                return true
            }

            R.id.action_chat_black ->{
                var intent = Intent(this, MapsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_account ->{
                if(auth.currentUser==null){
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else{
                    val intent = Intent(this, MyActivity::class.java)
                    startActivity(intent)
                }
                return true
            }
        }
        return false
    }
}