package pl.deesoft.cv.feature.cv.rows

import dagger.Reusable
import org.threeten.bp.format.DateTimeFormatter
import pl.deesoft.cv.App
import pl.deesoft.cv.R
import pl.deesoft.cv.domain.feature.cv.Education
import javax.inject.Inject

class EducationRow private constructor(
    val education: Education,
    private val dateFormatter: DateTimeFormatter,
    private val app: App
) {
    @Reusable
    class Factory @Inject constructor(
        private val dateTimeFormatter: DateTimeFormatter,
        private val app: App
    ) {
        fun create(education: Education): EducationRow {
            return EducationRow(
                education = education,
                dateFormatter = dateTimeFormatter,
                app = app
            )
        }
    }

    val educationDateRange = "%s - %s".format(
        dateFormatter.format(education.startDate),
        education.endDate?.let { dateFormatter.format(it) } ?: app.getString(R.string.present)
    )
}