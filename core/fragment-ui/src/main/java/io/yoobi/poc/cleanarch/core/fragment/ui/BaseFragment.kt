package io.yoobi.poc.cleanarch.core.fragment.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

abstract class BaseFragment<T: ViewBinding>(
    private val bindingInflater: (layoutInflater: LayoutInflater) -> T
): Fragment(), ExceptionHandler {

    @Inject internal lateinit var errorHandler: ExceptionHandler

    val clsName: String = this.javaClass.simpleName

    private var _binding: T? = null
    val binding: T
        get() = _binding!!

    override fun toString(throwable: Throwable, context: Context): String =
        errorHandler.toString(throwable, context)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        Log.e(clsName, "_binding $_binding")
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
