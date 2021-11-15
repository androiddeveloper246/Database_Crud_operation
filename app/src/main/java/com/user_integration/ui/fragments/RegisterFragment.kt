package com.demo.userintegration.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.demo.userintegration.database.model.UsersDetails
import com.demo.userintegration.ui.MainActivityViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.user_integration.R
import java.text.SimpleDateFormat
import java.util.*


class RegisterFragment : Fragment() {

    private val TAG = "RegisterFragment"

    private val model: MainActivityViewModel by activityViewModels()

    lateinit var name: TextInputEditText
    lateinit var email: TextInputEditText
    lateinit var dob: TextInputEditText
    lateinit var pass: TextInputEditText
    lateinit var confirmPass: TextInputEditText
    lateinit var registerBtn: MaterialButton

    lateinit var lName: TextInputLayout
    lateinit var lEmail: TextInputLayout
    lateinit var lDob: TextInputLayout
    lateinit var lPassword: TextInputLayout
    lateinit var lConfirmPass: TextInputLayout


    var userDOB = ""
    var age: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_register, container, false)

        name = v.findViewById(R.id.register_name)
        lName = v.findViewById(R.id.textInputLayout2)

        email = v.findViewById(R.id.register_email)
        lEmail = v.findViewById(R.id.textInputLayout3)

        dob = v.findViewById(R.id.register_dob)
        lDob = v.findViewById(R.id.textInputLayout4)
        dob.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .build()

            datePicker.addOnPositiveButtonClickListener {
                var cal: Calendar = Calendar.getInstance()
                cal.timeInMillis = it
                userDOB = datePicker.headerText
                dob.setText(userDOB)
                age = Calendar.getInstance().get(Calendar.YEAR) - cal.get(Calendar.YEAR)
                if (age < 18) {
                    lDob.setError("Age must be 18")
                } else {
                    lDob.setError(null)
                }
            }
            datePicker.show(childFragmentManager, "tag")
        }

        pass = v.findViewById(R.id.register_password)
        lPassword = v.findViewById(R.id.textInputLayout4)

        confirmPass = v.findViewById(R.id.register_confirm_password)
        lConfirmPass = v.findViewById(R.id.textInputLayout6)

        registerBtn = v.findViewById(R.id.login_button)
        registerBtn.setOnClickListener {

            if (name.text!!.isEmpty()) {
                lName.setError("Name is empty")
                return@setOnClickListener
            } else if (email.text!!.isEmpty()) {
                lEmail.setError("Email is empty")
                return@setOnClickListener
            } else if (dob.text!!.isEmpty()) {
                lDob.setError("Date of birth is empty")
                return@setOnClickListener
            } else if (pass.text!!.isEmpty()) {
                lPassword.setError("Password is empty")
                return@setOnClickListener
            } else if (confirmPass.text!!.isEmpty()) {
                lConfirmPass.setError("Confirm Password is empty")
                return@setOnClickListener
            } else if (age < 18) {
                lDob.setError("Age must be 18")
                return@setOnClickListener
            } else if (!confirmPass.text.toString().equals(pass.text.toString())) {
                Toast.makeText(context, "Password not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {

                if (model.isForUpdate) {
                    model.isForUpdate = false

                    Toast.makeText(context, "User Updated Successfully!", Toast.LENGTH_SHORT).show()

                    model.modelUpdateUserDetails(
                        name.text.toString(),
                        email.text.toString(),
                        dob.text.toString(),
                        pass.text.toString(),
                        model.updateUserDetails.primaryKey
                    )

                    name.setText("")
                    email.setText("")
                    dob.setText("")
                    pass.setText("")
                    confirmPass.setText("")
                } else {
                    Toast.makeText(context, "User Register Successfully!", Toast.LENGTH_SHORT)
                        .show()

                    model.modelInsertDetails(
                        UsersDetails(
                            name.text.toString(),
                            email.text.toString(),
                            dob.text.toString(),
                            pass.text.toString()
                        )
                    )

                    name.setText("")
                    email.setText("")
                    dob.setText("")
                    pass.setText("")
                    confirmPass.setText("")
                }
            }

        }

        return v
    }

    override fun onResume() {
        super.onResume()

        if (model.isForUpdate) {

            // age validation
            var formatDate: SimpleDateFormat = SimpleDateFormat("MMM dd, yyyy")
            try {
                var d = formatDate.parse(model.updateUserDetails.dateOfBirth)
                var cal = Calendar.getInstance()
                cal.timeInMillis = d.time
                age = Calendar.getInstance().get(Calendar.YEAR) - cal.get(Calendar.YEAR)

            } catch (e: Exception) {
                Log.e(TAG, "onResume: ${e.message}")
            }


            name.setText(model.updateUserDetails.name)
            email.setText(model.updateUserDetails.email)
            dob.setText(model.updateUserDetails.dateOfBirth)
            pass.setText(model.updateUserDetails.password)
            confirmPass.setText(model.updateUserDetails.password)
        }

    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "onPause: ")
    }


}