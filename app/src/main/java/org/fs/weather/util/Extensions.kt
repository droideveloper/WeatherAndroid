/*
 * Weather Kotlin Android Copyright (C) 2019 Fatih, Ozan Inc..
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fs.weather.util

import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.fs.architecture.mvi.common.View
import org.fs.architecture.mvi.util.EMPTY
import org.fs.weather.BuildConfig
import org.fs.weather.model.entity.City
import java.io.PrintWriter
import java.io.StringWriter

// log extensions
inline fun <reified T> T.log(message: String) = log(Log.DEBUG, message)

inline fun <reified T> T.log(level: Int, message: String) {
  if (isLogEnabled()) {
    Log.println(level, getClassTag(), message)
  }
}

inline fun <reified T> T.log(error: Throwable) {
  val sw = StringWriter()
  val pw = PrintWriter(sw)
  error.printStackTrace(pw)
  log(Log.ERROR, sw.toString())
}

inline fun <reified T> T.isLogEnabled(): Boolean = BuildConfig.DEBUG
inline fun <reified T> T.getClassTag(): String = T::class.java.simpleName

fun Completable.async(): Completable = subscribeOn(Schedulers.io())
  .observeOn(AndroidSchedulers.mainThread())

fun City.hasAreaName(q: String): Boolean {
  val areaName = areaName?.firstOrNull()?.value ?: String.EMPTY
  return areaName.contains(q)
}

fun View.showError(error: Throwable) {
  log(error)
}

fun SwipeRefreshLayout.bind(enable: Boolean) {
  if (isRefreshing != enable) {
    isRefreshing = enable
  }
}