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
import org.fs.weather.common.repo.ConnectivityRepository
import org.fs.weather.common.repo.LocalCityRepository
import org.fs.weather.common.repo.RemoteCityRepository
import org.fs.weather.model.CityModel
import org.fs.weather.model.entity.City
import org.fs.weather.model.net.Resource
import org.fs.weather.util.Operations.Companion.REFRESH
import java.io.IOException

class SearchCityIntent(
  private val q: String,
  private val connectivityRepository: ConnectivityRepository,
  private val remoteCityRepository: RemoteCityRepository,
  private val localCityRepository: LocalCityRepository): ObservableIntent<CityModel>() {

  override fun invoke(): Observable<Reducer<CityModel>> {
    val dataSource = if (connectivityRepository.isConnected()) {
      remoteCityRepository.loadCities(q)
    } else {
      localCityRepository.loadCities(q)
    }

    return dataSource.concatMap(::success)
      .onErrorResumeNext(::failure)
      .startWith(init())
      .subscribeOn(Schedulers.io())
  }

  private fun success(resource: Resource<List<City>>): Observable<Reducer<CityModel>> {
    return when(resource) {
      is Resource.Success<List<City>> -> Observable.just(
        { o -> o.copy(state = Operation(REFRESH, initialState = false), data = resource.data ?: emptyList()) },
        { o -> o.copy(state = Idle, data = emptyList()) })
      is Resource.Failure<List<City>> -> Observable.just(
        { o -> o.copy(state = Failure(resource.error ?: IOException("we do not know what happened, something must be wrong"))) },
        { o -> o.copy(state = Idle) })
    }
  }

  private fun failure(error: Throwable): Observable<Reducer<CityModel>> = Observable.just(
    { o -> o.copy(state = Failure(error)) },
    { o -> o.copy(state = Idle) })

  private fun init(): Reducer<CityModel> = { o -> o.copy(state = Operation(REFRESH, initialState = true)) }
}