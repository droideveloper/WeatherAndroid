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
package org.fs.weather.model.intent

import io.reactivex.Observable
import org.fs.architecture.mvi.common.Idle
import org.fs.architecture.mvi.common.ObservableIntent
import org.fs.architecture.mvi.common.Operation
import org.fs.architecture.mvi.common.Reducer
import org.fs.weather.model.DailyForecastModel
import org.fs.weather.model.entity.DailyForecast
import org.fs.weather.util.Operations.Companion.REFRESH

class LoadDailyForecastIntent(private val dailyForecast: DailyForecast): ObservableIntent<DailyForecastModel>() {

  override fun invoke(): Observable<Reducer<DailyForecastModel>> = Observable.just(
    { o -> o.copy(state = Operation(REFRESH, initialState = false), data = dailyForecast) },
    { o -> o.copy(state = Idle, data = DailyForecast.EMPTY) })
}