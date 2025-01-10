package com.amc.acieslinski.simplegiftapp.registration.data.datasource.account

import com.amc.acieslinski.simplegiftapp.configuration
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.model.AccountDatabaseModel

class AccountLocalFakeDataSource(
): AccountLocalDataSource {
    private var account: AccountDatabaseModel? = null

    init {
        if (configuration.useFakeAccount) {
            account = AccountDatabaseModel(
                name = "test name",
                surname = "test surname",
                public = "test public token",
                private = "test private token",
            )
        }
    }

    override suspend fun save(account: AccountDatabaseModel) {
        if (this.account == null) {
            this.account = account
        } else {
            error("fake account already provided")
        }
    }

    override suspend fun getAccount(): AccountDatabaseModel? = account
}
