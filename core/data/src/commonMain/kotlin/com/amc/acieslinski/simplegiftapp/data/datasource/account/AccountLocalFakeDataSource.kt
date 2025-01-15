package com.amc.acieslinski.simplegiftapp.data.datasource.account

import com.amc.acieslinski.simplegiftapp.TEST_PRIVATE_TOKEN
import com.amc.acieslinski.simplegiftapp.TEST_PUBLIC_TOKEN
import com.amc.acieslinski.simplegiftapp.configuration
import com.amc.acieslinski.simplegiftapp.data.datasource.account.model.AccountDatabaseModel

class AccountLocalFakeDataSource(
): AccountLocalDataSource {
    private var account: AccountDatabaseModel? = null

    init {
        if (configuration.useFakeAccount) {
            account = AccountDatabaseModel(
                name = "test name",
                surname = "test surname",
                public = TEST_PUBLIC_TOKEN,
                private = TEST_PRIVATE_TOKEN,
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
