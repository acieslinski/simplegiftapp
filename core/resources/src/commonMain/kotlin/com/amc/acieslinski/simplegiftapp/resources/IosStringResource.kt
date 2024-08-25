package com.amc.acieslinski.simplegiftapp.resources

import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

/**
 * This iosStringRes helps using the Res.string on iOs side. Using just Res.string makes error:
 * instance member 'account_name' cannot be used on type 'Res.string'; did you mean to use a value
 * of this type instead?
 * Probably it is related to the SKIE library.
 */
val iosStringRes = Res.string

/**
 * A dirty solution to use the string resources easily in SwiftUI but its fast enough. In case of
 * problems some cache for string resources can be provided instead.
 */
fun getIosString(resource: StringResource) = runBlocking {
    getString(resource)
}