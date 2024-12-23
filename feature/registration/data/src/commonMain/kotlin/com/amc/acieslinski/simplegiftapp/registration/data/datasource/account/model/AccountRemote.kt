package com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountRemote(
    @SerialName("name")
    val name: String,
    @SerialName("surname")
    val surname: String,
    @SerialName("public")
    val public: String,
    @SerialName("private")
    val private: String
)