package io.yoobi.poc.cleanarch.core.fragment.ui

import android.content.Context

interface ExceptionHandler {
    fun toString(throwable: Throwable, context: Context): String
}