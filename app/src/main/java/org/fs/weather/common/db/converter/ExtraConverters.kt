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

package org.fs.weather.common.db.converter

import android.arch.persistence.room.TypeConverter
import android.text.TextUtils
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.fs.weather.model.entity.*
import org.fs.weather.model.net.Avrage

sealed class ExtraConverters {

  companion object {

    @JvmStatic private val serializer = GsonBuilder().create()
    // will allow me create one time only
    // request
    @JvmStatic private val typeTokenRequest = object: TypeToken<List<Request>>() {}
    @JvmStatic private val typeAdapterRequest = serializer.getAdapter(typeTokenRequest)
    // currentCondition
    @JvmStatic private val typeTokenCurrentCondition = object: TypeToken<List<CurrentCondition>>() {}
    @JvmStatic private val typeAdapterCurrentCondition = serializer.getAdapter(typeTokenCurrentCondition)
    // dailyForecast
    @JvmStatic private val typeTokenDailyForecast = object: TypeToken<List<DailyForecast>>() {}
    @JvmStatic private val typeAdapterDailyForecast = serializer.getAdapter(typeTokenDailyForecast)
    // averages
    @JvmStatic private val typeTokenClimateAverages = object: TypeToken<List<Avrage>>() { }
    @JvmStatic private val typeAdapterClimateAverages = serializer.getAdapter(typeTokenClimateAverages)
    // valueObject
    @JvmStatic private val typeTokenValueObject = object: TypeToken<List<ValueObject>>() {}
    @JvmStatic private val typeAdapterValueObject = serializer.getAdapter(typeTokenValueObject)

    @JvmStatic @TypeConverter fun convertRequestListToString(requests: List<Request>?): String? {
      if (requests != null && requests.isNotEmpty()) {
        return typeAdapterRequest.toJson(requests)
      }
      return null
    }

    @JvmStatic @TypeConverter fun convertStringToRequestList(value: String?): List<Request>? {
      if (!TextUtils.isEmpty(value)) {
        return typeAdapterRequest.fromJson(value)
      }
      return null
    }

    @JvmStatic @TypeConverter fun convertCurrentConditionListToString(currentConditions: List<CurrentCondition>?): String? {
      if (currentConditions != null && currentConditions.isNotEmpty()) {
        return typeAdapterCurrentCondition.toJson(currentConditions)
      }
      return null
    }

    @JvmStatic @TypeConverter fun convertStringToCurrentConditionList(value: String?): List<CurrentCondition>? {
      if (!TextUtils.isEmpty(value)) {
        return typeAdapterCurrentCondition.fromJson(value)
      }
      return null
    }

    @JvmStatic @TypeConverter fun convertDailyForecastListToString(dailyForecasts: List<DailyForecast>?): String? {
      if (dailyForecasts != null && dailyForecasts.isNotEmpty()) {
        return typeAdapterDailyForecast.toJson(dailyForecasts)
      }
      return null
    }

    @JvmStatic @TypeConverter fun convertStringToDailyForecastList(value: String?): List<DailyForecast>? {
      if (!TextUtils.isEmpty(value)) {
        return typeAdapterDailyForecast.fromJson(value)
      }
      return null
    }

    @JvmStatic @TypeConverter fun convertValueObjectListToString(valueObjects: List<ValueObject>?): String? {
      if (valueObjects != null && valueObjects.isNotEmpty()) {
        return typeAdapterValueObject.toJson(valueObjects)
      }
      return null
    }

    @JvmStatic @TypeConverter fun convertStringToValueObjectList(value: String?): List<ValueObject>? {
      if (!TextUtils.isEmpty(value)) {
        return typeAdapterValueObject.fromJson(value)
      }
      return null
    }

    @JvmStatic @TypeConverter fun convertCliamgeAveragesToString(value: List<Avrage>?): String? {
      if (value != null) {
        return typeAdapterClimateAverages.toJson(value)
      }
      return null
    }

    @JvmStatic @TypeConverter fun convertStringToClimateAverages(value: String?): List<Avrage>? {
      if (!TextUtils.isEmpty(value)) {
        return typeAdapterClimateAverages.fromJson(value)
      }
      return null
    }
  }
}