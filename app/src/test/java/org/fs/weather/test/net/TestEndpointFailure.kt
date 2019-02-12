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

package org.fs.weather.test.net

import org.fs.architecture.mvi.util.EMPTY
import org.fs.weather.mock.net.MockEndpointFailure
import org.fs.weather.model.entity.City
import org.fs.weather.model.entity.Forecast
import org.fs.weather.model.net.Response
import org.fs.weather.model.net.SearchResponse
import org.fs.weather.util.C.Companion.DEFAULT_NUM_OF_DAYS
import org.junit.Test

class TestEndpointFailure {

  private val endpoint by lazy { MockEndpointFailure() }

  @Test fun testCityFor() {
    val res = endpoint.cityFor(String.EMPTY).onErrorReturn {
      Response(SearchResponse<List<City>>(result = null))
    }.blockingFirst()
    assert(res is Response<*>)
  }

  @Test fun testWeatherFor() {
    val res = endpoint.weatherFor(String.EMPTY, DEFAULT_NUM_OF_DAYS).onErrorReturn {
      Response(data = Forecast.EMPTY)
    }.blockingFirst()
    assert(res is Response<*>)
  }
}