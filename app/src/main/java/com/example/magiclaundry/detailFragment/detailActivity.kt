package com.example.magiclaundry.detailFragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.magiclaundry.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.custom_tab.view.*
import java.util.*

@Suppress("DEPRECATION")
class detailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val fragmentAapter  = FragmentAapter(supportFragmentManager)
        list_viewpager.adapter = fragmentAapter

        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("물세탁")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("다림질")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("수선")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("셔츠")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("기능성")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("특수")))

        list_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    list_viewpager.currentItem = tab.position
                }
           }

        })


    }

    private  fun createTabView(tabName: String) : View {
        val tabView = LayoutInflater.from(baseContext).inflate(R.layout.custom_tab, null)
        tabView.text_name.text = tabName

        return tabView
    }
}