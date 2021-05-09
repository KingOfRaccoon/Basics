package com.castprogramms.basics.workwithnetwork

import androidx.lifecycle.MutableLiveData
import com.castprogramms.karma.network.Resource
import com.castprogramms.karma.tools.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Repository(private val manageUserDataFireStore: ManageUserDataFireStore) {
    private val fireBaseAuthenticator = FirebaseAuth.getInstance()
    val userLiveData = MutableLiveData<Resource<FirebaseUser>>().apply {
        value = null
    }
    var user : FirebaseUser? = null

    init {
        user = fireBaseAuthenticator.currentUser
        if (user != null)
            userLiveData.postValue(Resource.Success(user!!))
    }
    // блок регестрации по почте и паролю
    fun login(email: String, password: String){
        if (user == null){
            userLiveData.postValue(Resource.Loading())
            fireBaseAuthenticator.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        userLiveData.postValue(Resource.Success(it.result?.user!!))
                        user = it.result?.user
                    }
                }.addOnFailureListener {
                    userLiveData.postValue(Resource.Error(it.message))
                }
            }
    }

    fun addUser(user: User, email: String, password: String){
        var id = ""
        fireBaseAuthenticator.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    if (it.result != null){
                        id = it.result?.user!!.uid
                        userLiveData.postValue(Resource.Success(it.result?.user!!))
                        this.user = it.result?.user
                    }
                }
            }.continueWith {
                if (id != "")
                    manageUserDataFireStore.addUser(user, id)
            }
    }
    // конец блока


    // блок регестрации по гугл аккаунту


    // конец блока

    fun getUser(id: String) = manageUserDataFireStore.getUser(id)
}