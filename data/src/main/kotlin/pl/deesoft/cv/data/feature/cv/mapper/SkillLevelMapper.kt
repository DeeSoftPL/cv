package pl.deesoft.cv.data.feature.cv.mapper

import pl.deesoft.cv.data.mapper.Mapper
import pl.deesoft.cv.domain.feature.cv.SkillLevel
import javax.inject.Inject

class SkillLevelMapper @Inject constructor(

) : Mapper<String?, SkillLevel?> {

    override fun transform(input: String?): SkillLevel? {
        return when (input) {
            "beginner" -> SkillLevel.Beginner
            "competent" -> SkillLevel.Competent
            "intermediate" -> SkillLevel.Intermediate
            "advanced" -> SkillLevel.Advanced
            "expert" -> SkillLevel.Expert
            else -> null
        }
    }
}