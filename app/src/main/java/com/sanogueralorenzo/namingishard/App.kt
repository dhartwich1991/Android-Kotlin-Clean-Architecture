package com.sanogueralorenzo.namingishard

import android.app.Application
import com.sanogueralorenzo.cache.CacheLibrary
import com.sanogueralorenzo.posts.Posts
import com.squareup.leakcanary.LeakCanary

class App : Application() {

    override fun onCreate() {
        // Enable Strict mode? https://developer.android.com/reference/android/os/StrictMode
        super.onCreate()
        when {
            LeakCanary.isInAnalyzerProcess(this) -> return
            // Report Leaks to Firebase Crashlytics? :thinking:
            // https://github.com/square/leakcanary/wiki/Customizing-LeakCanary#uploading-to-a-server
            else -> LeakCanary.install(this)
        }

        // Unique initialization of Cache library to allow saving into device
        CacheLibrary.init(this)

        // Unique initialization of specific features (such as dependency injection)
        Posts.init()
    }
}
