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

package org.fs.weather.test.repo

import org.fs.weather.mock.repo.MockConnectivityRepository
import org.junit.Test

class TestConnectivityRepository {

  private val repo by lazy { MockConnectivityRepository(isConnected = false) }

  @Test fun connectivity() {
    assert(!repo.isConnected())
  }
}