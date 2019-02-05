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
data class CurrentCondition(
  @field:SerializedName("observation_time") val time: String? = null,
  @field:SerializedName("temp_C") val tempC: String? = null,
  @field:SerializedName("temp_F") val tempF: String? = null,
  val weatherCode: String? = null,
  val weatherIconUrl: List<ValueObject>? = null,
  val weatherDesc: List<ValueObject>? = null,
  @field:SerializedName("windspeedMiles") val windSpeedM: String? = null,
  @field:SerializedName("windspeedKmph") val windSpeedKm: String? = null,
  @field:SerializedName("winddirDegree") val windDirDegree: String? = null,
  @field:SerializedName("winddir16Point") val windDirPoint: String? = null,
  @field:SerializedName("precipMM") val precip: String? = null,
  val humidity: String? = null,
  val visibility: String? = null,
  val pressure: String? = null,
  @field:SerializedName("cloudcover") val cloudCover: String? = null,
  @field:SerializedName("FeelsLikeC") val tempFeltC: String? = null,
  @field:SerializedName("FeelsLikeF") val tempFeltF: String? = null,
  val uvIndex: Int? = null): Parcelable {
  companion object {
    val EMPTY = CurrentCondition()
  }
}