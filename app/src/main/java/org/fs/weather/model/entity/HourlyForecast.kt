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
data class HourlyForecast(
  val time: String? = null,
  val tempC: String? = null,
  val tempF: String? = null,
  val weatherCode: String? = null,
  val weatherIconUrl: List<WeatherIconUrl>? = null,
  val weatherDesc: List<WeatherDesc>? = null,
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
  @field:SerializedName("HeatIndexC") val heatIndexC: String? = null,
  @field:SerializedName("HeatIndexF") val heatIndexF: String? = null,
  @field:SerializedName("DewPointC") val dewPointC: String? = null,
  @field:SerializedName("DewPointF") val dewPointF: String? = null,
  @field:SerializedName("WindChillC") val windChillC: String? = null,
  @field:SerializedName("WindChillF") val windChillF: String? = null,
  @field:SerializedName("WindGustC") val windGustC: String? = null,
  @field:SerializedName("WindGustF") val windGustF: String? = null,
  @field:SerializedName("chanceofrain") val rain: String? = null,
  @field:SerializedName("chanceofremdry") val remdry: String? = null,
  @field:SerializedName("chanceofwindy") val windy: String? = null,
  @field:SerializedName("chanceofovercast") val overcast: String? = null,
  @field:SerializedName("chanceofsunshine") val sunshine: String? = null,
  @field:SerializedName("chanceofforst") val frost: String? = null,
  @field:SerializedName("chanceofhightemp") val highTemp: String? = null,
  @field:SerializedName("chanceoffog") val fog: String? = null,
  @field:SerializedName("chanceofsnow") val snow: String? = null,
  @field:SerializedName("chanceofthunder") val thunder: String? = null): Parcelable {
  companion object {
    val EMPTY = HourlyForecast()
  }
}