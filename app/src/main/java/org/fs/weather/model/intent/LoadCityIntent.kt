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
import org.fs.weather.common.repo.LocalCityRepository
import org.fs.weather.common.repo.PreferenceRepository
import org.fs.weather.model.SplashModel
import org.fs.weather.model.entity.City
import org.fs.weather.model.net.Resource
import org.fs.weather.util.Operations.Companion.LOAD_CITY
import java.io.IOException

class LoadCityIntent(
  private val preferenceRepository: PreferenceRepository,
  private val localCityRepository: LocalCityRepository): ObservableIntent<SplashModel>() {

  override fun invoke(): Observable<Reducer<SplashModel>> = localCityRepository.loadCities(preferenceRepository.selectedCityName)
    .concatMap(::success)
    .onErrorResumeNext(::failure)
    .startWith(init())
    .subscribeOn(Schedulers.io())

  private fun success(resource: Resource<List<City>>): Observable<Reducer<SplashModel>> {
    return when(resource) {
      is Resource.Success<List<City>> -> Observable.just(
        { o -> o.copy(state = Operation(LOAD_CITY), data = resource.data?.firstOrNull() ?: City.EMPTY) },
        { o -> o.copy(state = Idle, data = City.EMPTY) })
      is Resource.Failure<List<City>> -> Observable.just(
        { o -> o.copy(state = Failure(resource.error ?: IOException("we have no idea"))) },
        { o -> o.copy(state = Idle, data = City.EMPTY) })
     }
  }

  private fun failure(error: Throwable): Observable<Reducer<SplashModel>> = Observable.just(
    { o -> o.copy(state = Failure(error)) },
    { o -> o.copy(state = Idle, data = City.EMPTY) })

  private fun init(): Reducer<SplashModel> = { o -> o.copy(state = Idle, data = City.EMPTY)}
}