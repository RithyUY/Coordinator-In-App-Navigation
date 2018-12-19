package com.rithyuy.coodinatordemo.src.createproject.projectname

import android.text.Editable
import android.text.TextWatcher
import com.rithyuy.coodinatordemo.base.BaseViewModel
import com.rithyuy.coodinatordemo.coodinator.flows.CreateProjectFlow
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

class ProjectNameViewModel : BaseViewModel<ProjectNameModel>() {

    @Inject
    lateinit var createProjectFlow: CreateProjectFlow

    val projectNameTextChange =object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
        override fun afterTextChanged(s: Editable?) {
            s?.apply {
                dataModel.name = this.toString()
                submittable.onNext(!dataModel.name.isEmpty())
            }

        }
    }
    val submittable: Subject<Boolean> by lazy { PublishSubject.create<Boolean>() }

    fun startProject() {
        createProjectFlow.submitProjectName(dataModel.name)
    }

    override fun onCleared() {
        super.onCleared()
        submittable.onComplete()
    }
}