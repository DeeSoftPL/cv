package pl.deesoft.cv.data.feature.cv.mapper

import pl.deesoft.cv.data.feature.cv.datasource.network.WorkResponse
import pl.deesoft.cv.data.mapper.DateToLocalDateMapper
import pl.deesoft.cv.data.mapper.Mapper
import pl.deesoft.cv.domain.feature.cv.Work
import javax.inject.Inject

class WorkResponseToWorkMapper @Inject constructor(
    private val dateMapper: DateToLocalDateMapper
) : Mapper<WorkResponse, Work> {

    override fun transform(input: WorkResponse): Work {
        return Work(
            company = input.company,
            summary = input.summary,
            position = input.position,
            endDate = input.endDate?.let { dateMapper.transform(it) },
            startDate = dateMapper.transform(input.startDate),
            companyLogoUrl = input.companyLogoUrl
        )
    }
}