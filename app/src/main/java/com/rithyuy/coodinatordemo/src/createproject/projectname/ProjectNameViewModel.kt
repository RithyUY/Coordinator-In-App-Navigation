package com.rithyuy.coodinatordemo.src.createproject.projectname

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.rithyuy.coodinatordemo.base.BaseViewModel
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class ProjectNameViewModel : BaseViewModel<ProjectNameModel>() {

    val projectNameTextChange =object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
        override fun afterTextChanged(s: Editable?) {
            s?.apply { dataModel.name = this.toString() }
            Log.d("PROJECT_NAME", s.toString())
        }
    }
    val submitName: Subject<String> by lazy { PublishSubject.create<String>() }
    val onNextClicked = View.OnClickListener { submitName.onNext(dataModel.name) }

    override fun onCleared() {
        super.onCleared()
        submitName.onComplete()
    }
}