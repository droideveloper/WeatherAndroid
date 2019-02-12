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
package org.fs.weather.view

import android.content.Intent
import android.os.Bundle
import io.reactivex.functions.Consumer
import org.fs.architecture.mvi.common.BusManager
import org.fs.architecture.mvi.common.Failure
import org.fs.architecture.mvi.common.Idle
import org.fs.architecture.mvi.common.Operation
import org.fs.architecture.mvi.core.AbstractActivity
import org.fs.architecture.mvi.util.EMPTY
import org.fs.architecture.mvi.util.plusAssign
import org.fs.weather.R
import org.fs.weather.common.repo.PreferenceRepository
import org.fs.weather.model.SplashModel
import org.fs.weather.model.entity.City
import org.fs.weather.model.event.LoadCityEvent
import org.fs.weather.model.event.PickCityEvent
import org.fs.weather.model.event.SelectCityEvent
import org.fs.weather.util.Operations.Companion.LOAD_CITY
import org.fs.weather.util.Operations.Companion.PICK_CITY
import org.fs.weather.util.Operations.Companion.SELECT_CITY
import org.fs.weather.util.showError
import org.fs.weather.vm.SplashActivityViewModel
import javax.inject.Inject

class SplashActivity : AbstractActivity<SplashModel, SplashActivityViewModel>(), SplashActivityView {

  @Inject lateinit var preferenceRepository: PreferenceRepository

  override val layoutRes: Int get() = R.layout.view_splash_activity

  override fun setUp(state: Bundle?) = Unit

  override fun attach() {
    super.attach()

    disposeBag += BusManager.add(Consumer { evt -> when(evt) {
        is SelectCityEvent -> accept(evt)
      }
    })

    disposeBag += viewModel.storage()
      .subscribe(::render)

    checkIfInitialLoad()
  }

  override fun render(model: SplashModel) {
    when (model.state) {
      is Idle -> Unit
      is Operation -> when (model.state.type) {
        PICK_CITY -> {
          val trans = supportFragmentManager.beginTransaction()
          trans.replace(R.id.viewContentFrame, CityFragment())
          trans.commit()
        }
        LOAD_CITY -> startActivityNext(model.data)
        SELECT_CITY -> {
          if (model.data != City.EMPTY) {
            startActivityNext(model.data)
          }
        }
        else -> Unit
      }
      is Failure -> showError(model.state.error)
    }
  }

  private fun checkIfInitialLoad() {
    val cityName = preferenceRepository.selectedCityName
    if (cityName == String.EMPTY) {
      val f = supportFragmentManager.findFragmentById(R.id.viewContentFrame)
      if (f !is CityFragment) {
        accept(PickCityEvent)
      }
    } else {
      accept(LoadCityEvent)
    }
  }

  private fun startActivityNext(city: City) {
    startActivity(Intent(this, MainActivity::class.java).apply {
      putExtra(MainActivity.BUNDLE_ARGS_CITY, city)
    })
    finish()
  }
} 