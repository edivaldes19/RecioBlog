package com.edival.recioblog.domain.model

import com.google.firebase.firestore.Exclude
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class User(
    var email: String? = null,
    var id: String? = null,
    var imgUrl: String? = null,
    var username: String? = null,
    @get:Exclude var password: String? = null
) {
    fun toJson(): String {
        return Gson().toJson(
            User(
                email = email,
                id = id,
                imgUrl = imgUrl?.let { url ->
                    URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                },
                username = username,
                password = password,
            )
        )
    }

    companion object {
        fun fromJson(data: String): User = Gson().fromJson(data, User::class.java)
    }
}