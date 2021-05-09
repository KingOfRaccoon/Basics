package com.castprogramms.basics.workwithnetwork

import androidx.lifecycle.MutableLiveData
import com.castprogramms.karma.network.Resource
import com.castprogramms.karma.tools.User

interface ManageUserDataInterface {
    fun addUser(user: User, id: String)

    fun getUser(id: String): MutableLiveData<Resource<Pair<String, User>>>
}