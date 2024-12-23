package com.amc.acieslinski.simplegiftapp.registration.data.datasource.account

import com.amc.acieslinski.simplegiftapp.registration.data.repository.account.AccountData
import com.amc.acieslinski.simplegiftapp.registration.data.repository.account.AccountLocalDataSource
import com.amc.acieslinski.simplegiftapp.configuration

class AccountLocalFakeDataSource(
): AccountLocalDataSource {
    private var account: AccountData? = null

    init {
        if (configuration.useFakeAccount) {
            account = AccountData(
                name = "test name",
                surname = "test surname",
                public = "test public token",
                private = "test private token",
            )
        }
    }

    override fun save(account: AccountData) {
        if (this.account == null) {
            this.account = account
        } else {
            error("fake account already provided")
        }
    }

    override suspend fun getAccount(): AccountData? = account
}
