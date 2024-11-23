package com.amc.acieslinski.simplegiftapp.account.datasource

import com.amc.acieslinski.simplegiftapp.account.repository.AccountData
import com.amc.acieslinski.simplegiftapp.account.repository.AccountLocalDataSource
import com.amc.acieslinski.simplegiftapp.configuration

class AccountLocalFakeService(
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
