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

package uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.browse

import androidx.fragment.app.Fragment
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.Row
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.grid.glide.GlideVerticalGridFragment
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.grid.picasso.PicassoVerticalGridFragment
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.grid.simple.SimpleVerticalGridFragment

class MainBrowseFragmentFactory(
        private val updateDelay: Long? = null,
        private val blurScale: Float? = null,
        private val blurRadius: Float? = null
) : BrowseSupportFragment.FragmentFactory<Fragment>() {

    override fun createFragment(any: Any): Fragment = when ((any as Row).headerItem.id.toInt()) {
        0 -> SimpleVerticalGridFragment.newInstance(updateDelay, blurScale, blurRadius)
        1 -> GlideVerticalGridFragment.newInstance(updateDelay, blurScale, blurRadius)
        2 -> PicassoVerticalGridFragment.newInstance(updateDelay, blurScale, blurRadius)
        else -> throw IllegalArgumentException(String.format("Invalid row %s", any))
    }
}