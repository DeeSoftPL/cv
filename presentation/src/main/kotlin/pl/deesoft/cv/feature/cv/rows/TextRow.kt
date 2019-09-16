package pl.deesoft.cv.feature.cv.rows

import dagger.Reusable
import javax.inject.Inject

class TextRow private constructor(
    val text: String
) {
    @Reusable
    class Factory @Inject constructor() {
        fun create(text: String): TextRow {
            return TextRow(text)
        }
    }
}