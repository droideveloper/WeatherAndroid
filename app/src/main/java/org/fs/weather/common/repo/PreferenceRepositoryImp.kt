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

package org.fs.weather.common.repo

import android.content.Context
import android.content.SharedPreferences
import org.fs.architecture.mvi.util.EMPTY
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceRepositoryImp @Inject constructor(context: Context): PreferenceRepository {

  companion object {
    private const val APP_PREFERENCES = "app.preferences"

    private const val PREF_KEY_SELECTED_CITY = "pref.key.selected.city"
  }

  private val preferences by lazy { context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE) }

  override var selectedCityName: String
    get() = preferences.getString(PREF_KEY_SELECTED_CITY, String.EMPTY) ?: String.EMPTY
    set(value) {
      preferences.edit {
        putString(PREF_KEY_SELECTED_CITY, value)
      }
    }

  private fun SharedPreferences.edit(block: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    block(editor)
    editor.apply()
  }
}