package com.example.xtreme.ui.profile

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xtreme.auth.AuthListener
import com.example.xtreme.auth.UserLiveData


class LoginViewModel : ViewModel() {
    enum class AuthenticationState {
        UNAUTHENTICATED,        // Initial state, the user needs to authenticate
        AUTHENTICATED,        // The user has authenticated successfully
        INVALID_AUTHENTICATION  // Authentication failed
    }

    val authenticationState = MutableLiveData<AuthenticationState>()
    val email: String? = null
    val password: String? = null
    var authListener: AuthListener? = null


    init {
        // In this example, the user is always unauthenticated when MainActivity is launched
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
        //email = ""
//        password = ""
    }



    fun refuseAuthentication() {
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
    }

//    fun authenticate(email: String, password: String) {
//        if (passwordIsValidForUsername(email, password)) {
//            this.email = email
//            authenticationState.value = AuthenticationState.AUTHENTICATED
//        } else {
//            authenticationState.value = AuthenticationState.INVALID_AUTHENTICATION
//        }
//    }

    private fun passwordIsValidForUsername(email: String, password: String): Boolean {
        if(email.isEmpty() || password.isEmpty())  {

        }
            return false
    }

    fun onLoginButtonClicked(view: View){
    //authenticate(email.toString(), password.toString())
        //val amountTv = view.edtxt_login_email
        authListener?.onStarted()
        authenticationState.value = AuthenticationState.UNAUTHENTICATED    //////////////////
        if(email!!.isEmpty() || password!!.isEmpty()) {
            authenticationState.value = AuthenticationState.INVALID_AUTHENTICATION   ///////////////////
            authListener?.onFailure("Invalid Email or password ")
            return
        }

        val loginResponse = UserLiveData().userLogin(email, password)       // this is bad practice, use dependency injection
        authListener?.onSuccess(loginResponse)
        //authenticationState.value = AuthenticationState.AUTHENTICATED  ////////////////

    }

}

// A ViewModel is scoped to a ViewModelSt
// oreOwner.
// You can share data between the fragments by having a ViewModel scoped to the activity,
// which implements ViewModelStoreOwner