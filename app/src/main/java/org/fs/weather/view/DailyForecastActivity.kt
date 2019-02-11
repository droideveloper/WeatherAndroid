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
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.view_daily_forecastl_activity.*
import org.fs.architecture.mvi.common.Operation
import org.fs.architecture.mvi.core.AbstractActivity
import org.fs.architecture.mvi.util.ObservableList
import org.fs.architecture.mvi.util.plusAssign
import org.fs.weather.R
import org.fs.weather.model.DailyForecastModel
import org.fs.weather.model.entity.DailyForecast
import org.fs.weather.model.entity.HourlyForecast
import org.fs.weather.model.event.LoadDailyForecastEvent
import org.fs.weather.util.C
import org.fs.weather.util.Operations.Companion.REFRESH
import org.fs.weather.util.bind
import org.fs.weather.view.adapter.HourlyForecastAdapter
import org.fs.weather.vm.DailyForecastActivityViewModel
import javax.inject.Inject

class DailyForecastActivity : AbstractActivity<DailyForecastModel, DailyForecastActivityViewModel>(),
  DailyForecastActivityView {

  companion object {
    const val BUNDLE_ARGS_DAILY_FORECAST = "bundle.args.daily.forecast"
  }

  private val dividerDrawable by lazy { ResourcesCompat.getDrawable(resources, R.drawable.ic_vertical_divider, theme) }

  @Inject lateinit var dataSet: ObservableList<HourlyForecast>
  @Inject lateinit var hourlyForecastAdapter: HourlyForecastAdapter

  private var dailyForecast = DailyForecast.EMPTY

  override val layoutRes: Int get() = 0

  override fun setUp(state: Bundle?) {
    dailyForecast = state?.getParcelable(BUNDLE_ARGS_DAILY_FORECAST) ?: DailyForecast.EMPTY

    viewRecycler.apply {
      setHasFixedSize(true)
      setItemViewCacheSize(C.RECYCLER_CACHE_SIZE)
      layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
      adapter = hourlyForecastAdapter
      dividerDrawable?.let { drawable ->
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(drawable)
        addItemDecoration(divider)
      }
    }
  }

  override fun attach() {
    super.attach()

    disposeBag += viewModel.state()
      .map { state ->
        if(state is Operation) {
          return@map state.type == REFRESH
        }
        return@map false
      }.subscribe(viewSwipeRefreshLayout::bind)

    disposeBag += viewModel.storage()
      .subscribe(::render)

    checkIfInitialLoadNeeded()
  }

  override fun render(model: DailyForecastModel) {
    // TODO implement this
  }

  private fun checkIfInitialLoadNeeded() {
    if (dailyForecast != DailyForecast.EMPTY) {
      accept(LoadDailyForecastEvent(dailyForecast))
    }
  }
} 