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

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import okhttp3.Interceptor
import org.fs.architecture.mvi.common.ForActivity
import org.fs.weather.App
import org.fs.weather.common.net.AuthInterceptor
import org.fs.weather.common.repo.*
import org.fs.weather.net.EndpointImp
import org.fs.weather.net.EndpointProxy
import org.fs.weather.view.DailyForecastActivity
import org.fs.weather.view.MainActivity
import org.fs.weather.view.SplashActivity
import javax.inject.Singleton

@Module
abstract class AppModule {

  @Singleton @Binds abstract fun bindApplication(app: App): Application

  @Singleton @Binds abstract fun bindContext(app: Application): Context

  @Singleton @Binds abstract fun bindAuthInterceptor(interceptor: AuthInterceptor): Interceptor

  @Singleton @Binds abstract fun bindEndpointProxy(proxy: EndpointImp): EndpointProxy

  @Singleton @Binds abstract fun bindLocalCityRepository(repo: LocalCityRepositoryImp): LocalCityRepository

  @Singleton @Binds abstract fun bindRemoteCityRepository(repo: RemoteCityRepositoryImp): RemoteCityRepository

  @Singleton @Binds abstract fun bindLocalForecastRepository(repo: LocalForecastRepositoryImp): LocalForecastRepository

  @Singleton @Binds abstract fun bindRemoteForecastRepository(repo: RemoteForecastRepositoryImp): RemoteForecastRepository

  @Singleton @Binds abstract fun bindConnectivityRepository(repo: ConnectivityRepositoryImp): ConnectivityRepository

  @Singleton @Binds abstract fun bindPreferenceRepository(repo: PreferenceRepositoryImp): PreferenceRepository

  @ForActivity @ContributesAndroidInjector(modules = [ActivityModule::class, ActivityProviderModule::class])
  abstract fun contributeSplashActivity(): SplashActivity

  @ForActivity @ContributesAndroidInjector(modules = [ActivityModule::class, ActivityProviderModule::class])
  abstract fun contributeMainActivity(): MainActivity

  @ForActivity @ContributesAndroidInjector(modules = [ActivityModule::class, ActivityProviderModule::class])
  abstract fun contributeDailyForecastActivity(): DailyForecastActivity
}