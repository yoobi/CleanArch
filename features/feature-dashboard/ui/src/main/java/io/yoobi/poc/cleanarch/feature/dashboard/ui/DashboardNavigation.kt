package io.yoobi.poc.cleanarch.feature.dashboard.ui

interface DashboardNavigation {

    fun navigateToDetailsRepository(owner: String, name: String)

    fun navigateToDetailsUser(name: String)

}
