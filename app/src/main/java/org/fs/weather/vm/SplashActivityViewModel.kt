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
import org.fs.weather.common.repo.LocalCityRepository
import org.fs.weather.common.repo.PreferenceRepository
import org.fs.weather.model.SplashModel
import org.fs.weather.model.entity.City
import org.fs.weather.model.event.LoadCityEvent
import org.fs.weather.model.event.PickCityEvent
import org.fs.weather.model.event.SelectCityEvent
import org.fs.weather.model.intent.LoadCityIntent
import org.fs.weather.model.intent.NothingIntent
import org.fs.weather.model.intent.PickCityIntent
import org.fs.weather.model.intent.SelectCityIntent
import org.fs.weather.view.SplashActivityView
import javax.inject.Inject

@ForActivity
class SplashActivityViewModel @Inject constructor(
  private val preferenceRepository: PreferenceRepository,
  private val localCityRepository: LocalCityRepository,
  view: SplashActivityView) : AbstractViewModel<SplashModel, SplashActivityView>(view) {

  override fun initState(): SplashModel = SplashModel(state = Idle, data = City.EMPTY)

  override fun toIntent(event: Event): Intent = when (event) {
    PickCityEvent -> PickCityIntent()
    LoadCityEvent -> LoadCityIntent(preferenceRepository, localCityRepository)
    is SelectCityEvent -> SelectCityIntent(event.city, preferenceRepository, localCityRepository)
    else -> NothingIntent<SplashModel>() // if we can not resolve event to intent
  }
} 