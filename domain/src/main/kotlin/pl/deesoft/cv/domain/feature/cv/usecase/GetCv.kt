package pl.deesoft.cv.domain.feature.cv.usecase

import io.reactivex.Single
import pl.deesoft.cv.domain.base.SingleUseCase
import pl.deesoft.cv.domain.executor.PostExecutionThread
import pl.deesoft.cv.domain.executor.ThreadExecutor
import pl.deesoft.cv.domain.feature.cv.Cv
import pl.deesoft.cv.domain.feature.cv.CvRepository
import javax.inject.Inject

class GetCv @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val repository: CvRepository
) : SingleUseCase<Cv, Unit>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Unit): Single<Cv> {
        return repository.getCv()
    }
}
