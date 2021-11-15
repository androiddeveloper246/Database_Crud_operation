package com.demo.userintegration.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.userintegration.database.model.UsersDetails
import com.demo.userintegration.ui.MainActivity
import com.demo.userintegration.ui.MainActivityViewModel
import com.user_integration.R
import com.user_integration.ui.adapters.UserListDetailsAdapter


class UsersListFragment : Fragment() , LifecycleOwner{

    private val model: MainActivityViewModel by activityViewModels()
    lateinit var usersList : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_users_list, container, false)

        usersList = v.findViewById(R.id.users_list)
        usersList.layoutManager = LinearLayoutManager(context)

        model.modelGetUsersDetails().observe(viewLifecycleOwner,object : androidx.lifecycle.Observer<List<UsersDetails>>{
            override fun onChanged(t: List<UsersDetails>?) {
                usersList.adapter = UserListDetailsAdapter(t!!,model,RegisterFragment(),
                    activity as MainActivity
                )
            }
        })

        return  v
    }



}
