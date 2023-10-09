package io.yoobi.poc.cleanarch.feature.repository.details.ui


data class RepositoryDetailsArgs(val owner: String, val repo: String)

interface RepositoryDetailsNavigation {
    val args: RepositoryDetailsArgs
}
