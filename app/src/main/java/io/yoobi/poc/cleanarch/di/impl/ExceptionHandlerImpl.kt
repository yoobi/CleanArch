package io.yoobi.poc.cleanarch.di.impl

import android.content.Context
import io.yoobi.poc.cleanarch.AppStringRes
import io.yoobi.poc.cleanarch.core.fragment.ui.ExceptionHandler
import io.yoobi.poc.cleanarch.core.network.exception.CertificateException
import io.yoobi.poc.cleanarch.core.network.exception.HttpException
import io.yoobi.poc.cleanarch.core.network.exception.ParserException
import io.yoobi.poc.cleanarch.core.network.exception.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExceptionHandlerImpl @Inject constructor() : ExceptionHandler {
    override fun toString(throwable: Throwable, context: Context): String = when (throwable) {
        is UnknownHostException -> context.getString(AppStringRes.error_no_internet)
        is CertificateException -> context.getString(AppStringRes.error_certificate)
        is ParserException -> context.getString(AppStringRes.error_parser)
        is HttpException -> context.getString(AppStringRes.error_http, throwable.code)
        else -> throwable.localizedMessage ?: context.getString(AppStringRes.error_generic)
    }
}