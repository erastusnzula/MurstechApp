package com.example.murstechapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.murstechapp.data.MutableInitialValues
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthModel : ViewModel() {
    private val firebaseInstance = FirebaseAuth.getInstance()
    private val _authenticationStatus = MutableLiveData<UserStatus>()
    val authenticationStatus: LiveData<UserStatus> = _authenticationStatus

    init {
        if (firebaseInstance.currentUser == null) {
            _authenticationStatus.value = UserStatus.UnAuthenticated

        } else {
            _authenticationStatus.value = UserStatus.Authenticated

        }
    }

    fun logOut() {
        _authenticationStatus.value = UserStatus.UnAuthenticated
        firebaseInstance.signOut()
    }

    fun signIn(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            _authenticationStatus.value = UserStatus.IsLoading
            firebaseInstance.signInWithEmailAndPassword(email, password).addOnCompleteListener {

                if (it.isSuccessful) {
                    _authenticationStatus.value = UserStatus.Authenticated
                } else {
                    _authenticationStatus.value =
                        UserStatus.AuthenticationError(it.exception?.message.toString())
                        UserStatus.AuthenticationError(it.exception?.message.toString()).message
                }
            }

        } else {
            _authenticationStatus.value =
                UserStatus.AuthenticationError("Email and Password can't be empty!")

        }

    }

    fun signUp(email: String, password: String, passwordConfirm: String) {
        if (email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()) {
            if (password == passwordConfirm) {
                _authenticationStatus.value = UserStatus.IsLoading
                firebaseInstance.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            _authenticationStatus.value = UserStatus.Authenticated
                        } else {
                            _authenticationStatus.value =
                                UserStatus.AuthenticationError(it.exception?.message.toString())
                        }
                    }

            } else {
                _authenticationStatus.value =
                    UserStatus.AuthenticationError("Password does not match!")
            }

        } else {
            _authenticationStatus.value =
                UserStatus.AuthenticationError("Email and password can't be empty!")
        }

    }

}


sealed class UserStatus {
    data object Authenticated : UserStatus()
    data object UnAuthenticated : UserStatus()
    data object IsLoading : UserStatus()
    data class AuthenticationError(val message: String) : UserStatus()
}