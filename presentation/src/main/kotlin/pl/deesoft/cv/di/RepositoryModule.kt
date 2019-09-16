package pl.deesoft.cv.di

import dagger.Binds
import dagger.Module
import pl.deesoft.cv.data.feature.cv.CvRepositoryImp
import pl.deesoft.cv.domain.feature.cv.CvRepository

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsCvRepository(cvRepositoryImp: CvRepositoryImp): CvRepository
}