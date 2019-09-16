package pl.deesoft.cv.feature.main

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.deesoft.cv.base.BaseActivity
import pl.deesoft.cv.di.ActivityModule
import pl.deesoft.cv.feature.cv.CvFragment
import pl.deesoft.cv.feature.cv.CvModule

@Module(includes = [ActivityModule::class, MainModule.Declarations::class])
class MainModule {

    @Module
    internal interface Declarations {

        @Binds
        fun bindBaseActivity(activity: MainActivity): BaseActivity

        @ContributesAndroidInjector(modules = [CvModule::class])
        fun cvInjector(): CvFragment
    }
}