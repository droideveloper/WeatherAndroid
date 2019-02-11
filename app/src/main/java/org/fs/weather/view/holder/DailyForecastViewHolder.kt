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

package org.fs.weather.view.holder

import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.view_daily_forecast_item.view.*
import org.fs.architecture.mvi.common.BusManager
import org.fs.architecture.mvi.util.inflate
import org.fs.architecture.mvi.util.plusAssign
import org.fs.rx.extensions.util.clicks
import org.fs.weather.R
import org.fs.weather.model.entity.DailyForecast
import org.fs.weather.model.event.SelectDailyForecastEvent

class DailyForecastViewHolder(view: View): BaseDailyForecastViewHolder(view) {

  private val viewTextDate by lazy { itemView.viewTextDay }
  private val viewTextMax by lazy { itemView.viewTextMaxTemp }
  private val viewTextMin by lazy { itemView.viewTextMinTemp }

  private val disposeBag by lazy { CompositeDisposable() }
  private val context by lazy { itemView.context }

  constructor(parent: ViewGroup): this(parent.inflate(R.layout.view_daily_forecast_item))

  override fun bind(value: DailyForecast) {

    viewTextDate.text = value.date
    viewTextMax.text = context.getString(R.string.str_avg_temp_max_c, value.maxTempC)
    viewTextMin.text = context.getString(R.string.str_avg_temp_min_c, value.minTempC)

    disposeBag += bindSelectDailyForecastEvent(value).subscribe(BusManager.Companion::send)
  }

  override fun unbind() = disposeBag.clear()

  private fun bindSelectDailyForecastEvent(entity: DailyForecast): Observable<SelectDailyForecastEvent> = itemView.clicks()
    .map { SelectDailyForecastEvent(entity) }
}