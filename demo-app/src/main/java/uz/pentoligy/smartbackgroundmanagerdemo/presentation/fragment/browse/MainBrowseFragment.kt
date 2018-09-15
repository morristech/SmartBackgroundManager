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

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.PageRow
import uz.pentoligy.smartbackgroundmanagerdemo.R
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.utils.FragmentUtils

class MainBrowseFragment : BrowseSupportFragment() {

    private val movieRowItems: List<PageRow> by lazy {
        ArrayList<PageRow>().apply {
            add(PageRow(HeaderItem(0, "Demo (Simple)")))
            add(PageRow(HeaderItem(1, "Demo with Glide")))
            add(PageRow(HeaderItem(2, "Demo with Picasso")))
        }
    }

    private val browseAdapter: ArrayObjectAdapter by lazy { ArrayObjectAdapter(ListRowPresenter()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        brandColor = ContextCompat.getColor(requireContext(), R.color.colorPrimary)

        adapter = browseAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        browseAdapter.addAll(browseAdapter.size(), movieRowItems)

        if (savedInstanceState == null) {
            mainFragmentRegistry?.registerFragment(
                    PageRow::class.java, MainBrowseFragmentFactory(
                    requestedBackgroundUpdateDelay,
                    requestedRenderScriptOptionsBlurScale,
                    requestedRenderScriptOptionsBlurRadius
            ))
        }
    }

    private val requestedBackgroundUpdateDelay: Long? by lazy {
        FragmentUtils.getRequestedBackgroundUpdateDelay(this)
    }

    private val requestedRenderScriptOptionsBlurScale: Float? by lazy {
        FragmentUtils.getRequestedRenderScriptOptionsBlurScale(this)
    }

    private val requestedRenderScriptOptionsBlurRadius: Float? by lazy {
        FragmentUtils.getRequestedRenderScriptOptionsBlurRadius(this)
    }

    companion object {

        fun newInstance(
                updateDelay: Long? = null, blurScale: Float? = null, blurRadius: Float? = null
        ): MainBrowseFragment =
                MainBrowseFragment().apply {
                    arguments = FragmentUtils.baseBundle(updateDelay, blurScale, blurRadius)
                }
    }
}