package pl.deesoft.cv.data.feature.cv.mapper

import pl.deesoft.cv.data.feature.cv.datasource.network.SkillResponse
import pl.deesoft.cv.data.mapper.Mapper
import pl.deesoft.cv.domain.feature.cv.Skill
import javax.inject.Inject

class SkillResponseToSkillMapper @Inject constructor(
    private val skillLevelMapper: SkillLevelMapper
) : Mapper<SkillResponse, Skill> {

    override fun transform(input: SkillResponse): Skill {
        return Skill(
            name = input.name,
            level = skillLevelMapper.transform(input.level)
        )
    }
}