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

package uz.pentoligy.smartbackgroundmanagerdemo.presentation.leanbackpresenter.picasso

import androidx.leanback.widget.ImageCardView
import com.squareup.picasso.Picasso
import uz.pentoligy.smartbackgroundmanagerdemo.entity.MovieItem
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.leanbackpresenter.MovieItemPresenter

class PicassoMovieItemPresenter : MovieItemPresenter() {

    override fun onBindViewHolder(imageCardView: ImageCardView, item: MovieItem) {
        imageCardView.titleText = item.title
        Picasso.get().load(item.poster).into(imageCardView.mainImageView)
    }

    override fun onUnbindViewHolder(imageCardView: ImageCardView) =
            Picasso.get().cancelRequest(imageCardView.mainImageView)
}