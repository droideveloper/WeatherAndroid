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
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.view_main_activity.*
import org.fs.architecture.mvi.common.BusManager
import org.fs.architecture.mvi.common.Failure
import org.fs.architecture.mvi.common.Idle
import org.fs.architecture.mvi.common.Operation
import org.fs.architecture.mvi.core.AbstractActivity
import org.fs.architecture.mvi.util.ObservableList
import org.fs.architecture.mvi.util.plusAssign
import org.fs.rx.extensions.v4.util.refreshes
import org.fs.weather.R
import org.fs.weather.model.ForecastModel
import org.fs.weather.model.entity.*
import org.fs.weather.model.event.LoadForecastEvent
import org.fs.weather.model.event.SelectDailyForecastEvent
import org.fs.weather.util.C.Companion.RECYCLER_CACHE_SIZE
import org.fs.weather.util.Operations.Companion.REFRESH
import org.fs.weather.util.bind
import org.fs.weather.util.showError
import org.fs.weather.view.adapter.ClimateAveragePagerAdapter
import org.fs.weather.view.adapter.DailyForecastAdapter
import org.fs.weather.vm.MainActivityViewModel
import javax.inject.Inject

class MainActivity : AbstractActivity<ForecastModel, MainActivityViewModel>(), MainActivityView {

  companion object {
    const val BUNDLE_ARGS_CITY = "bundle.args.city"
  }

  @Inject lateinit var dataSet: ObservableList<DailyForecast>
  @Inject lateinit var dailyForecastAdapter: DailyForecastAdapter

  private val averageDataSet by lazy { ObservableList<MonthlyAverage>() }
  private val averagePagerAdapter by lazy { ClimateAveragePagerAdapter(supportFragmentManager, averageDataSet) }

  private val dividerDrawable by lazy { ResourcesCompat.getDrawable(resources, R.drawable.ic_vertical_divider, theme) }

  override val layoutRes: Int get() = R.layout.view_main_activity

  private var city = City.EMPTY

  override fun onCreate(savedInstanceState: Bundle?) {
    overridePendingTransition(R.anim.translate_right_in, R.anim.scale_out)
    super.onCreate(savedInstanceState)
  }

  override fun setUp(state: Bundle?) {
    // when we select city
    city = state?.getParcelable(BUNDLE_ARGS_CITY) ?: City.EMPTY
    // set up views
    viewRecycler.apply {
      setHasFixedSize(true)
      setItemViewCacheSize(RECYCLER_CACHE_SIZE)
      layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
      adapter = dailyForecastAdapter
      dividerDrawable?.let { drawable ->
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(drawable)
        addItemDecoration(divider)
      }
    }
    viewPager.adapter = averagePagerAdapter
    // progress color
    viewSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)

    val title = "${city.areaName?.firstOrNull()?.value}, ${city.country?.firstOrNull()?.value}"
    viewTextTitle.text = title
  }

  override fun attach() {
    averagePagerAdapter.register()
    super.attach()
    // listen bus manager
    disposeBag += BusManager.add(Consumer {  evt -> when(evt) {
        is SelectDailyForecastEvent -> startActivity(Intent(this, DailyForecastActivity::class.java).apply {
          putExtra(DailyForecastActivity.BUNDLE_ARGS_DAILY_FORECAST, evt.dailyForecast)
        })
      }
    })

    disposeBag += viewModel.state()
      .map { state ->
        if(state is Operation) {
          return@map state.type == REFRESH
        }
        return@map false
      }.subscribe(viewSwipeRefreshLayout::bind)

    disposeBag += viewSwipeRefreshLayout.refreshes()
      .filter { city != City.EMPTY }
      .map { LoadForecastEvent(city) }
      .doOnNext {
        clearState()
      }
      .subscribe(::accept)

    disposeBag += viewModel.storage()
      .subscribe(::render)

    checkIfInitialLoadNeeded()
  }

  override fun detach() {
    averagePagerAdapter.unregister()
    super.detach()
  }

  override fun render(model: ForecastModel) {
    when(model.state) {
      is Idle -> Unit
      is Operation -> when(model.state.type) {
        REFRESH -> {
          val data = model.data
          if (data != Forecast.EMPTY) {
            val array = data.weather ?: emptyList()
            if (array.isNotEmpty()) {
              dataSet.addAll(array)
            }
            val averages = data.climateAverages?.firstOrNull()?.month ?: emptyList()
            if (averages.isNotEmpty()) {
              averageDataSet.addAll(averages)
            }
          }
        }
        else -> Unit
      }
      is Failure -> showError(model.state.error)
    }
  }

  private fun checkIfInitialLoadNeeded() {
    if (city != City.EMPTY && dataSet.isEmpty()) {
      accept(LoadForecastEvent(city))
    }
  }

  private fun clearState() {
    dataSet.clear()
    averageDataSet.clear()
  }

  override fun finish() {
    super.finish()
    overridePendingTransition(R.anim.scale_in, R.anim.translate_right_out)
  }
} 