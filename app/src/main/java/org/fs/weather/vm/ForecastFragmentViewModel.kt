/*
 * Ozan Inc. Copyright (C) 2019 Fatih, Weather Android Kotlin.
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
package org.fs.weather.vm

import org.fs.architecture.mvi.common.Event
import org.fs.architecture.mvi.common.ForFragment
import org.fs.architecture.mvi.common.Intent
import org.fs.architecture.mvi.core.AbstractViewModel
import org.fs.weather.model.ForecastModel
import org.fs.weather.model.intent.NothingIntent
import org.fs.weather.view.ForecastFragmentView
import javax.inject.Inject

@ForFragment
class ForecastFragmentViewModel @Inject constructor(view: ForecastFragmentView) :
  AbstractViewModel<ForecastModel, ForecastFragmentView>(view) {

  override fun initState(): ForecastModel = throw NotImplementedError("not implemented")

  override fun toIntent(event: Event): Intent = when (event) {
    else -> NothingIntent<ForecastModel>() // if we can not resolve event to intent
  }

} 