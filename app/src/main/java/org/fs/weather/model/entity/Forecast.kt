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

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.fs.weather.model.net.Avrage
import org.fs.weather.model.net.ClimateAverages

@Entity(tableName = "forecasts")
@Parcelize
class Forecast @Ignore constructor(
  @field:PrimaryKey(autoGenerate = true) var forecastId: Long? = null,
  var request: List<Request>? = null,
  @field:SerializedName("current_condition") var currentCondition: List<CurrentCondition>? = null,
  var weather: List<DailyForecast>? = null,
  @field:SerializedName("ClimateAverages") var climateAverages: List<Avrage>? = null,
  var cityId: Long? = null): Parcelable {

  // default constructor
  constructor(): this(null, null, null, null, null, null)

  companion object {
    val EMPTY = Forecast()
  }
}