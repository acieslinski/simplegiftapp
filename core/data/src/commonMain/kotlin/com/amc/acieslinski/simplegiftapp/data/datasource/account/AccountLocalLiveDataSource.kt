package com.amc.acieslinski.simplegiftapp.data.datasource.account

import com.amc.acieslinski.simplegiftapp.data.datasource.account.model.AccountDatabaseModel
import com.amc.acieslinski.simplegiftapp.db.SimpleGiftAppDatabase

class AccountLocalLiveDataSource(
    private val db: SimpleGiftAppDatabase,
): AccountLocalDataSource {
    override suspend fun save(account: AccountDatabaseModel) {
        saveAccount(account)
    }

    private fun saveAccount(accountDatabaseModel: AccountDatabaseModel) {
        if (db.simpleGiftAppDatabaseQueries.isAccountEmpty().executeAsOne() == 1L) {
            db.simpleGiftAppDatabaseQueries.insertAccount(
                accountDatabaseModel.name,
                accountDatabaseModel.surname,
                accountDatabaseModel.public,
                accountDatabaseModel.private,
            )
        } else {
            error("there is already an account saved")
        }
    }

    override suspend fun getAccount(): AccountDatabaseModel? =
        db.simpleGiftAppDatabaseQueries.selectAccount(::mapSource).executeAsOneOrNull()

    fun clearSources() =
        db.simpleGiftAppDatabaseQueries.removeAccount()

    private fun mapSource(
        id: String,
        name: String,
        public: String,
        private: String,
    ): AccountDatabaseModel {
        return AccountDatabaseModel(
            id,
            name,
            public,
            private
        )
    }
}
