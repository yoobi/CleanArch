package io.yoobi.poc.cleanarch.core.fragment.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T: ViewBinding>(
    private val bindingInflater: (layoutInflater: LayoutInflater) -> T
): Fragment() {

    val clsName: String = this.javaClass.simpleName

    private var _binding: T? = null
    val binding: T
        get() = _binding!!

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
