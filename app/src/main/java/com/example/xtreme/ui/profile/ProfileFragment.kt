package com.example.xtreme.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.xtreme.R
import com.example.xtreme.signup.SignupActivity
import com.example.xtreme.storage.SharedPrefManager
import com.example.xtreme.ui.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    //private lateinit var welcomeTextView: TextView
    private val viewModel: LoginViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_sign_up.setOnClickListener {
            val intent = Intent(context, SignupActivity::class.java)
            this.startActivity(intent)
        }

        btn_login.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            this.startActivity(intent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        if (!context?.let { SharedPrefManager.getInstance(it).isLoggedIn }!!) {
//            view.findNavController().navigate(R.id.navigation_login)
//        }

        ////// android conditional nav
        val navController = findNavController()
        viewModel.authenticationState.observe(viewLifecycleOwner,
            Observer { authenticationState->
                when (authenticationState) {
                    LoginViewModel.AuthenticationState.AUTHENTICATED -> showWelcomeMessage()
                    LoginViewModel.AuthenticationState.UNAUTHENTICATED -> navController.navigate(R.id.navigation_login)
                   // else -> LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION
                }
            })
    }

    private fun showWelcomeMessage() {
        Toast.makeText(context, "showWelcomeMessage", Toast.LENGTH_SHORT).show()
    }
}
