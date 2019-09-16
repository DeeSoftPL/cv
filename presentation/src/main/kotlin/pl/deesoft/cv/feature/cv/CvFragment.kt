package pl.deesoft.cv.feature.cv

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import pl.deesoft.cv.R
import pl.deesoft.cv.base.mvvm.ViewModelFragment
import pl.deesoft.cv.databinding.CvFragmentBinding
import pl.deesoft.cv.feature.cv.rows.*

class CvFragment : ViewModelFragment<CvViewModel, CvFragmentBinding>() {

    override fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CvFragmentBinding {
        return CvFragmentBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        observeUrlOpenEvent()
        observeEmailOpenEvent()
        observePhoneOpenEvent()
    }

    private fun observeUrlOpenEvent() {
        binding.viewModel?.openUrlEvent?.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { url -> openUrl(url) }
        })
    }

    private fun observeEmailOpenEvent() {
        binding.viewModel?.openEmailEvent?.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { email ->
                openIntent(Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email")))
            }
        })
    }

    private fun observePhoneOpenEvent() {
        binding.viewModel?.openPhoneEvent?.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { phone ->
                openIntent(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")))
            }
        })
    }

    private fun openUrl(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        startActivity(Intent.createChooser(browserIntent, getString(R.string.choose_app)))
    }

    private fun openIntent(intent: Intent) {
        context?.let { context ->
            if (intent.resolveActivity(context.packageManager) != null) {
                startActivity(intent)
            }
        }
    }

    private fun initRecycler() {
        binding.recycler.registerDelegates { adapter ->
            adapter.registerViewModels(
                HeaderRow::class.java to R.layout.row_cv_header,
                SectionNameRow::class.java to R.layout.row_cv_section_name,
                TextRow::class.java to R.layout.row_cv_text,
                WorkRow::class.java to R.layout.row_cv_work,
                SkillRow::class.java to R.layout.row_cv_skill,
                LanguageRow::class.java to R.layout.row_cv_language,
                CertificateRow::class.java to R.layout.row_cv_certificate,
                EducationRow::class.java to R.layout.row_cv_education
            )
        }
    }
}
