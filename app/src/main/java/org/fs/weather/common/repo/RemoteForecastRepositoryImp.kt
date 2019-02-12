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

import io.reactivex.Observable
import org.fs.weather.model.entity.Forecast
import org.fs.weather.model.net.Resource
import org.fs.weather.mock.net.EndpointProxy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteForecastRepositoryImp @Inject constructor(private val proxy: EndpointProxy): RemoteForecastRepository {

  override fun loadForecastFor(q: String): Observable<Resource<Forecast>> = proxy.weatherFor(q)
}