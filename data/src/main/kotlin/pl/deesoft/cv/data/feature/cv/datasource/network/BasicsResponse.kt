package pl.deesoft.cv.data.feature.cv.datasource.network

data class BasicsResponse constructor(
    val name: String,
    val email: String?,
    val phone: String?,
    val summary: String?,
    val location: LocationResponse?
)
