package com.amc.acieslinski.simplegiftapp.data.datasource.token

import com.amc.acieslinski.simplegiftapp.db.SimpleGiftAppDatabase

class PrivateTokenLocalDataSourceImpl(
    private val db: SimpleGiftAppDatabase,
) : PrivateTokenDataSource {
    override suspend fun getPrivateToken(): String =
        db.simpleGiftAppDatabaseQueries.selectAccount { _,_,_, private -> private }.executeAsOne()
}
