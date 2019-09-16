package pl.deesoft.cv.data.feature.cv.datasource.network

import java.util.*

data class CertificateResponse constructor(
    val name: String,
    val issuingOrganization: String,
    val issueDate: Date,
    val expirationDate: Date?,
    val credentialUrl: String?
)