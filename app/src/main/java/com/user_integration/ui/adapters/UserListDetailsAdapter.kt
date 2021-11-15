package com.user_integration.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.userintegration.database.model.UsersDetails
import com.demo.userintegration.ui.MainActivity
import com.demo.userintegration.ui.MainActivityViewModel
import com.demo.userintegration.ui.fragments.RegisterFragment
import com.user_integration.R

class UserListDetailsAdapter(
    var users: List<UsersDetails>,
    var model: MainActivityViewModel,
    var frag: RegisterFragment,
    var mainActivity: MainActivity
) :
    RecyclerView.Adapter<UserListDetailsAdapter.Holder>() {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.textView5)
        var email: TextView = itemView.findViewById(R.id.textView6)
        var dob: TextView = itemView.findViewById(R.id.textView4)
        var pass: TextView = itemView.findViewById(R.id.pass)
        var delete: ImageView = itemView.findViewById(R.id.imageView)
        var update: ImageView = itemView.findViewById(R.id.imageView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_details, parent, false)
        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.setText(users.get(position).name)
        holder.email.setText(users.get(position).email)
        holder.dob.setText(users.get(position).dateOfBirth)
        holder.pass.setText(users.get(position).password)
        holder.delete.setOnClickListener {
            model.modelDeleteDetails(users.get(position))
        }
        holder.update.setOnClickListener {
            model.updateUserDetails = users.get(position)
            mainActivity.navigateToRegisterFragment()
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

}