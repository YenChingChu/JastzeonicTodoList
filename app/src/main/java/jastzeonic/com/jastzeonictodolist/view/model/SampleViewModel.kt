package jastzeonic.com.jastzeonictodolist.view.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.Observable
import android.databinding.ObservableField
import android.databinding.ObservableInt

class SampleViewModel(application: Application) : AndroidViewModel(application) {

    val editTextContent = ObservableField<String>()

    val parentList = ObservableField<List<String>>()
    val subList = ObservableField<List<String>>()

    val selectedPosition = ObservableInt()

    val listsMap = HashMap<Int, List<String>>()

    init {

        val titleList: MutableList<String> = mutableListOf()
        titleList.add("食物")
        titleList.add("遊戲")
        titleList.add("飲料")
        val contentList1: MutableList<String> = mutableListOf()
        contentList1.add("滷肉飯")
        contentList1.add("魷魚焿")
        contentList1.add("叉燒飯")
        val contentList2: MutableList<String> = mutableListOf()
        contentList2.add("剪刀石頭布")
        contentList2.add("跳房子")
        contentList2.add("圈圈叉叉")

        val contentList3: MutableList<String> = mutableListOf()
        contentList3.add("白開水")
        contentList3.add("紅茶")
        contentList3.add("奶茶")


        listsMap[0] = contentList1
        listsMap[1] = contentList2
        listsMap[2] = contentList3

        this.parentList.set(titleList)
        selectedPosition.set(0)
        subList.set(contentList1)

        selectedPosition.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                subList.set(listsMap[selectedPosition.get()])

            }

        })
    }


}