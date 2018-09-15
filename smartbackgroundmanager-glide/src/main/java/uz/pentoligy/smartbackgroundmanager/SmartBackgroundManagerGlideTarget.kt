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

@file:Suppress("DEPRECATION")

package uz.pentoligy.smartbackgroundmanager

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import java.lang.ref.WeakReference

/**
 * [com.bumptech.glide.request.target.SimpleTarget] does not deprecated.
 * @see <a href="https://github.com/bumptech/glide/issues/2077">Glide into() SimpleTarget<File> issue</a>
 */
class SmartBackgroundManagerGlideTarget(
        smartBackgroundManager: SmartBackgroundManager
) : SimpleTarget<Bitmap>(smartBackgroundManager.displayWidth, smartBackgroundManager.displayHeight) {

    private val smartBackgroundManagerReference: WeakReference<SmartBackgroundManager> =
            WeakReference(smartBackgroundManager)

    override fun onLoadStarted(placeholder: Drawable?) {
        placeholder?.let { smartBackgroundManagerReference.get()?.fromDrawable(it) }
    }

    override fun onLoadCleared(placeholder: Drawable?) {
        placeholder?.let { smartBackgroundManagerReference.get()?.fromDrawable(it) }
    }

    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
        smartBackgroundManagerReference.get()?.fromBitmap(resource)
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {
        errorDrawable?.let { smartBackgroundManagerReference.get()?.fromDrawable(it) }
    }

}