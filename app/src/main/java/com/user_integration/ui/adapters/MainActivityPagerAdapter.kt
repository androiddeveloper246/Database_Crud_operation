package com.demo.userintegration.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.demo.userintegration.ui.fragments.LoginFragment
import com.demo.userintegration.ui.fragments.RegisterFragment
import com.demo.userintegration.ui.fragments.UsersListFragment

class MainActivityPagerAdapter(fragAct: FragmentActivity) : FragmentStateAdapter(fragAct) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {

        when (position) {
            0 -> return LoginFragment()
            1 -> return RegisterFragment()
            2 -> return UsersListFragment()
        }
        return LoginFragment()
    }


}