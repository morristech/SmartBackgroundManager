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

package uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.grid.picasso

import android.os.Bundle
import android.view.View
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ImageCardView
import com.squareup.picasso.Picasso
import uz.pentoligy.smartbackgroundmanager.SmartBackgroundManagerPicassoTarget
import uz.pentoligy.smartbackgroundmanagerdemo.entity.MovieItem
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.grid.BaseVerticalGridFragment
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.utils.FragmentUtils
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.leanbackpresenter.picasso.PicassoMovieItemPresenter

class PicassoVerticalGridFragment : BaseVerticalGridFragment() {

    private val movieItems: List<MovieItem> = MovieItem.marvelCinematicUniverse2MoviesItems

    private val picassoAdapter: ArrayObjectAdapter by lazy {
        ArrayObjectAdapter(PicassoMovieItemPresenter())
    }

    private val picassoTarget: SmartBackgroundManagerPicassoTarget by lazy {
        SmartBackgroundManagerPicassoTarget(backgroundManager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = "Marvel Cinematic Universe Movies: Phase Three"
        adapter = picassoAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        picassoAdapter.apply { clear(); addAll(picassoAdapter.size(), movieItems) }
    }

    override fun onItemSelected(imageCardView: ImageCardView, item: MovieItem) {
        Picasso.get().apply {
            cancelRequest(picassoTarget)
            load(item.backgroundPoster)
                    .resize(backgroundManager.displayWidth, backgroundManager.displayHeight)
                    .centerCrop()
                    .into(picassoTarget)
        }
    }

    override fun onDestroy() {
        Picasso.get().cancelRequest(picassoTarget)
        super.onDestroy()
    }

    companion object {

        fun newInstance(
                updateDelay: Long? = null, blurScale: Float? = null, blurRadius: Float? = null
        ): PicassoVerticalGridFragment = PicassoVerticalGridFragment().apply {
            arguments = FragmentUtils.baseBundle(updateDelay, blurScale, blurRadius)
        }
    }

}