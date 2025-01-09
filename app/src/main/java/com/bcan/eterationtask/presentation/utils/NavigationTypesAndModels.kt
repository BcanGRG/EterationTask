package com.bcan.eterationtask.presentation.utils

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.bcan.eterationtask.data.model.ProductResponseModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val ProductType = navTypeGenerator<ProductResponseModel>()
}

inline fun <reified T> navTypeGenerator(isNullableAllowed: Boolean = true): NavType<T?> {
    return object : NavType<T?>(isNullableAllowed) {

        override fun get(bundle: Bundle, key: String): T? {
            return bundle.getString(key)?.let {
                Json.decodeFromString(it)
            }
        }

        override fun parseValue(value: String): T {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: T?): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: T?) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}