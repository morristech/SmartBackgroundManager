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

package uz.pentoligy.smartbackgroundmanagerdemo.presentation.leanbackpresenter

import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import uz.pentoligy.smartbackgroundmanagerdemo.R
import uz.pentoligy.smartbackgroundmanagerdemo.entity.MovieItem

abstract class MovieItemPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val imageCardView: ImageCardView = ImageCardView(parent.context).apply {
            findViewById<View>(R.id.content_text).visibility = View.GONE
            setMainImageDimensions(IMAGE_CARD_VIEW_WIDTH, IMAGE_CARD_VIEW_WIDTH_HEIGHT)
            cardType = ImageCardView.CARD_TYPE_INFO_UNDER
            isFocusable = true
            isFocusableInTouchMode = true
        }

        return Presenter.ViewHolder(imageCardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) =
            onBindViewHolder(viewHolder.view as ImageCardView, item as MovieItem)

    override fun onUnbindViewHolder(viewHolder: ViewHolder) =
            onUnbindViewHolder(viewHolder.view as ImageCardView)

    private companion object {
        const val IMAGE_CARD_VIEW_WIDTH: Int = 238
        const val IMAGE_CARD_VIEW_WIDTH_HEIGHT: Int = 313
    }

    protected abstract fun onBindViewHolder(imageCardView: ImageCardView, item: MovieItem)

    protected abstract fun onUnbindViewHolder(imageCardView: ImageCardView)

}