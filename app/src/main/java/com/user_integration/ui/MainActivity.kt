package com.demo.userintegration.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.viewpager2.widget.ViewPager2
import com.demo.userintegration.ui.adapters.MainActivityPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.user_integration.R
import com.user_integration.utils.Navigation

class MainActivity : AppCompatActivity() , LifecycleOwner, Navigation {

    lateinit var mainActivityPager : ViewPager2
    lateinit var tabLayout : TabLayout
    val activityModel : MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityModel.modelInItDatabase(applicationContext)

        tabLayout = findViewById(R.id.tabLayout)
        mainActivityPager = findViewById(R.id.fragment_pager)
        mainActivityPager.adapter = MainActivityPagerAdapter(this)

        TabLayoutMediator(tabLayout, mainActivityPager) { tab, position ->
            when(position){
                0->{tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_verified_user_24)}
                1->{tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_person_24)}
                2->{tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_group_24)}
            }
        }.attach()
    }

    override fun navigateToRegisterFragment() {
        activityModel.isForUpdate = true
       mainActivityPager.setCurrentItem(1,true)
    }
}