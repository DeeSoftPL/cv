package pl.deesoft.cv.feature.cv

import dagger.Binds
import dagger.Module
import dagger.Provides
import org.threeten.bp.format.DateTimeFormatter
import pl.deesoft.cv.base.BaseFragment
import java.util.*

@Module(includes = [CvModule.Declarations::class])
class CvModule {

    @Provides
    internal fun provideDateFormatter(locale: Locale): DateTimeFormatter {
        return DateTimeFormatter.ofPattern("MMMM d, yyyy", locale)
    }

    @Module
    internal interface Declarations {

        @Binds
        fun bindBaseFragment(fragment: CvFragment): BaseFragment
    }
}