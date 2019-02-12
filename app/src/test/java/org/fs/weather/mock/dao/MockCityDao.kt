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

package org.fs.weather.mock.dao

import io.reactivex.Single
import org.fs.weather.common.db.dao.CityDao
import org.fs.weather.model.entity.City
import java.util.*

class MockCityDao(private val dataSet: ArrayList<City>): CityDao {

  override fun loadCities(): Single<List<City>> = Single.just(dataSet)

  override fun update(city: City) {
    val index = dataSet.indexOfFirst { c -> c.cityId == city.cityId }
    if (index != -1) {
      dataSet[index] = city
    }
  }

  override fun create(city: City) {
    dataSet.add(city)
  }

  override fun delete(city: City) {
    val index = dataSet.indexOfFirst { c -> c.cityId == city.cityId }
    if (index != -1) {
      dataSet.removeAt(index)
    }
  }

  override fun deleteAll() = dataSet.clear()
}