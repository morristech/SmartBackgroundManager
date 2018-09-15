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

package uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.grid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.app.VerticalGridSupportFragment
import androidx.leanback.widget.*
import uz.pentoligy.smartbackgroundmanager.RenderScriptOptions
import uz.pentoligy.smartbackgroundmanager.SmartBackgroundManager
import uz.pentoligy.smartbackgroundmanagerdemo.R
import uz.pentoligy.smartbackgroundmanagerdemo.entity.MovieItem
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.utils.FragmentUtils

open class BaseVerticalGridFragment : VerticalGridSupportFragment(),
        BrowseSupportFragment.MainFragmentAdapterProvider, OnItemViewSelectedListener {

    protected lateinit var backgroundManager: SmartBackgroundManager private set
    private lateinit var verticalGridPresenter: VerticalGridPresenter
    private var mainFragmentAdapter: BrowseSupportFragment.MainFragmentAdapter<BaseVerticalGridFragment>? = null

    override fun getMainFragmentAdapter(): BrowseSupportFragment.MainFragmentAdapter<BaseVerticalGridFragment> {
        if (mainFragmentAdapter == null) {
            mainFragmentAdapter = BrowseSupportFragment.MainFragmentAdapter(this)
        }

        return mainFragmentAdapter!!
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        verticalGridPresenter = VerticalGridPresenter()
        verticalGridPresenter.numberOfColumns = 3
        gridPresenter = verticalGridPresenter

        setOnItemViewSelectedListener(this)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val smartBackgroundManagerBuilder: SmartBackgroundManager.Builder =
                SmartBackgroundManager.Builder(this)

        if (requestedRenderScriptOptionsBlurScale != null ||
                requestedRenderScriptOptionsBlurRadius != null) {

            val builder: RenderScriptOptions.Builder =
                    RenderScriptOptions.Builder(this).apply {
                requestedRenderScriptOptionsBlurScale?.let { withBlurScale(it) }
                requestedRenderScriptOptionsBlurRadius?.let { withBlurRadius(it) }
            }

            smartBackgroundManagerBuilder.withRenderScriptOptions(builder.build())

        }

        requestedBackgroundUpdateDelay?.let { smartBackgroundManagerBuilder.withUpdateDelay(it) }
        backgroundManager = smartBackgroundManagerBuilder.build()
    }

    override fun onDestroy() {
        backgroundManager.onDestroy()
        super.onDestroy()
    }

    @Deprecated("",
            ReplaceWith(
                    "if (itemViewHolder != null) {" +
                            "onItemSelected (itemViewHolder.view as ImageCardView, item as MovieItem)"
            )
    )
    override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?, item: Any,
            rowViewHolder: RowPresenter.ViewHolder?, row: Row?
    ) {

        (itemViewHolder?.view as? ImageCardView)?.let {
            backgroundManager.fromColor(R.color.colorPrimary)
            onItemSelected(it, item as MovieItem)
        }
    }

    protected open fun onItemSelected(imageCardView: ImageCardView, item: MovieItem) { /* ignored */ }

    private val requestedBackgroundUpdateDelay: Long? by lazy {
        FragmentUtils.getRequestedBackgroundUpdateDelay(this)
    }

    private val requestedRenderScriptOptionsBlurScale: Float? by lazy {
        FragmentUtils.getRequestedRenderScriptOptionsBlurScale(this)
    }

    private val requestedRenderScriptOptionsBlurRadius: Float? by lazy {
        FragmentUtils.getRequestedRenderScriptOptionsBlurRadius(this)
    }
}