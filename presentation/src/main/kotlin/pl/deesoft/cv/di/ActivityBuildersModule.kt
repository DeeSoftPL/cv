package pl.deesoft.cv.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.deesoft.cv.feature.main.MainActivity
import pl.deesoft.cv.feature.main.MainModule

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributesMainActivity(): MainActivity
}