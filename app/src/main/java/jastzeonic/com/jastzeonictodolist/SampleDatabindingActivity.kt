package jastzeonic.com.jastzeonictodolist

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jastzeonic.com.jastzeonictodolist.databinding.ActivitySampleDatabindingBinding
import jastzeonic.com.jastzeonictodolist.view.model.SampleViewModel

class SampleDatabindingActivity : AppCompatActivity() {

    lateinit var viewModel: SampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SampleViewModel::class.java)

        val binding = DataBindingUtil.setContentView<ActivitySampleDatabindingBinding>(this, R.layout.activity_sample_databinding)
        binding.sampleViewModel = viewModel
        setContentView(binding.root)
    }
}
