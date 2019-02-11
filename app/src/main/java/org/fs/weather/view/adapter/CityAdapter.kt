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

package org.fs.weather.view.adapter

import android.view.ViewGroup
import org.fs.architecture.mvi.common.ForFragment
import org.fs.architecture.mvi.core.AbstractRecyclerViewAdapter
import org.fs.architecture.mvi.util.ObservableList
import org.fs.weather.model.entity.City
import org.fs.weather.view.holder.BaseCityViewHolder
import org.fs.weather.view.holder.CityViewHolder
import javax.inject.Inject

@ForFragment
class CityAdapter @Inject constructor(dataSet: ObservableList<City>): AbstractRecyclerViewAdapter<City, BaseCityViewHolder>(dataSet) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCityViewHolder = CityViewHolder(parent)
}