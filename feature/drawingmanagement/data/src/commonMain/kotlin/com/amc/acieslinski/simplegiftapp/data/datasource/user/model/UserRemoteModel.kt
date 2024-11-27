package com.amc.acieslinski.simplegiftapp.data.datasource.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRemoteModel(
    @SerialName("name")
    val name: String,
    @SerialName("surname")
    val surname: String,
    @SerialName("public")
    val idToken: String,
)