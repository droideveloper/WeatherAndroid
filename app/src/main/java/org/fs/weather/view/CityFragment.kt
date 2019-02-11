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
package org.fs.weather.view

import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.view_city_fragment.*
import org.fs.architecture.mvi.common.Operation
import org.fs.architecture.mvi.core.AbstractFragment
import org.fs.architecture.mvi.util.ObservableList
import org.fs.architecture.mvi.util.plusAssign
import org.fs.rx.extensions.util.EMPTY
import org.fs.rx.extensions.v7.util.queryChanges
import org.fs.weather.R
import org.fs.weather.model.CityModel
import org.fs.weather.model.entity.City
import org.fs.weather.model.event.SearchCityEvent
import org.fs.weather.util.C.Companion.RECYCLER_CACHE_SIZE
import org.fs.weather.util.Operations.Companion.REFRESH
import org.fs.weather.util.bind
import org.fs.weather.view.adapter.CityAdapter
import org.fs.weather.vm.CityFragmentViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CityFragment : AbstractFragment<CityModel, CityFragmentViewModel>(), CityFragmentView {

  override val layoutRes: Int get() = R.layout.view_city_fragment

  private val dividerDrawable by lazy { ResourcesCompat.getDrawable(resources, R.drawable.ic_vertical_divider, context?.theme) }

  @Inject lateinit var dataSet: ObservableList<City>
  @Inject lateinit var cityAdapter: CityAdapter

  private var q = String.EMPTY

  override fun setUp(state: Bundle?) {
    // set up views
    viewRecycler.apply {
      setHasFixedSize(true)
      setItemViewCacheSize(RECYCLER_CACHE_SIZE)
      layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
      adapter = cityAdapter
      dividerDrawable?.let { drawable ->
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(drawable)
        addItemDecoration(divider)
      }
    }
    // set another view
    viewSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
    viewSwipeRefreshLayout.isEnabled = false
  }

  override fun attach() {
    super.attach()

    disposeBag += viewEditTextSearch.queryChanges()
      .debounce(500L, TimeUnit.MILLISECONDS)
      .map { SearchCityEvent(it.toString()) }
      .doOnNext {
        q = it.q // grab ref of it
        clearDataState()
      }
      .subscribe(::accept)

    disposeBag += viewModel.state()
      .map { state ->
        if (state is Operation) {
          return@map state.type == REFRESH
        }
        return@map false
      }.subscribe(viewSwipeRefreshLayout::bind)

    disposeBag += viewModel.storage()
      .subscribe(::render)
  }

  override fun render(model: CityModel) {
    // TODO implement this
  }

  private fun clearDataState() {
    dataSet.clear()
  }
}