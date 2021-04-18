package com.deniz.ingcase

object Versions {
    const val ktlint = "0.40.0"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.3"
    const val material = "com.google.android.material:material:1.3.0"

    object Kotlin {
        private const val version = "1.4.30"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.4.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object AndroidX {
        private const val core_ktx_version = "1.3.2"
        private const val appcompat_version = "1.2.0"
        private const val recycler_view_version = "1.1.0"
        const val coreKtx = "androidx.core:core-ktx:$core_ktx_version"
        const val appCompat = "androidx.appcompat:appcompat:$appcompat_version"
        const val recyclerView = "androidx.recyclerview:recyclerview:$recycler_view_version"
    }

    object Navigation {
        private const val navigation_version = "2.3.1"
        const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$navigation_version"
        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$navigation_version"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$navigation_version"
    }

    object Room {
        private const val room_version = "2.2.6"
        const val room = "androidx.room:room-runtime:$room_version"
        const val roomCompiler = "androidx.room:room-compiler:$room_version"
        const val roomKtx = "androidx.room:room-ktx:$room_version"
    }

    object DaggerHilt {
        private const val hilt_version = "2.33-beta"
        const val daggerHilt = "com.google.dagger:hilt-android:$hilt_version"
        const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:$hilt_version"
        const val daggerHiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hilt_version"
    }

    object Glide {
        private const val glide_version = "4.11.0"
        const val glide = "com.github.bumptech.glide:glide:$glide_version"
        const val glideCompiler = "com.github.bumptech.glide:compiler:$glide_version"
    }

    object Network {
        private const val retrofit_version = "2.9.0"
        private const val okHttp_version = "4.7.2"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
        const val converter = "com.squareup.retrofit2:converter-gson:$retrofit_version"
        const val okhttp = "com.squareup.okhttp3:okhttp:$okHttp_version"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:$okHttp_version"
    }

    object UnitTest {
        private const val junit_version = "4.13.2"
        private const val mockk_version = "1.10.6"
        private const val truth_version = "1.1.2"
        private const val coroutine_test_version = "1.4.2"
        private const val core_test_version = "2.1.0"
        const val junit = "junit:junit:$junit_version"
        const val mockk = "io.mockk:mockk:$mockk_version"
        const val truth = "com.google.truth:truth:$truth_version"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutine_test_version"
        const val coreTest = "androidx.arch.core:core-testing:$core_test_version"
    }

    object AndroidTest {
        private const val junit_version = "1.1.2"
        private const val espresso_version = "3.3.0"
        const val junit = "androidx.test.ext:junit:$junit_version"
        const val espresso = "androidx.test.espresso:espresso-core:$espresso_version"
    }
}