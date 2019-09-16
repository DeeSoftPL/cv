package pl.deesoft.cv.feature.cv.rows

import dagger.Reusable
import org.threeten.bp.format.DateTimeFormatter
import pl.deesoft.cv.App
import pl.deesoft.cv.R
import pl.deesoft.cv.domain.feature.cv.Certificate
import javax.inject.Inject

class CertificateRow private constructor(
    val certificate: Certificate,
    val onClick: (certificate: Certificate) -> Unit,
    private val dateFormatter: DateTimeFormatter,
    private val app: App
) {
    @Reusable
    class Factory @Inject constructor(
        private val dateTimeFormatter: DateTimeFormatter,
        private val app: App
    ) {
        fun create(
            certificate: Certificate,
            onClick: (certificate: Certificate) -> Unit
        ): CertificateRow {
            return CertificateRow(
                certificate = certificate,
                onClick = onClick,
                dateFormatter = dateTimeFormatter,
                app = app
            )
        }
    }

    val expirationDateRange = "%s - %s".format(
        dateFormatter.format(certificate.issueDate),
        certificate.expirationDate?.let { dateFormatter.format(it) }
            ?: app.getString(R.string.no_expiration_date)
    )

    val credentialBtnVisibility = !certificate.credentialUrl.isNullOrBlank()
}