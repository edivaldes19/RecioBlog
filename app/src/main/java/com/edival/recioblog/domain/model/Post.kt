package com.edival.recioblog.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class Post(
    var id: String? = null,
    var name: String? = null,
    var description: String? = null,
    var category: String? = null,
    var imgUrl: String? = null,
    var idUser: String? = null,
    var user: User? = null,
    var likes: MutableList<String> = mutableListOf()
) {
    fun toJson(): String {
        return Gson().toJson(
            Post(
                id = id,
                name = name,
                description = description,
                category = category,
                imgUrl = imgUrl?.let { url ->
                    URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                },
                idUser = idUser,
                user = user?.let { u ->
                    User(id = u.id,
                        username = u.username,
                        email = u.email,
                        password = u.password,
                        imgUrl = u.imgUrl?.let { url ->
                            URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                        })
                },
                likes = likes
            )
        )
    }

    companion object {
        fun fromJson(data: String): Post = Gson().fromJson(data, Post::class.java)
    }
}