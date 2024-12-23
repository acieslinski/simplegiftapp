package com.amc.acieslinski.simplegiftapp.registration.data.datasource.account

import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.mapper.AccountDataMapper
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.model.AccountLocal
import com.amc.acieslinski.simplegiftapp.registration.data.repository.account.AccountData
import com.amc.acieslinski.simplegiftapp.registration.data.repository.account.AccountLocalDataSource
import com.amc.acieslinski.simplegiftapp.db.SimpleGiftAppDatabase

class AccountLocalDataSource(
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

    override suspend fun getAccount(): AccountData? =
        db.simpleGiftAppDatabaseQueries.selectAccount(::mapSource).executeAsOneOrNull()?.let {
            accountDataMapper.mapToAccount(it)
        }

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
