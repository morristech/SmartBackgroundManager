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

package uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.grid.glide

import android.os.Bundle
import android.view.View
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ImageCardView
import com.bumptech.glide.Glide
import uz.pentoligy.smartbackgroundmanager.SmartBackgroundManagerGlideTarget
import uz.pentoligy.smartbackgroundmanagerdemo.entity.MovieItem
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.grid.BaseVerticalGridFragment
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.utils.FragmentUtils
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.leanbackpresenter.glide.GlideMovieItemPresenter

class GlideVerticalGridFragment : BaseVerticalGridFragment() {

    private val movieItems: List<MovieItem> = MovieItem.marvelCinematicUniverse1MoviesItems

    private val glideAdapter: ArrayObjectAdapter by lazy {
        ArrayObjectAdapter(GlideMovieItemPresenter())
    }

    private val glideTarget: SmartBackgroundManagerGlideTarget by lazy {
        SmartBackgroundManagerGlideTarget(backgroundManager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = "Marvel Cinematic Universe Movies: First And Second Phases"
        adapter = glideAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glideAdapter.apply { clear(); addAll(glideAdapter.size(), movieItems) }
    }

    override fun onItemSelected(imageCardView: ImageCardView, item: MovieItem) {
        Glide.with(requireContext()).apply {
            clear(glideTarget)
            asBitmap().load(item.backgroundPoster).into(glideTarget)
        }
    }

    override fun onDestroy() {
        Glide.with(requireActivity()).clear(glideTarget)
        super.onDestroy()
    }

    companion object {

        fun newInstance(
                updateDelay: Long? = null, blurScale: Float? = null, blurRadius: Float? = null
        ): GlideVerticalGridFragment = GlideVerticalGridFragment().apply {
            arguments = FragmentUtils.baseBundle(updateDelay, blurScale, blurRadius)
        }

    }
}