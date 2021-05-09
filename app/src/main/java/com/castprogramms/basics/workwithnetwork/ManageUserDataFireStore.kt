package com.castprogramms.basics.workwithnetwork

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.castprogramms.karma.network.Resource
import com.castprogramms.karma.tools.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class ManageUserDataFireStore: ManageUserDataInterface {
    private val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true)
        .build()
    private val fireStore = FirebaseFirestore.getInstance().apply {
        firestoreSettings = settings
    }

    override fun addUser(user: User, id: String){
        fireStore.collection(USERS_TAG)
            .document(id)
            .set(user)
    }

    override fun getUser(id: String): MutableLiveData<Resource<Pair<String, User>>> {
        val mutableLiveData = MutableLiveData<Resource<Pair<String, User>>>(null)
        fireStore.collection(USERS_TAG)
            .document(id)
            .addSnapshotListener { value, error ->
                if (value != null){
                    mutableLiveData.postValue(Resource.Loading())
                    Log.e("auth", id + value.data.toString())
                    mutableLiveData.postValue(Resource.Success(id to value.toObject(User::class.java)!!))
                }
                else{
                    mutableLiveData.postValue(Resource.Error(error?.message))
                }
            }
        return mutableLiveData
    }

    companion object{
        const val SERVICES_TAG = "services"
        const val USERS_TAG = "users"
    }
}