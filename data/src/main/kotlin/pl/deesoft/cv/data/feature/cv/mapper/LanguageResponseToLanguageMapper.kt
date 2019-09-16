package pl.deesoft.cv.data.feature.cv.mapper

import pl.deesoft.cv.data.feature.cv.datasource.network.LanguageResponse
import pl.deesoft.cv.data.mapper.Mapper
import pl.deesoft.cv.domain.feature.cv.Language
import javax.inject.Inject

class LanguageResponseToLanguageMapper @Inject constructor(
    private val languageFluencyMapper: LanguageFluencyMapper
) : Mapper<LanguageResponse, Language> {

    override fun transform(input: LanguageResponse): Language {
        return Language(
            name = input.language,
            fluency = languageFluencyMapper.transform(input.fluency)
        )
    }
}