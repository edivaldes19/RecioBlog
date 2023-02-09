package com.edival.recioblog.domain.model

import com.google.firebase.firestore.Exclude
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class User(
    @get:Exclude var id: String? = null,
    var username: String? = null,
    var email: String? = null,
    @get:Exclude var password: String? = null,
    var imgUrl: String? = null
) {
    fun toJson(): String {
        return Gson().toJson(
            User(
                id, username, email, password, if (imgUrl != null) {
                    URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.toString())
                } else null
            )
        )
    }

    companion object {
        fun fromJson(data: String): User = Gson().fromJson(data, User::class.java)
    }
}