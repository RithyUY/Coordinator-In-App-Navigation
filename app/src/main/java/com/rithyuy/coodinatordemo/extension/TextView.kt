package com.rithyuy.coodinatordemo.extension

import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.disposables.Disposable

@CheckReturnValue
fun TextView.bind(observable: Observable<String>) : Disposable {
    return observable.subscribe{ this.text = it }
}

@CheckReturnValue
fun TextView.bindResource(observable: Observable<Int>) : Disposable {
    return observable.subscribe { resource -> this.setText(resource) }
}