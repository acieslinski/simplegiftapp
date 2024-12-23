package com.amc.acieslinski.simplegiftapp.data.datasource.token

import com.amc.acieslinski.simplegiftapp.TEST_PRIVATE_TOKEN

class FakePrivateTokenLocalDataSourceImpl : PrivateTokenDataSource {
    override suspend fun getPrivateToken(): String = TEST_PRIVATE_TOKEN
}
