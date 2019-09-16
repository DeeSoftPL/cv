package pl.deesoft.cv.di

import android.content.Intent
import dagger.Module
import dagger.Provides
import pl.deesoft.cv.base.BaseActivity

@Module
class ActivityModule {

    @Provides
    internal fun provideIntent(activity: BaseActivity): Intent {
        return activity.intent
    }
}