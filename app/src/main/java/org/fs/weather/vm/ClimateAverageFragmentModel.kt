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
import org.fs.architecture.mvi.common.Idle
import org.fs.architecture.mvi.common.Intent
import org.fs.architecture.mvi.core.AbstractViewModel
import org.fs.weather.model.ClimateAverageModel
import org.fs.weather.model.entity.ClimateAverage
import org.fs.weather.model.event.LoadClimateAverageEvent
import org.fs.weather.model.intent.LoadClimateAverageIntent
import org.fs.weather.model.intent.NothingIntent
import org.fs.weather.view.ClimateAverageFragmentView
import javax.inject.Inject

@ForFragment
class ClimateAverageFragmentModel @Inject constructor(view: ClimateAverageFragmentView) :
  AbstractViewModel<ClimateAverageModel, ClimateAverageFragmentView>(view) {

  override fun initState(): ClimateAverageModel = ClimateAverageModel(state = Idle, data = ClimateAverage.EMPTY)

  override fun toIntent(event: Event): Intent = when (event) {
    is LoadClimateAverageEvent -> LoadClimateAverageIntent(event.climateAverage)
    else -> NothingIntent<ClimateAverageModel>() // if we can not resolve event to intent
  }
} 