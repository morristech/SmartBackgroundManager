/*
 * Copyright (c) 2018 Sanjar Khodjaev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package uz.pentoligy.smartbackgroundmanagerdemo.presentation.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.guided.BackgroundManagerGuidedStepFragment

class MainLeanbackActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null)
            startFragment(BackgroundManagerGuidedStepFragment.newInstance())
    }

    fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, fragment, fragment::class.java.name)
                .addToBackStack(null)
                .commit()
    }

    override fun onBackPressed() =
            if (supportFragmentManager.backStackEntryCount == 1) finish() else super.onBackPressed()
}