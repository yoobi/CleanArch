package io.yoobi.poc.cleanarch.feature.user.details.ui


data class UserDetailsArgs(val user: String)

interface UserDetailsNavigation {
    val args: UserDetailsArgs
}
