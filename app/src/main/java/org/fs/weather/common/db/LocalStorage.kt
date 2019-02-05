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

package org.fs.weather.common.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import org.fs.architecture.mvi.common.db.Converters
import org.fs.weather.common.db.converter.ExtraConverters
import org.fs.weather.common.db.dao.CityDao
import org.fs.weather.common.db.dao.ForecastDao
import org.fs.weather.model.entity.City
import org.fs.weather.model.entity.Forecast

@Database(entities = [City::class, Forecast::class], version = 1) // entities
@TypeConverters(value = [Converters::class, ExtraConverters::class]) // converters
abstract class LocalStorage: RoomDatabase() {
  // city dao
  abstract fun cityDao(): CityDao
  // forecast dao
  abstract fun forecastDao(): ForecastDao
}