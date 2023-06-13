object AndroidConfig {
    const val applicationId = "io.yoobi.poc.cleanarch"
    const val minSdk = 22
    const val compileSdk = 33
    const val targetSdk = compileSdk
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val androidGradle = "7.4.2"
    const val kotlin = "1.8.10"
    const val hilt = "2.44.2"
    const val benGradleVersionPlugin = "0.39.0"
    const val kotlinxCoroutines = "1.6.3"

    const val okhttp = "4.10.0"
    const val retrofit = "2.9.0"
    const val retrofitAdapter = "0.9.2"
    const val moshi = "1.14.0"
    const val room = "2.4.2"
    const val material = "1.8.0-alpha03"

    const val androidxRecyclerview = "1.2.1"
    const val androidxCoreKtx = "1.10.0"
    const val androidxAppCompat = "1.6.1"
    const val androidxConstraintLayout = "2.1.4"
    const val androidxNavigation = "2.5.3"
    const val androidxCoreSplashscreen = "1.0.0"
    const val androidxFragmentKtx = "1.5.7"
    const val androidxLifecycleKtx = "2.6.1"
}

object Libraries {

    const val androidxConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}"
    const val androidxRecyclerview = "androidx.recyclerview:recyclerview:${Versions.androidxRecyclerview}"
    const val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.androidxAppCompat}"
    const val androidxLifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidxLifecycleKtx}"
    const val androidxLifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycleKtx}"
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCoreKtx}"
    const val androidxFragment = "androidx.fragment:fragment-ktx:${Versions.androidxFragmentKtx}"
    const val androidxNavigation = "androidx.navigation:navigation-ui-ktx:${Versions.androidxNavigation}"
    const val androidxNavigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.androidxNavigation}"

    const val material = "com.google.android.material:material:${Versions.material}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    const val glideAndroid = "com.github.bumptech.glide:glide:4.15.1"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val retrofitAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitAdapter}"

    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlin ="com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiAdapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
    const val moshiKapt = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

}
