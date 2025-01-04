package com.amc.acieslinski.simplegiftapp.presentation

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {

    val scope: CoroutineScope
}