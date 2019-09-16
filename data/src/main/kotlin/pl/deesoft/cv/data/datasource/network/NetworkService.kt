package pl.deesoft.cv.data.datasource.network

import io.reactivex.Single
import pl.deesoft.cv.data.feature.cv.datasource.network.CvResponse
import retrofit2.http.GET

interface NetworkService {

    @GET("cv-json")
    fun getCv(): Single<CvResponse>
}