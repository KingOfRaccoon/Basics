package com.castprogramms.karma.tools

data class User(
    var name: String = "",
    var family: String = "",
    var number : String = "",
    var scores: List<Score> = listOf()
){
    fun getFullName() = "$name $family"
}
