package pl.deesoft.cv.feature.cv.rows

import dagger.Reusable
import org.threeten.bp.format.DateTimeFormatter
import pl.deesoft.cv.App
import pl.deesoft.cv.R
import pl.deesoft.cv.domain.feature.cv.Work
import javax.inject.Inject

class WorkRow private constructor(
    val work: Work,
    private val dateFormatter: DateTimeFormatter,
    private val app: App
) {
    @Reusable
    class Factory @Inject constructor(
        private val dateTimeFormatter: DateTimeFormatter,
        private val app: App
    ) {
        fun create(work: Work): WorkRow {
            return WorkRow(
                work = work,
                dateFormatter = dateTimeFormatter,
                app = app
            )
        }
    }

    val employmentDateRange = "%s - %s".format(
        dateFormatter.format(work.startDate),
        work.endDate?.let { dateFormatter.format(it) } ?: app.getString(R.string.present)
    )

    val companyLogoVisibility = !work.companyLogoUrl.isNullOrBlank()
}