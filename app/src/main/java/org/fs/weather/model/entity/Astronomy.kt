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
data class Astronomy(
  val sunrise: String? = null,
  val sunset: String? = null,
  val moonrise: String? = null,
  @field:SerializedName("moonset") val moonSet: String? = null,
  @field:SerializedName("moon_phase") val moonPhase: String? = null,
  @field:SerializedName("moon_illumination") val moonIllumination: String? = null): Parcelable {
  companion object {
    val EMPTY = Astronomy()
  }
}