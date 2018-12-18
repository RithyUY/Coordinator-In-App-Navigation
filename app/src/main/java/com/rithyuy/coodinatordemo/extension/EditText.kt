package com.rithyuy.coodinatordemo.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

fun EditText.createTextChange() : Pair<TextWatcher, Observable<Editable>> {

    val observable: Subject<Editable> = PublishSubject.create()
    val textWatcher =object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            s?.apply { observable.onNext(this) }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    }
    this.addTextChangedListener(textWatcher)

    return Pair(textWatcher, observable)
}