package jastzeonic.com.jastzeonictodolist.view.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.database.Observable
import androidx.databinding.ObservableField

class SampleViewModel(application: Application) : AndroidViewModel(application) {

    val editTextContent = ObservableField<String>()

}