package pl.deesoft.cv.data.feature.cv.mapper

import pl.deesoft.cv.data.feature.cv.datasource.network.EducationResponse
import pl.deesoft.cv.data.mapper.DateToLocalDateMapper
import pl.deesoft.cv.data.mapper.Mapper
import pl.deesoft.cv.domain.feature.cv.Education
import javax.inject.Inject

class EducationResponseToEducationMapper @Inject constructor(
    private val dateMapper: DateToLocalDateMapper
) : Mapper<EducationResponse, Education> {

    override fun transform(input: EducationResponse): Education {
        return Education(
            institution = input.institution,
            area = input.area,
            studyType = input.studyType,
            startDate = dateMapper.transform(input.startDate),
            endDate = input.endDate?.let { dateMapper.transform(it) }
        )
    }
}