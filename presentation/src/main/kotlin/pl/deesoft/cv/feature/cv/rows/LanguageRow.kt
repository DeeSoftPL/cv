package pl.deesoft.cv.feature.cv.rows

import dagger.Reusable
import pl.deesoft.cv.App
import pl.deesoft.cv.R
import pl.deesoft.cv.domain.feature.cv.Language
import pl.deesoft.cv.domain.feature.cv.LanguageFluency
import javax.inject.Inject

class LanguageRow private constructor(
    val language: Language,
    private val app: App
) {
    @Reusable
    class Factory @Inject constructor(
        private val app: App
    ) {
        fun create(language: Language): LanguageRow {
            return LanguageRow(
                language = language,
                app = app
            )
        }
    }

    val languageFluency = language.fluency?.let { languageFluency ->
        when (languageFluency) {
            LanguageFluency.Beginner -> app.getString(R.string.beginner)
            LanguageFluency.Conversational -> app.getString(R.string.conversational)
            LanguageFluency.Proficient -> app.getString(R.string.proficient)
            LanguageFluency.Fluent -> app.getString(R.string.fluent)
            LanguageFluency.Native -> app.getString(R.string.native_fluency)
        }
    }
}