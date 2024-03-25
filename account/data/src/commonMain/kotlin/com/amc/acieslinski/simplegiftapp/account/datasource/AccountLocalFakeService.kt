package com.amc.acieslinski.simplegiftapp.account.datasource

import com.amc.acieslinski.simplegiftapp.account.repository.AccountData
import com.amc.acieslinski.simplegiftapp.account.repository.AccountLocalDataSource

class AccountLocalFakeService(
): AccountLocalDataSource {
    override fun save(account: AccountData) {
        // do nothing
    }
}
