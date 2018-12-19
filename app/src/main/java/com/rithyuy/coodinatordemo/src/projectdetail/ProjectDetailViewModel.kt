package com.rithyuy.coodinatordemo.src.projectdetail

import android.util.Log
import com.rithyuy.coodinatordemo.base.BaseViewModel
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/****
 *
 * Created by UY RITHY on 12/19/18.
 *
 */

class ProjectDetailViewModel : BaseViewModel<ProjectDetailModel>() {


    val teams get() = dataModel.initialTeams
    val projectName get() = dataModel.projectName
}