package pl.deesoft.cv.data.feature.cv.mapper

import pl.deesoft.cv.data.feature.cv.datasource.network.CertificateResponse
import pl.deesoft.cv.data.mapper.DateToLocalDateMapper
import pl.deesoft.cv.data.mapper.Mapper
import pl.deesoft.cv.domain.feature.cv.Certificate
import javax.inject.Inject

class CertificateResponseToCertificateMapper @Inject constructor(
    private val dateMapper: DateToLocalDateMapper
) : Mapper<CertificateResponse, Certificate> {

    override fun transform(input: CertificateResponse): Certificate {
        return Certificate(
            name = input.name,
            credentialUrl = input.credentialUrl,
            expirationDate = input.expirationDate?.let { dateMapper.transform(it) },
            issueDate = dateMapper.transform(input.issueDate),
            issuingOrganization = input.issuingOrganization
        )
    }
}