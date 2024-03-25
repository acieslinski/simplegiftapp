package com.amc.acieslinski.simplegiftapp.account.datasource

import com.amc.acieslinski.simplegiftapp.account.datasource.mapper.AccountDataMapper
import com.amc.acieslinski.simplegiftapp.account.datasource.model.AccountLocal
import com.amc.acieslinski.simplegiftapp.account.repository.AccountData
import com.amc.acieslinski.simplegiftapp.account.repository.AccountLocalDataSource
import com.amc.acieslinski.simplegiftapp.db.SimpleGiftAppDatabase

class AccountLocalService(
    private val db: SimpleGiftAppDatabase,
    private val accountDataMapper: AccountDataMapper
): AccountLocalDataSource {
    override fun save(account: AccountData) {
        saveAccount(accountDataMapper.mapToLocalSource(account))
    }

    private fun saveAccount(accountLocal: AccountLocal) {
        if (db.simpleGiftAppDatabaseQueries.isAccountEmpty().executeAsOne() == 1L) {
            db.simpleGiftAppDatabaseQueries.insertAccount(
                accountLocal.name,
                accountLocal.surname,
                accountLocal.public,
                accountLocal.private,
            )
        } else {
            error("there is already an account saved")
        }
    }

    fun getAccount(): AccountLocal =
        db.simpleGiftAppDatabaseQueries.selectAccount(::mapSource).executeAsOne()

    fun clearSources() =
        db.simpleGiftAppDatabaseQueries.removeAccount()

    private fun mapSource(
        id: String,
        name: String,
        public: String,
        private: String,
    ): AccountLocal {
        return AccountLocal(
            id,
            name,
            public,
            private
        )
    }
}
