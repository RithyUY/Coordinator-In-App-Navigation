package com.rithyuy.coodinatordemo.extension

import android.widget.Button
import io.reactivex.Observable
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.disposables.Disposable

/****
 *
 * Created by UY RITHY on 12/19/18.
 *
 */

@CheckReturnValue
fun Button.bindText(observable: Observable<String>): Disposable {
    return observable.subscribe { this.text = it }
}