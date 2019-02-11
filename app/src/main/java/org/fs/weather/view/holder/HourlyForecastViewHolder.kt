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

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_hourly_forecast_item.view.*
import org.fs.architecture.mvi.util.EMPTY
import org.fs.architecture.mvi.util.inflate
import org.fs.weather.R
import org.fs.weather.common.glide.GlideRequests
import org.fs.weather.model.entity.HourlyForecast

class HourlyForecastViewHolder(view: View, private val glide: GlideRequests): BaseHourlyForecastViewHolder(view) {

  private val viewTextDescription by lazy { itemView.viewTextDescription }
  private val viewImageDescription by lazy { itemView.viewImageDescription }

  private val viewTextTemp by lazy { itemView.viewTextTemp }
  private val viewTextDate by lazy { itemView.viewTextDate }

  private val context by lazy { itemView.context }

  constructor(parent: ViewGroup, glide: GlideRequests): this(parent.inflate(R.layout.view_hourly_forecast_item), glide)

  override fun bind(value: HourlyForecast) {
    val avg = context.getString(R.string.str_avg_temp_max_c, value.tempC ?: String.EMPTY)
    val uri = Uri.parse(value.weatherIconUrl?.firstOrNull()?.value) ?: Uri.EMPTY
    val desc = value.weatherDesc?.firstOrNull()?.value ?: String.EMPTY
    val date = value.time ?: String.EMPTY

    glide.clear(viewImageDescription)
    glide.load(uri)
      .centerCrop()
      .into(viewImageDescription)

    viewTextTemp.text = avg
    viewTextDescription.text = desc
    viewTextDate.text = date
  }

  override fun unbind() = Unit
}