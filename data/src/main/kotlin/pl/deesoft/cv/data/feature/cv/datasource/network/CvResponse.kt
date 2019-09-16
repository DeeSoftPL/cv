package pl.deesoft.cv.data.feature.cv.datasource.network

data class CvResponse constructor(
    val basics: BasicsResponse,
    val work: List<WorkResponse>,
    val education: List<EducationResponse>,
    val skills: List<SkillResponse>,
    val languages: List<LanguageResponse>,
    val interests: List<String>,
    val certificates: List<CertificateResponse>
)