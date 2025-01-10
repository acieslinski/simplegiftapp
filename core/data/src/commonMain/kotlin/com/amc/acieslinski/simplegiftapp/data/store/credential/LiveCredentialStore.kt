package com.amc.acieslinski.simplegiftapp.data.store.credential

import com.amc.acieslinski.simplegiftapp.db.SimpleGiftAppDatabase

class LiveCredentialStore(
    private val db: SimpleGiftAppDatabase,
) : CredentialStore {
    override suspend fun getPrivateToken(): String =
        db.simpleGiftAppDatabaseQueries.selectAccount { _,_,_, private -> private }.executeAsOne()
}
