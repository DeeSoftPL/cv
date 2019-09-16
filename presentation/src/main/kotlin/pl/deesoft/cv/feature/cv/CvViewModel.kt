package pl.deesoft.cv.feature.cv

import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.observers.DisposableSingleObserver
import pl.deesoft.cv.App
import pl.deesoft.cv.R
import pl.deesoft.cv.base.SingleEvent
import pl.deesoft.cv.base.mvvm.BaseViewModel
import pl.deesoft.cv.domain.feature.cv.Certificate
import pl.deesoft.cv.domain.feature.cv.Cv
import pl.deesoft.cv.domain.feature.cv.usecase.GetCv
import pl.deesoft.cv.feature.cv.rows.*
import javax.inject.Inject

class CvViewModel @Inject constructor(
    private val app: App,
    private val getCv: GetCv,
    private val headerRowFactory: HeaderRow.Factory,
    private val sectionNameRowFactory: SectionNameRow.Factory,
    private val textRowFactory: TextRow.Factory,
    private val workRowFactory: WorkRow.Factory,
    private val skillRowFactory: SkillRow.Factory,
    private val languageRowFactory: LanguageRow.Factory,
    private val certificateRowFactory: CertificateRow.Factory,
    private val educationRowFactory: EducationRow.Factory
) : BaseViewModel(app) {

    val rows: MutableLiveData<List<Any>> = MutableLiveData()

    val openUrlEvent: MutableLiveData<SingleEvent<String>> = MutableLiveData()
    val openPhoneEvent: MutableLiveData<SingleEvent<String>> = MutableLiveData()
    val openEmailEvent: MutableLiveData<SingleEvent<String>> = MutableLiveData()

    init {
        rows.value = emptyList()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        fetchCv()
    }

    private fun fetchCv() {
        getCv.execute(object : DisposableSingleObserver<Cv>() {
            override fun onSuccess(t: Cv) {
                rows.value = createCvRows(t)
            }

            override fun onError(e: Throwable) {
                communicateError()
                e.printStackTrace()
            }
        }, Unit)
    }

    private fun createCvRows(cv: Cv): List<Any> {
        val cvRows: MutableList<Any> = mutableListOf(
            headerRowFactory.create(
                cv = cv,
                onEmailClick = { email ->
                    openEmailEvent.value = SingleEvent(email)
                },
                onPhoneClick = { phone ->
                    openPhoneEvent.value = SingleEvent(phone)
                }
            )
        )

        cv.summary?.let { summary ->
            cvRows.add(sectionNameRowFactory.create(app.getString(R.string.summary)))
            cvRows.add(textRowFactory.create(summary))
        }

        cv.works.takeIf { works -> works.isNotEmpty() }?.let { works ->
            cvRows.add(sectionNameRowFactory.create(app.getString(R.string.experience)))
            cvRows.addAll(works.map { work -> workRowFactory.create(work) })
        }

        cv.educations.takeIf { educations -> educations.isNotEmpty() }?.let { educations ->
            cvRows.add(sectionNameRowFactory.create(app.getString(R.string.education)))
            cvRows.addAll(educations.map { education -> educationRowFactory.create(education) })
        }

        cv.skills.takeIf { skills -> skills.isNotEmpty() }?.let { skills ->
            cvRows.add(sectionNameRowFactory.create(app.getString(R.string.professional_skills)))
            cvRows.addAll(skills.map { skill -> skillRowFactory.create(skill) })
        }

        cv.languages.takeIf { languages -> languages.isNotEmpty() }?.let { languages ->
            cvRows.add(sectionNameRowFactory.create(app.getString(R.string.languages)))
            cvRows.addAll(languages.map { language -> languageRowFactory.create(language) })
        }

        cv.certificates.takeIf { certificates -> certificates.isNotEmpty() }?.let { certificates ->
            cvRows.add(sectionNameRowFactory.create(app.getString(R.string.certifications)))
            cvRows.addAll(certificates.map { item ->
                certificateRowFactory.create(item, onCertificateClick)
            })
        }

        cv.interests.takeIf { interests -> interests.isNotEmpty() }?.let { interests ->
            cvRows.add(sectionNameRowFactory.create(app.getString(R.string.interests)))
            cvRows.add(textRowFactory.create(interests.joinToString(", ")))
        }

        return cvRows
    }

    private val onCertificateClick: (certificate: Certificate) -> Unit = { certificate ->
        certificate.credentialUrl?.let { credentialUrl ->
            openUrlEvent.value = SingleEvent(credentialUrl)
        }
    }

    private fun communicateError() {
        Toast.makeText(app, app.getString(R.string.error_occurred), Toast.LENGTH_LONG).show()
    }

    override fun onCleared() {
        getCv.dispose()
        super.onCleared()
    }
}