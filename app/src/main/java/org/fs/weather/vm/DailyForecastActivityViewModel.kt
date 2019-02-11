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
import org.fs.architecture.mvi.common.ForActivity
import org.fs.architecture.mvi.common.Idle
import org.fs.architecture.mvi.common.Intent
import org.fs.architecture.mvi.core.AbstractViewModel
import org.fs.weather.model.DailyForecastModel
import org.fs.weather.model.entity.DailyForecast
import org.fs.weather.model.event.LoadDailyForecastEvent
import org.fs.weather.model.intent.LoadDailyForecastIntent
import org.fs.weather.model.intent.NothingIntent
import org.fs.weather.view.DailyForecastActivityView
import javax.inject.Inject

@ForActivity
class DailyForecastActivityViewModel @Inject constructor(view: DailyForecastActivityView) :
  AbstractViewModel<DailyForecastModel, DailyForecastActivityView>(view) {

  override fun initState(): DailyForecastModel = DailyForecastModel(state = Idle, data = DailyForecast.EMPTY)

  override fun toIntent(event: Event): Intent = when (event) {
    is LoadDailyForecastEvent -> LoadDailyForecastIntent(event.dailyForecast)
    else -> NothingIntent<DailyForecastModel>() // if we can not resolve event to intent
  }
} 