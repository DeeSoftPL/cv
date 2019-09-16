package pl.deesoft.cv.domain.feature.cv

import io.reactivex.Single

interface CvRepository {
    fun getCv(): Single<Cv>
}