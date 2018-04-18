package jastzeonic.com.jastzeonictodolist.view.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.database.Observable
import android.databinding.ObservableField

class SampleViewModel(application: Application) : AndroidViewModel(application) {

    val editTextContent = ObservableField<String>()

}