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

package uz.pentoligy.smartbackgroundmanagerdemo.presentation.leanbackpresenter.glide

import androidx.leanback.widget.ImageCardView
import com.bumptech.glide.Glide
import uz.pentoligy.smartbackgroundmanagerdemo.entity.MovieItem
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.leanbackpresenter.MovieItemPresenter

class GlideMovieItemPresenter : MovieItemPresenter() {

    override fun onBindViewHolder(imageCardView: ImageCardView, item: MovieItem) {
        imageCardView.titleText = item.title
        Glide.with(imageCardView.mainImageView).load(item.poster).into(imageCardView.mainImageView)
    }

    override fun onUnbindViewHolder(imageCardView: ImageCardView) =
            Glide.with(imageCardView.mainImageView).clear(imageCardView.mainImageView)
}