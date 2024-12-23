package com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.mapper

import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.model.AccountLocal
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.model.AccountRemote
import com.amc.acieslinski.simplegiftapp.registration.data.repository.account.AccountData

class AccountDataMapper {
    fun mapToLocalSource(accountData: AccountData) = AccountLocal(
        accountData.name,
        accountData.surname,
        accountData.public,
        accountData.private,
    )

    fun mapToAccount(accountRemote: AccountRemote) = AccountData(
        accountRemote.name,
        accountRemote.surname,
        accountRemote.public,
        accountRemote.private,
    )

    fun mapToAccount(accountLocal: AccountLocal) = AccountData(
        accountLocal.name,
        accountLocal.surname,
        accountLocal.public,
        accountLocal.private,
    )
}