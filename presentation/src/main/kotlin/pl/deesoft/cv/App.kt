package pl.deesoft.cv

import android.app.Activity
import android.app.Application
import androidx.databinding.DataBindingUtil
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import pl.deesoft.cv.di.ApplicationComponent
import pl.deesoft.cv.di.DaggerApplicationComponent
import javax.inject.Inject

open class App : Application(), HasActivityInjector {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    internal lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)
        DataBindingUtil.setDefaultComponent(appComponent)
        AndroidThreeTen.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}