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

package org.fs.weather.test.dao

import org.fs.weather.mock.dao.MockForecastDao
import org.fs.weather.model.entity.Forecast
import org.junit.Before
import org.junit.Test

class TestForecastDao {

  private val dataSet by lazy { ArrayList<Forecast>() }
  private val dao by lazy { MockForecastDao(dataSet) }

  @Before
  fun setUp() {
    // mock set up
    val forecast = Forecast(cityId = 0L)
    dataSet.add(forecast)
  }

  @Test
  fun testCreate() {
    dao.create(Forecast.EMPTY)
    assert(dataSet.size == 2)
  }

  @Test fun testUpdate() {
    val forecast = Forecast(cityId = 0L, forecastId = 0L)
    dao.update(forecast)
    assert(dataSet.size == 1) // no change
  }

  @Test fun testDelete() {
    val forecast = Forecast(cityId = 0L, forecastId = 0L)
    dao.delete(forecast)
    assert(dataSet.size == 0)
  }

  @Test fun testDeleteAll() {
    dao.deleteAll()
    assert(dataSet.size == 0)
  }

  @Test fun testLoadCities() {
    val item = dao.forecastByCityId(0L).blockingGet()
    assert(item.cityId == 0L)
  }
}