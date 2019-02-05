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
import org.fs.weather.model.net.Response
import org.fs.weather.model.net.SearchResponse
import org.fs.weather.util.C.Companion.QUERY_NUMBER_OF_DAYS
import org.fs.weather.util.C.Companion.QUERY_SERACH
import org.fs.weather.util.C.Companion.SEARCH_REQUEST_PATH
import org.fs.weather.util.C.Companion.WEATHER_REQUEST_PATH
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {

  @GET(WEATHER_REQUEST_PATH) fun weatherFor(@Query(QUERY_SERACH) q: String, @Query(QUERY_NUMBER_OF_DAYS) days: Int): Observable<Response<Forecast>>

  @GET(SEARCH_REQUEST_PATH) fun cityFor(@Query(QUERY_SERACH) q: String): Observable<Response<SearchResponse<List<City>>>>
}