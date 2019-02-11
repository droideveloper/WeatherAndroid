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

import org.fs.architecture.mvi.common.*
import org.fs.architecture.mvi.core.AbstractViewModel
import org.fs.weather.common.repo.ConnectivityRepository
import org.fs.weather.common.repo.LocalForecastRepository
import org.fs.weather.common.repo.RemoteForecastRepository
import org.fs.weather.model.ForecastModel
import org.fs.weather.model.entity.Forecast
import org.fs.weather.model.event.LoadForecastEvent
import org.fs.weather.model.intent.LoadForecastIntent
import org.fs.weather.model.intent.NothingIntent
import org.fs.weather.view.MainActivityView
import javax.inject.Inject

@ForActivity
class MainActivityViewModel @Inject constructor(
  private val connectivityRepository: ConnectivityRepository,
  private val localForecastRepository: LocalForecastRepository,
  private val remoteForecastRepository: RemoteForecastRepository,
  view: MainActivityView) :
  AbstractViewModel<ForecastModel, MainActivityView>(view) {

  override fun initState(): ForecastModel = ForecastModel(state = Idle, data = Forecast.EMPTY)

  override fun toIntent(event: Event): Intent = when (event) {
    is LoadForecastEvent -> LoadForecastIntent(event.city, connectivityRepository, remoteForecastRepository, localForecastRepository)
    else -> NothingIntent<ForecastModel>() // if we can not resolve event to intent
  }
} 