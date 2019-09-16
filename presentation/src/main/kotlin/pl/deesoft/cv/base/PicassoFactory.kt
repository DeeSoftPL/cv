package pl.deesoft.cv.base

import android.content.Context
import android.os.StatFs
import android.util.Log
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Downloader
import com.squareup.picasso.Picasso
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Protocol
import pl.deesoft.cv.di.ApplicationModule.Companion.IS_DEBUG
import java.io.File
import javax.inject.Inject
import javax.inject.Named
import kotlin.math.max
import kotlin.math.min

class PicassoFactory @Inject constructor(
    @Named(IS_DEBUG) private val isDebug: Boolean
) {

    fun create(context: Context): Picasso {
        val builder = Picasso.Builder(context)

        builder.downloader(createDownloader(context))
        builder.loggingEnabled(isDebug)
        if (isDebug) {
            builder.listener(createExceptionListener())
        }

        return builder.build()
    }

    private fun createDownloader(context: Context): Downloader {
        return OkHttp3Downloader(
            OkHttpClient.Builder()
                .protocols(listOf(Protocol.HTTP_1_1))
                .cache(createPicassoDiskCache(context))
                .build()
        )
    }

    private fun createExceptionListener(): Picasso.Listener {
        return Picasso.Listener { _, uri, exception ->
            Log.e("PICASSO", "failed on uri %s".format(uri), exception)
        }
    }

    private fun createPicassoDiskCache(context: Context): Cache {
        val cacheDir = createDefaultCacheDir(context)

        return Cache(
            cacheDir,
            calculateDiskCacheSize(cacheDir)
        )
    }

    private fun createDefaultCacheDir(context: Context): File {
        val cache = File(context.applicationContext.cacheDir, "picasso-cache")
        if (!cache.exists()) {

            cache.mkdirs()
        }

        return cache
    }

    private fun calculateDiskCacheSize(dir: File): Long {
        var size = (5 * 1024 * 1024).toLong()
        try {
            val statFs = StatFs(dir.absolutePath)
            val available = statFs.blockCountLong * statFs.blockSizeLong
            // Target 2% of the total space.
            size = available / 50
        } catch (ignored: IllegalArgumentException) {
        }

        // Bound inside min/max size for disk cache.
        return max(min(size, (50 * 1024 * 1024).toLong()), (5 * 1024 * 1024).toLong())
    }
}
