package pl.deesoft.cv.domain.feature.cv

import org.threeten.bp.LocalDate

data class Work constructor(
    val company: String,
    val position: String,
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val summary: String?,
    val companyLogoUrl: String?
)