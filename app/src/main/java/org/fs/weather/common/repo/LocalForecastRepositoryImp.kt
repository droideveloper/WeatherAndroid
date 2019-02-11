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

package org.fs.weather.common.repo

import io.reactivex.Completable
import io.reactivex.Observable
import org.fs.weather.common.db.dao.ForecastDao
import org.fs.weather.model.entity.Forecast
import org.fs.weather.model.net.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalForecastRepositoryImp @Inject constructor(private val dao: ForecastDao): LocalForecastRepository {

  override fun loadForecastForCityId(cityId: Long): Observable<Resource<Forecast>> = dao.forecastByCityId(cityId)
    .toObservable()
    .map { forecast -> Resource.Success(forecast) }

  override fun create(forecast: Forecast): Completable = Completable.create {
    dao.create(forecast)
  }

  override fun update(forecast: Forecast): Completable = Completable.create {
    dao.update(forecast)
  }

  override fun delete(forecast: Forecast): Completable = Completable.create {
    dao.delete(forecast)
  }

  override fun deleteAll(): Completable = Completable.create {
    dao.deleteAll()
  }
}