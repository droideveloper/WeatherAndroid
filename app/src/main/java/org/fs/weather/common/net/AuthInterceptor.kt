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

package org.fs.weather.common.net

import okhttp3.Interceptor
import okhttp3.Response
import org.fs.weather.BuildConfig
import org.fs.weather.util.C.Companion.DEFAULT_FORMAT
import org.fs.weather.util.C.Companion.QUERY_FORMAT
import org.fs.weather.util.C.Companion.QUERY_KEY
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(): Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    // grab ref of request
    val request = chain.request()

    // create new url by adding key
    val newUrl = request.url()
      .newBuilder()
      .addQueryParameter(QUERY_KEY, BuildConfig.API_KEY)
      .addQueryParameter(QUERY_FORMAT, DEFAULT_FORMAT)
        // TODO might want to add user locale information
      .build()

    // create new request url that we intercepted will change
    val newRequest = request.newBuilder().url(newUrl).build()

    // and proceed with this new request
    return chain.proceed(newRequest)
  }
}