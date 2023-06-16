package io.yoobi.poc.cleanarch.core.fragment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.appbar.MaterialToolbar
import io.yoobi.poc.cleanarch.core.fragment.ui.databinding.IncludeTextToolbarBinding
import javax.inject.Inject

abstract class TextToolbarBaseFragment<T: ViewBinding>(
    bindingInflater: (layoutInflater: LayoutInflater) -> T
): BaseFragment<T>(bindingInflater) {

    @Inject lateinit var toolbarManager: ToolbarManager

    private lateinit var toolbar: MaterialToolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val includeTextToolbar = IncludeTextToolbarBinding.inflate(layoutInflater)
        toolbar = includeTextToolbar.textToolbar
        val linearLayout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            addView(includeTextToolbar.root)
            addView(view)
        }
        toolbarManager.setupWithController(toolbar)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        return linearLayout
    }

    protected fun setTextToolbar(text: String) {
        toolbar.title = text
    }

}