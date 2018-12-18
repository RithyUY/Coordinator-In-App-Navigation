package com.rithyuy.coodinatordemo.extension

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

val <T> Observable<T>.asObserver get()= this.observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
