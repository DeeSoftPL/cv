package pl.deesoft.cv.domain.feature.cv

import org.threeten.bp.LocalDate

data class Education constructor(
    val institution: String,
    val area: String,
    val studyType: String,
    val startDate: LocalDate,
    val endDate: LocalDate?
)