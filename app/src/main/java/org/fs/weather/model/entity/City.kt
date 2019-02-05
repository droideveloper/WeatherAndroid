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
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "cities")
@Parcelize
class City @Ignore constructor(
  @field:PrimaryKey(autoGenerate = true) var cityId: Long? = null,
  var areaName: List<ValueObject>? = null,
  var country: List<ValueObject>? = null,
  var region: List<ValueObject>? = null,
  var lattitude: String? = null,
  var longitude: String? = null,
  var population: String? = null,
  var weatherUrl: List<ValueObject>? = null): Parcelable {

  // will be used by Room to initialize new instance of this object
  constructor(): this(null, null, null, null, null, null, null, null)

  companion object {
    val EMPTY = City()
  }
}