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

package uz.pentoligy.smartbackgroundmanager

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception
import java.lang.ref.WeakReference

class SmartBackgroundManagerPicassoTarget(smartBackgroundManager: SmartBackgroundManager) : Target {

    private val smartBackgroundManagerReference: WeakReference<SmartBackgroundManager> =
            WeakReference(smartBackgroundManager)

    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        placeHolderDrawable?.let { smartBackgroundManagerReference.get()?.fromDrawable(it) }
    }

    override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
        smartBackgroundManagerReference.get()?.fromBitmap(bitmap)
    }

    override fun onBitmapFailed(e: Exception, errorDrawable: Drawable?) {
        errorDrawable?.let { smartBackgroundManagerReference.get()?.fromDrawable(it) }
    }

}