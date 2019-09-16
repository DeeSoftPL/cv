package pl.deesoft.cv.domain.feature.cv

data class Cv constructor(
    val name: String,
    val email: String?,
    val phone: String?,
    val summary: String?,
    val location: Location?,
    val works: List<Work>,
    val educations: List<Education>,
    val skills: List<Skill>,
    val languages: List<Language>,
    val interests: List<String>,
    val certificates: List<Certificate>
)