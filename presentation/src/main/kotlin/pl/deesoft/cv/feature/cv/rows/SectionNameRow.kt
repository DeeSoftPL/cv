package pl.deesoft.cv.feature.cv.rows

import dagger.Reusable
import javax.inject.Inject

class SectionNameRow private constructor(
    val name: String
) {
    @Reusable
    class Factory @Inject constructor() {
        fun create(name: String): SectionNameRow {
            return SectionNameRow(name)
        }
    }
}