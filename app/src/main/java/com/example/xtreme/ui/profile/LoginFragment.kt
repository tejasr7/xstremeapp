package com.example.xtreme.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.example.xtreme.R
import com.example.xtreme.auth.AuthListener
import com.example.xtreme.databinding.FragmentLoginBinding
import com.example.xtreme.utils.hide
import com.example.xtreme.utils.show
import com.example.xtreme.utils.toast
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), AuthListener {

    companion object {
        fun newInstance() = LoginFragment()
    }

    //private lateinit var viewModel: LoginViewModel //by activityViewModels()
    private val viewModel: LoginViewModel by activityViewModels()
//    private lateinit var emailEditText: EditText
//    private lateinit var passwordEditText: EditText
//    private lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val root =  inflater.inflate(R.layout.fragment_login, container, false)
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)

        binding.loginViewModel = viewModel
        viewModel.authListener = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

//        emailEditText = view.findViewById(R.id.edtxt_login_email)
//        passwordEditText = view.findViewById(R.id.edtxt_login_password)

        //loginButton = view.findViewById(R.id.fbtn_login)
//        fbtn_login.setOnClickListener {
//            login_frag_loading.visibility = View.VISIBLE
//            viewModel.authenticate(emailEditText.text.toString(),
//                passwordEditText.text.toString())
//        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.refuseAuthentication()
            navController.popBackStack(R.id.navigation_home, false)
        }

        //val navController = findNavController()
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> navController.popBackStack()
                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> showErrorMessage()
            }

        })

    }

    private fun showErrorMessage() {
        login_frag_loading.visibility = View.GONE
        context?.toast("error message")
    }

    override fun onStarted() {     //// This is from AuthListener
        //context?.toast("Login Started")
        login_frag_loading.show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {     //// This is from AuthListener
        loginResponse.observe(this, Observer {
            login_frag_loading.hide()
            context?.toast("onSucess $it")
        })
    }

    override fun onFailure(message: String) {     //// This is from AuthListener
        context?.toast("onFailure $message")
        login_frag_loading.hide()
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}
