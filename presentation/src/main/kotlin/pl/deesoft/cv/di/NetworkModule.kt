package pl.deesoft.cv.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Cache
import pl.deesoft.cv.data.datasource.network.NetworkService
import pl.deesoft.cv.data.datasource.network.factory.NetworkServiceFactory
import pl.deesoft.cv.data.datasource.network.factory.OkHttpClientFactory
import pl.deesoft.cv.di.ApplicationModule.Companion.IS_DEBUG
import java.io.File
import javax.inject.Named

@Module
class NetworkModule {

    @Reusable
    @Provides
    internal fun provideGsonBase(): Gson {
        return GsonBuilder().create()
    }

    @Reusable
    @Provides
    @Named("cache_dir")
    internal fun provideCacheDir(context: Context): File {
        return context.cacheDir
    }

    @Reusable
    @Provides
    internal fun provideCache(@Named("cache_dir") cacheDir: File): Cache {
        return Cache(cacheDir, (10 * 1024 * 1024).toLong())
    }

    @Reusable
    @Provides
    internal fun provideMainOkHttpClientFactory(
        cache: Cache
    ): OkHttpClientFactory {
        return OkHttpClientFactory(cache)
    }

    @Reusable
    @Provides
    internal fun provideMainNetworkServiceFactory(
        gson: Gson
    ): NetworkServiceFactory {
        return NetworkServiceFactory(
            gson,
            "https://gist.githubusercontent.com/dees91/e404d528b714389cdea117d2ec41cd47/raw/a75ff4b646200b3f29d0150ec039d7f38712a126/"
        )
    }

    @Reusable
    @Provides
    internal fun provideNetworkService(
        httpClientFactory: OkHttpClientFactory,
        networkServiceFactory: NetworkServiceFactory,
        @Named(IS_DEBUG) isDebug: Boolean
    ): NetworkService {
        return networkServiceFactory.create(
            NetworkService::class.java,
            httpClientFactory.create(isDebug)
        )
    }
}