package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseModel(
    @SerialName("name")
    val name: String,
    @SerialName("surname")
    val surname: String,
    @SerialName("public")
    val id: String,
)