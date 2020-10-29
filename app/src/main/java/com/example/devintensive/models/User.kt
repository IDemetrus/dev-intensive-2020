package com.example.devintensive.models

import com.example.devintensive.utils.Utils
import java.util.*

data class User(
        val id: String,
        var firstName: String?,
        var lastName: String?,
        var avatar: String?,
        var rating: Int = 0,
        var respect: Int = 0,
        var lastVisit: Date? = Date(),
        var isOnline: Boolean = false
) {
    constructor(id: String, firstName: String?, lastName: String?):
            this (
                    id = id,
                    firstName = firstName,
                    lastName = lastName,
                    avatar = null
            )
    constructor(id: String) : this (id, "John", "Doe")

    init {
        println(if (isOnline)"User $firstName $lastName: is Online" else "User: $lastName is offline")
    }

    companion object Factory {
        private var lastId : Int = -1
        fun makeUser(fullName: String?): User {
            lastId++
                val (firstName, lastName) = Utils.parseFullName(fullName)
                return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }
}
