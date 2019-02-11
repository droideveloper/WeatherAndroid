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
import io.reactivex.schedulers.Schedulers
import org.fs.architecture.mvi.common.*
import org.fs.architecture.mvi.util.EMPTY
import org.fs.weather.common.repo.ConnectivityRepository
import org.fs.weather.common.repo.LocalForecastRepository
import org.fs.weather.common.repo.RemoteForecastRepository
import org.fs.weather.model.ForecastModel
import org.fs.weather.model.entity.City
import org.fs.weather.model.entity.Forecast
import org.fs.weather.model.net.Resource
import org.fs.weather.util.Operations.Companion.REFRESH
import java.io.IOException

class LoadForecastIntent(
  private val city: City,
  private val connectivityRepository: ConnectivityRepository,
  private val remoteForecastRepository: RemoteForecastRepository,
  private val localForecastRepository: LocalForecastRepository): ObservableIntent<ForecastModel>() {

  override fun invoke(): Observable<Reducer<ForecastModel>> {
    val dataSource = if (connectivityRepository.isConnected()) {
      val cityName = city.areaName?.firstOrNull()?.value ?: String.EMPTY
      remoteForecastRepository.loadForecastFor(cityName)
    } else {
      val cityId = city.cityId ?: 0L
      localForecastRepository.loadForecastForCityId(cityId)
    }

    return dataSource.concatMap(::success)
      .onErrorResumeNext(::failure)
      .startWith(init())
      .subscribeOn(Schedulers.io())
  }

  private fun success(resource: Resource<Forecast>): Observable<Reducer<ForecastModel>>  {
    return when(resource) {
      is Resource.Success<Forecast> -> Observable.just(
        { o -> o.copy(state = Operation(REFRESH, initialState = false), data = resource.data ?: Forecast.EMPTY) },
        { o -> o.copy(state = Idle, data = Forecast.EMPTY) })
      is Resource.Failure<Forecast> -> Observable.just(
        { o -> o.copy(state = Failure(resource.error ?: IOException("we do not know what happened, something must be wrong"))) },
        { o -> o.copy(state = Idle) }
      )
    }
  }

  private fun failure(error: Throwable): Observable<Reducer<ForecastModel>> = Observable.just(
    { o -> o.copy(state = Failure(error)) },
    { o -> o.copy(state = Idle) })

  private fun init(): Reducer<ForecastModel> = { o -> o.copy(state = Operation(REFRESH, initialState = true), data = Forecast.EMPTY)}
}