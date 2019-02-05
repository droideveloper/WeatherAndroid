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

package org.fs.weather.common.db.dao

import android.arch.persistence.room.*
import io.reactivex.Single
import org.fs.weather.model.entity.City

@Dao interface CityDao {

  @Query("SELECT * FROM cities") fun loadCities(): Single<List<City>>
  @Update(onConflict = OnConflictStrategy.REPLACE) fun update(city: City)
  @Insert(onConflict = OnConflictStrategy.REPLACE) fun create(city: City)
  @Delete fun delete(city: City)
}