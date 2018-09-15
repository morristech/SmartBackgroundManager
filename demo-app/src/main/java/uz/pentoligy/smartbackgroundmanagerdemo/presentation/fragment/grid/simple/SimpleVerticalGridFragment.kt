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

package uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.grid.simple

import android.os.Bundle
import android.view.View
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ImageCardView
import uz.pentoligy.smartbackgroundmanagerdemo.entity.MovieItem
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.grid.BaseVerticalGridFragment
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.utils.FragmentUtils
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.leanbackpresenter.picasso.PicassoMovieItemPresenter

class SimpleVerticalGridFragment : BaseVerticalGridFragment() {

    private val movieItems: List<MovieItem> = MovieItem.upcomingMoviesItems

    private val picassoAdapter: ArrayObjectAdapter by lazy {
        ArrayObjectAdapter(PicassoMovieItemPresenter())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = "Upcoming Movies '18"
        adapter = picassoAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        picassoAdapter.apply { clear(); addAll(picassoAdapter.size(), movieItems) }
    }

    override fun onItemSelected(imageCardView: ImageCardView, item: MovieItem) {
//        backgroundManager.fromDrawable(imageCardView.mainImage)
        backgroundManager.fromImageCardView(imageCardView)
    }

    companion object {

        fun newInstance(
                updateDelay: Long? = null, blurScale: Float? = null, blurRadius: Float? = null
        ): SimpleVerticalGridFragment = SimpleVerticalGridFragment().apply {
            arguments = FragmentUtils.baseBundle(updateDelay, blurScale, blurRadius)
        }

    }

}