package pl.deesoft.cv.feature.cv.rows

import dagger.Reusable
import pl.deesoft.cv.domain.feature.cv.Cv
import javax.inject.Inject

class HeaderRow private constructor(
    val cv: Cv,
    private val onEmailClick: (email: String) -> Unit,
    private val onPhoneClick: (phone: String) -> Unit
) {
    @Reusable
    class Factory @Inject constructor() {
        fun create(
            cv: Cv,
            onEmailClick: (email: String) -> Unit,
            onPhoneClick: (phone: String) -> Unit
        ): HeaderRow {
            return HeaderRow(
                cv = cv,
                onEmailClick = onEmailClick,
                onPhoneClick = onPhoneClick
            )
        }
    }

    val address: String
        get() {
            val sb = StringBuilder()

            cv.location?.let { location ->
                sb.append(location.city)
                location.region?.let { region ->
                    sb.append(", ")
                    sb.append(region)
                }
            }

            return sb.toString()
        }

    fun onEmailClicked() {
        cv.email?.let { onEmailClick(it) }
    }

    fun onPhoneClicked() {
        cv.phone?.let { onPhoneClick(it) }
    }
}