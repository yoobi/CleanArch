package io.yoobi.poc.cleanarch.di.impl.toolbar

import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.scopes.FragmentScoped
import io.yoobi.poc.cleanarch.MainActivity
import io.yoobi.poc.cleanarch.core.fragment.ui.ToolbarManager
import javax.inject.Inject

@FragmentScoped
class ToolbarManagerImpl @Inject constructor(
    private val navController: NavController
): ToolbarManager {

    override fun setupWithController(toolbar: MaterialToolbar) {
        toolbar.setupWithNavController(navController, MainActivity.appBarConfiguration)
    }

}
