package pl.deesoft.cv.data.feature.cv.datasource.network

import java.util.*

data class WorkResponse constructor(
    val company: String,
    val position: String,
    val startDate: Date,
    val endDate: Date?,
    val summary: String?,
    val companyLogoUrl: String?
)