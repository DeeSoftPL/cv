package pl.deesoft.cv.data.feature.cv.mapper

import pl.deesoft.cv.data.mapper.Mapper
import pl.deesoft.cv.domain.feature.cv.LanguageFluency
import javax.inject.Inject

class LanguageFluencyMapper @Inject constructor(

) : Mapper<String?, LanguageFluency?> {

    override fun transform(input: String?): LanguageFluency? {
        return when (input) {
            "beginner" -> LanguageFluency.Beginner
            "conversational" -> LanguageFluency.Conversational
            "proficient" -> LanguageFluency.Proficient
            "fluent" -> LanguageFluency.Fluent
            "native" -> LanguageFluency.Native
            else -> null
        }
    }
}