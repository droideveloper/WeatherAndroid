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

import org.fs.weather.mock.dao.MockCityDao
import org.fs.weather.model.entity.City
import org.junit.Before
import org.junit.Test
import java.util.ArrayList

class TestCityDao {

  private val dataSet by lazy { ArrayList<City>() }
  private val dao by lazy { MockCityDao(dataSet) }

  @Before fun setUp() {
    // mock set up
    val city = City(cityId = 0L)
    dataSet.add(city)
  }

  @Test fun testCreate() {
    dao.create(City.EMPTY)
    assert(dataSet.size == 2)
  }

  @Test fun testUpdate() {
    val city = City(cityId = 0L, lattitude = "42.4242")
    dao.update(city)
    assert(dataSet.size == 1) // no change
  }

  @Test fun testDelete() {
    val city = City(cityId = 0L)
    dao.delete(city)
    assert(dataSet.size == 0)
  }

  @Test fun testDeleteAll() {
    dao.deleteAll()
    assert(dataSet.size == 0)
  }

  @Test fun testLoadCities() {
    val array = dao.loadCities().blockingGet()
    assert(array.isNotEmpty())
  }
}