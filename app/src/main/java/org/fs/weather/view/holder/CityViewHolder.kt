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

package org.fs.weather.view.holder

import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.view_city_item.view.*
import org.fs.architecture.mvi.common.BusManager
import org.fs.architecture.mvi.util.inflate
import org.fs.architecture.mvi.util.plusAssign
import org.fs.rx.extensions.util.clicks
import org.fs.weather.R
import org.fs.weather.model.entity.City
import org.fs.weather.model.event.SelectCityEvent

class CityViewHolder(view: View): BaseCityViewHolder(view) {

  private val disposeBag by lazy { CompositeDisposable() }

  private val viewTextCityName by lazy { itemView.viewTextCityName }
  private val viewTextCountryName by lazy { itemView.viewTextCountryName }
  private val viewTextPopulation by lazy { itemView.viewTextPopulation }

  constructor(parent: ViewGroup): this(parent.inflate(R.layout.view_city_item))

  override fun bind(value: City) {
    viewTextCityName.text = value.areaName?.firstOrNull()?.value
    viewTextCountryName.text = value.country?.firstOrNull()?.value
    viewTextPopulation.text = value.population

    disposeBag += bindSelectCityEvent(value).subscribe(BusManager.Companion::send)
  }

  override fun unbind() = disposeBag.clear()

  private fun bindSelectCityEvent(entity: City): Observable<SelectCityEvent> = itemView.clicks()
    .map { SelectCityEvent(entity) }
}