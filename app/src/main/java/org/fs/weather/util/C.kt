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

package org.fs.weather.util

sealed class C {

  companion object {
    const val DB_NAME = "storage.db"

    // used for request in
    const val QUERY_KEY = "key"
    const val QUERY_SERACH = "q"
    const val QUERY_FORMAT = "format"
    // forecast api param
    const val QUERY_NUMBER_OF_DAYS = "num_of_days"

    // path that we use for request
    const val WEATHER_REQUEST_PATH = "/premium/v1/weather.ashx"
    const val SEARCH_REQUEST_PATH = "/premium/v1/search.ashx"

    // default values for api
    const val DEFAULT_NUM_OF_DAYS = 5
    const val DEFAULT_FORMAT = "json"

    const val RECYCLER_CACHE_SIZE = 10
  }
}