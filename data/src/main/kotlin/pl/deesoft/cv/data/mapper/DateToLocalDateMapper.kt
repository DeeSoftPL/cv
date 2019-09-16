package pl.deesoft.cv.data.mapper

import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import java.util.*
import javax.inject.Inject

class DateToLocalDateMapper @Inject constructor(
    private val zoneId: ZoneId
) : Mapper<Date, LocalDate> {

    override fun transform(input: Date): LocalDate {
        val instant = Instant.ofEpochMilli(input.time)

        return LocalDateTime.ofInstant(instant, zoneId).toLocalDate()
    }

    override fun reverse(input: LocalDate): Date {
        val instant = input.atStartOfDay().atZone(zoneId).toInstant()

        return Date(instant.toEpochMilli())
    }
}
