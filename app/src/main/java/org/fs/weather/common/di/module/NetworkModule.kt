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

package org.fs.weather.common.di.module

import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.fs.weather.BuildConfig
import org.fs.weather.mock.net.Endpoint
import org.fs.weather.util.log
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.lang.IllegalArgumentException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
  // Network configuration will be here

  @Singleton @Provides fun provideHttpUrl(): HttpUrl = HttpUrl.parse(BuildConfig.BASE_URL) ?: throw IllegalArgumentException("we think that this url is invalid ${BuildConfig.BASE_URL}")

  @Singleton @Provides fun provideGson() = GsonBuilder().create()

  @Singleton @Provides fun provideFactory(context: Context, auth: Interceptor): OkHttpClient {
    val logger = HttpLoggingInterceptor.Logger { log(it) }
    val l = HttpLoggingInterceptor(logger)
    l.level = HttpLoggingInterceptor.Level.BODY

    val cache = File(context.cacheDir, "http")
    val c = Cache(cache, 12 * 1024 * 1024L) // 12MB

    val factory = OkHttpClient.Builder()
      .connectTimeout(5, TimeUnit.SECONDS)
      .readTimeout(5, TimeUnit.SECONDS)
      .writeTimeout(5, TimeUnit.SECONDS)
      .cache(c)

    if (BuildConfig.DEBUG) {
      factory.addInterceptor(l)
    }
    factory.addInterceptor(auth)
    return factory.build()
  }

  @Singleton @Provides fun provideRetrofit(factory: OkHttpClient, url: HttpUrl): Retrofit = Retrofit.Builder()
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(url)
    .callFactory(factory)
    .build()

  @Singleton @Provides fun provideEndpoint(retrofit: Retrofit): Endpoint = retrofit.create(Endpoint::class.java)
}