/*
 * Weather Kotlin Android Copyright (C) 2019 Fatih, Ozan Inc..
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

package org.fs.weather.net

import io.reactivex.Observable
import org.fs.weather.model.entity.City
import org.fs.weather.model.entity.Forecast
import org.fs.weather.model.net.Resource
import org.fs.weather.model.net.Response
import org.fs.weather.model.net.SearchResponse
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EndpointImp @Inject constructor(private val internal: Endpoint): EndpointProxy {

  override fun weatherFor(q: String, days: Int): Observable<Resource<Forecast>> = internal.weatherFor(q, days).applyResource()

  override fun cityFor(q: String): Observable<Resource<List<City>>> = internal.cityFor(q).applySearchResource()

  private fun <T> Observable<Response<T>>.applyResource(): Observable<Resource<T>> = map { response ->
    if (response.data != null) {
      return@map Resource.Success(data = response.data)
    }
    return@map Resource.Failure<T>(error = IOException("error serializing data"))
  }

  private fun Observable<Response<SearchResponse<List<City>>>>.applySearchResource(): Observable<Resource<List<City>>> = map { response ->
    if (response.data != null) {
      return@map Resource.Success(data = response.data.result)
    }
    return@map Resource.Failure<List<City>>(error = IOException("error serializing data"))
  }
}