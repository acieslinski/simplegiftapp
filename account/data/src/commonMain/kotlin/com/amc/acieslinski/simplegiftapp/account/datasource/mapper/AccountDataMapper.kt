package com.amc.acieslinski.simplegiftapp.account.datasource.mapper

import com.amc.acieslinski.simplegiftapp.account.datasource.model.AccountLocal
import com.amc.acieslinski.simplegiftapp.account.datasource.model.AccountRemote
import com.amc.acieslinski.simplegiftapp.account.repository.AccountData

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
}