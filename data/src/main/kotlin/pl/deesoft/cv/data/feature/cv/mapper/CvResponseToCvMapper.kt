package pl.deesoft.cv.data.feature.cv.mapper

import pl.deesoft.cv.data.feature.cv.datasource.network.CvResponse
import pl.deesoft.cv.data.mapper.Mapper
import pl.deesoft.cv.domain.feature.cv.Cv
import javax.inject.Inject

class CvResponseToCvMapper @Inject constructor(
    private val workResponseToWorkMapper: WorkResponseToWorkMapper,
    private val educationResponseToEducationMapper: EducationResponseToEducationMapper,
    private val languageResponseToLanguageMapper: LanguageResponseToLanguageMapper,
    private val locationResponseToLocationMapper: LocationResponseToLocationMapper,
    private val skillResponseToSkillMapper: SkillResponseToSkillMapper,
    private val certificateResponseToCertificateMapper: CertificateResponseToCertificateMapper
) : Mapper<CvResponse, Cv> {

    override fun transform(input: CvResponse): Cv {
        return Cv(
            name = input.basics.name,
            email = input.basics.email,
            phone = input.basics.phone,
            summary = input.basics.summary,
            works = input.work.map { workResponseToWorkMapper.transform(it) },
            educations = input.education.map { educationResponseToEducationMapper.transform(it) },
            languages = input.languages.map { languageResponseToLanguageMapper.transform(it) },
            location = input.basics.location?.let { locationResponseToLocationMapper.transform(it) },
            skills = input.skills.map { skillResponseToSkillMapper.transform(it) },
            interests = input.interests,
            certificates = input.certificates.map {
                certificateResponseToCertificateMapper.transform(
                    it
                )
            }
        )
    }
}