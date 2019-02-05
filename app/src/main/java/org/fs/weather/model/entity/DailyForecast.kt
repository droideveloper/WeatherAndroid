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

package org.fs.weather.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DailyForecast(
  val date: String? = null,
  val astronomy: List<Astronomy>? = null,
  @field:SerializedName("maxtempC") val maxTempC: String? = null,
  @field:SerializedName("maxtempF") val maxTempF: String? = null,
  @field:SerializedName("mintempC") val minTempC: String? = null,
  @field:SerializedName("mintempF") val minTempF: String? = null,
  @field:SerializedName("totalSnow_cm") val totalSnowInCm: String? = null,
  val sunHour: String? = null,
  val uvIndex: String? = null,
  val hourly: List<HourlyForecast>? = null): Parcelable {
  companion object {
    val EMPTY = DailyForecast()
  }
}