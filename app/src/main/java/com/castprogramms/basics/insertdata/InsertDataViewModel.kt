package com.castprogramms.karma.ui.insertdata

import androidx.lifecycle.ViewModel
import com.castprogramms.karma.network.Repository
import com.castprogramms.karma.tools.User

class InsertDataViewModel(private val repository: Repository): ViewModel() {
    fun addUser(user: User, email: String, password: String) = repository.addUser(user, email, password)
}