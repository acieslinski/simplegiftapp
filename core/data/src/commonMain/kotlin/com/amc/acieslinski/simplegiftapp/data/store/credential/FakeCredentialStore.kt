package com.amc.acieslinski.simplegiftapp.data.store.credential

import com.amc.acieslinski.simplegiftapp.TEST_PRIVATE_TOKEN

class FakeCredentialStore : CredentialStore {
    override suspend fun getPrivateToken(): String = TEST_PRIVATE_TOKEN
}
