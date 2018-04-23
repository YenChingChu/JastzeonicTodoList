package jastzeonic.com.jastzeonictodolist

import android.arch.lifecycle.ViewModelProviders
import android.databinding.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import jastzeonic.com.jastzeonictodolist.databinding.ActivitySampleDatabindingBinding
import jastzeonic.com.jastzeonictodolist.view.model.SampleViewModel
import android.widget.ArrayAdapter


class SampleDatabindingActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        @BindingAdapter("spinnerList")
        fun setSpinnerList(spinner: Spinner, contentList: ObservableField<List<String>>) {

            val adapter = ArrayAdapter(spinner.context,
                    android.R.layout.simple_spinner_dropdown_item,
                    contentList.get())
            spinner.adapter = adapter

        }

        @JvmStatic
        @BindingAdapter("selectionPosition")
        fun setSpinnerSelectionPosition(spinner: Spinner, selectPosition: Int) {
            spinner.setSelection(selectPosition)
        }

        @JvmStatic
        @InverseBindingAdapter(attribute = "selectionPosition")
        fun getSpinnerSelectionPosition(spinner: Spinner): Int {
            return spinner.selectedItemPosition
        }

        @JvmStatic
        @BindingAdapter("selectionPositionAttrChanged")
        fun setSpinnerOnChangeListener(spinner: Spinner, listener: InverseBindingListener) {
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    listener.onChange()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }

    }

    private lateinit var viewModel: SampleViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SampleViewModel::class.java)

        val binding = DataBindingUtil.setContentView<ActivitySampleDatabindingBinding>(this, R.layout.activity_sample_databinding)
        binding.sampleViewModel = viewModel
        setContentView(binding.root)
    }
}
