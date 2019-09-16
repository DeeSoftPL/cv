package pl.deesoft.cv.data.feature.cv.datasource.network

import java.util.*

data class EducationResponse constructor(
    val institution: String,
    val area: String,
    val studyType: String,
    val startDate: Date,
    val endDate: Date?
)