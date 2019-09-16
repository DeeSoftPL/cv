package pl.deesoft.cv.data.feature.cv

import io.reactivex.Single
import pl.deesoft.cv.data.datasource.network.NetworkService
import pl.deesoft.cv.data.feature.cv.mapper.CvResponseToCvMapper
import pl.deesoft.cv.domain.feature.cv.Cv
import pl.deesoft.cv.domain.feature.cv.CvRepository
import javax.inject.Inject

class CvRepositoryImp @Inject constructor(
    private val networkService: NetworkService,
    private val cvResponseToCvMapper: CvResponseToCvMapper
) : CvRepository {

    override fun getCv(): Single<Cv> {
        return networkService.getCv().map { cvResponseToCvMapper.transform(it) }
    }
}