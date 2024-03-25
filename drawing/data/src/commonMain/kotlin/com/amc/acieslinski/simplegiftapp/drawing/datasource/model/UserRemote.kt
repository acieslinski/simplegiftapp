package com.amc.acieslinski.simplegiftapp.drawing.datasource.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRemote(
    @SerialName("name")
    val name: String,
    @SerialName("surname")
    val surname: String,
    @SerialName("public")
    val idToken: String,
)