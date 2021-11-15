package com.demo.userintegration.ui.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.demo.userintegration.database.model.UsersDetails
import com.demo.userintegration.ui.MainActivityViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.user_integration.R
import com.user_integration.ui.adapters.UserListDetailsAdapter
import kotlinx.android.synthetic.main.item_user_details.*
import java.util.*
import kotlin.math.log

class LoginFragment : Fragment() {

    private val model: MainActivityViewModel by activityViewModels()

    lateinit var login: TextInputEditText
    lateinit var vLogin: TextInputLayout
    lateinit var password: TextInputEditText
    lateinit var vPassword: TextInputLayout
    lateinit var loginButton: MaterialButton

    var users: List<UsersDetails> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        model.modelGetUsersDetails()
            .observe(viewLifecycleOwner, object : androidx.lifecycle.Observer<List<UsersDetails>> {
                override fun onChanged(t: List<UsersDetails>?) {
                    users = t!!
                }
            })

        var v = inflater.inflate(R.layout.fragment_login, container, false)

        login = v.findViewById(R.id.login_email)
        vLogin = v.findViewById(R.id.textInputLayout2)
        password = v.findViewById(R.id.login_password)
        vPassword = v.findViewById(R.id.textInputLayout)
        loginButton = v.findViewById(R.id.login_button)

        loginButton.setOnClickListener {

            var tempIsUserAvailable = false
            var tempUserName = ""

            if (login.text.toString().isEmpty()) {
                vLogin.setError("Enter Email!")
                return@setOnClickListener
            } else if (password.text.toString().isEmpty()) {
                vPassword.setError("Enter Password!")
                return@setOnClickListener
            }

            for (a in users) {
                if (a.email.equals(login.text.toString()) && a.password.equals(password.text.toString())) {
                    tempIsUserAvailable = true
                    tempUserName = a.name
                    break
                }else if (a.email.equals(login.text.toString()) && !a.password.equals(password.text.toString())){
                    vPassword.setError("Invalid Password!")
                    return@setOnClickListener
                    break
                }
            }

            if (tempIsUserAvailable) {
                var alert = AlertDialog.Builder(context).setTitle("Welcome!")
                    .setMessage("Hello $tempUserName")
                    .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog!!.cancel()
                        }
                    }).create()
                alert.show()
            } else {
                Toast.makeText(context, "No user found!", Toast.LENGTH_SHORT).show()
            }

        }

        return v
    }
}