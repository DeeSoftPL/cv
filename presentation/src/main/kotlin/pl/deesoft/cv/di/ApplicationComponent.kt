package pl.deesoft.cv.di

import androidx.databinding.DataBindingComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import pl.deesoft.cv.App
import pl.deesoft.cv.base.databinding.ImageViewPicassoBindings
import pl.deesoft.cv.base.databinding.ViewBindings
import pl.deesoft.cv.base.databinding.recycler.DataBindingAdapterBindings
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        RepositoryModule::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<App>, DataBindingComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        fun build(): ApplicationComponent
    }

    override fun getDataBindingAdapterBindings(): DataBindingAdapterBindings

    override fun getViewBindings(): ViewBindings

    override fun getImageViewPicassoBindings(): ImageViewPicassoBindings
}