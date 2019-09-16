package pl.deesoft.cv.di

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.Reusable
import org.threeten.bp.ZoneId
import pl.deesoft.cv.App
import pl.deesoft.cv.BuildConfig
import pl.deesoft.cv.base.PicassoFactory
import pl.deesoft.cv.base.UIThread
import pl.deesoft.cv.data.executor.JobExecutor
import pl.deesoft.cv.domain.executor.PostExecutionThread
import pl.deesoft.cv.domain.executor.ThreadExecutor
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule {

    companion object {
        const val IS_DEBUG = "is_debug"
    }

    @Provides
    internal fun provideContext(app: App): Context {
        return app.applicationContext
    }

    @Provides
    @Reusable
    internal fun providesLocale(): Locale {
        return Locale.ENGLISH
    }

    @Provides
    @Reusable
    internal fun providesZoneId(): ZoneId {
        return ZoneId.of("Europe/Warsaw")
    }

    @Provides
    @Singleton
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Named(IS_DEBUG)
    internal fun provideDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    @Provides
    internal fun providesPicasso(context: Context, picassoFactory: PicassoFactory): Picasso {
        return picassoFactory.create(context)
    }
}