package com.example.shrine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shrine.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private lateinit var tilUsername: TextInputLayout
    private lateinit var tilPassword: TextInputLayout

    private lateinit var btnNext: MaterialButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

    }

    private fun initViews(view: View) {
        tilUsername = view.findViewById(R.id.til_username)
        tilPassword = view.findViewById(R.id.til_password)

        btnNext = view.findViewById(R.id.btn_next)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnNextListener()

    }

    private fun btnNextListener() {
        btnNext.setOnClickListener {

            if (isErrorShown(tilPassword)) clearError(tilPassword)

            val password = getPassword()

            if (isPasswordValid(password)) {

                navigateToProductGridFragment()

            } else {

                showError()

            }

        }
    }

    private fun isErrorShown(inputLayout: TextInputLayout) = inputLayout.isErrorEnabled

    private fun clearError(inputLayout: TextInputLayout) {
        val emptyString = ""
        inputLayout.error = emptyString
    }

    private fun getPassword() = tilPassword.editText?.text.toString()

    private fun isPasswordValid(password: String) = password.length >= 8 // at-least 8 characters

    private fun navigateToProductGridFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_productGridFragment)
    }

    private fun showError() {
        tilPassword.error = getString(R.string.password_error_message)
    }

}