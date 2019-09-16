package pl.deesoft.cv.domain.feature.cv

import org.threeten.bp.LocalDate

data class Certificate constructor(
    val name: String,
    val issuingOrganization: String,
    val issueDate: LocalDate,
    val expirationDate: LocalDate?,
    val credentialUrl: String?
)