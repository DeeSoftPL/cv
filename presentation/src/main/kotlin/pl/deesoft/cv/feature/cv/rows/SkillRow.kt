package pl.deesoft.cv.feature.cv.rows

import dagger.Reusable
import pl.deesoft.cv.App
import pl.deesoft.cv.R
import pl.deesoft.cv.domain.feature.cv.Skill
import pl.deesoft.cv.domain.feature.cv.SkillLevel
import javax.inject.Inject

class SkillRow private constructor(
    val skill: Skill,
    private val app: App
) {
    @Reusable
    class Factory @Inject constructor(
        private val app: App
    ) {
        fun create(skill: Skill): SkillRow {
            return SkillRow(
                skill = skill,
                app = app
            )
        }
    }

    val skillLevel = skill.level?.let { skillLevel ->
        when (skillLevel) {
            SkillLevel.Advanced -> app.getString(R.string.advanced)
            SkillLevel.Beginner -> app.getString(R.string.beginner)
            SkillLevel.Competent -> app.getString(R.string.competent)
            SkillLevel.Intermediate -> app.getString(R.string.intermediate)
            SkillLevel.Expert -> app.getString(R.string.expert)
        }
    }
}