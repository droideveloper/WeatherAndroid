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

package org.fs.weather.widget

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import org.fs.weather.R

class ElevatedConstraintLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, style: Int = 0): ConstraintLayout(context, attrs, style) {

  private val defaultElevation by lazy { resources.getDimension(R.dimen.default_elevation) }

  init {
    ViewCompat.setElevation(this, defaultElevation)
  }
}