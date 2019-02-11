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

import android.os.Bundle
import org.fs.architecture.mvi.common.Failure
import org.fs.architecture.mvi.common.Idle
import org.fs.architecture.mvi.common.Operation
import org.fs.architecture.mvi.core.AbstractFragment
import org.fs.architecture.mvi.util.plusAssign
import org.fs.weather.R
import org.fs.weather.model.ClimateAverageModel
import org.fs.weather.model.entity.ClimateAverage
import org.fs.weather.model.event.LoadClimateAverageEvent
import org.fs.weather.util.Operations.Companion.REFRESH
import org.fs.weather.util.showError
import org.fs.weather.vm.ClimateAverageFragmentModel

class ClimateAverageFragment : AbstractFragment<ClimateAverageModel, ClimateAverageFragmentModel>(), ClimateAverageFragmentView {

  companion object {
    private const val BUNDLE_ARGS_CLIMATE_AVERAGE = "bundle.args.climate.average"

    @JvmStatic fun newInstance(climateAverage: ClimateAverage): ClimateAverageFragment = ClimateAverageFragment().apply {
      arguments = Bundle().apply {
        putParcelable(BUNDLE_ARGS_CLIMATE_AVERAGE, climateAverage)
      }
    }
  }

  private var climateAverage = ClimateAverage.EMPTY

  override val layoutRes: Int get() = R.layout.view_average_forecast_fragment

  override fun setUp(state: Bundle?) {
    climateAverage = state?.getParcelable(BUNDLE_ARGS_CLIMATE_AVERAGE) ?: ClimateAverage.EMPTY
  }

  override fun attach() {
    super.attach()

    disposeBag += viewModel.storage()
      .subscribe(::render)

    checkIfRenderNeeded()
  }

  override fun render(model: ClimateAverageModel) {
    when(model.state) {
      is Idle -> Unit
      is Operation -> when(model.state.type) {
        REFRESH -> bind(model.data)
        else -> Unit
      }
      is Failure -> showError(model.state.error)
    }
  }

  private fun checkIfRenderNeeded() {
    if (climateAverage != ClimateAverage.EMPTY) {
      accept(LoadClimateAverageEvent(climateAverage))
    }
  }

  private fun bind(climateAverage: ClimateAverage) {
    // TODO bind data in here
  }
}