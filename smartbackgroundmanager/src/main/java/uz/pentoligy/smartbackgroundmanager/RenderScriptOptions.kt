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

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

class RenderScriptOptions private constructor(
        private val renderScript: RenderScript,
        private val blurScale: Float, private val blurRadius: Float
) {

    internal fun from(source: Bitmap): Bitmap {

        val width: Int = Math.round(source.width * blurScale)
        val height: Int = Math.round(source.height * blurScale)

        val inputBitmap: Bitmap = Bitmap.createScaledBitmap(source, width, height, false)
        val outputBitmap: Bitmap = Bitmap.createBitmap(inputBitmap)

        val scriptIntrinsicBlur: ScriptIntrinsicBlur =
                ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))

        val allocationIn: Allocation = Allocation.createFromBitmap(renderScript, inputBitmap)
        val allocationOut : Allocation = Allocation.createFromBitmap(renderScript, outputBitmap)

        scriptIntrinsicBlur.setRadius(blurRadius)

        scriptIntrinsicBlur.setInput(allocationIn)
        scriptIntrinsicBlur.forEach(allocationOut)
        allocationOut.copyTo(outputBitmap)

        return outputBitmap
    }

    class Builder(context: Context) {

        constructor(activity: Activity) : this(activity.baseContext)

        constructor(fragment: Fragment) : this(fragment.requireContext())

        private val contextReference: WeakReference<Context> = WeakReference(context)
        private val renderScript: RenderScript

        private var blurScale: Float? = null
        private var blurRadius: Float? = null

        init { renderScript = RenderScript.create(contextReference.get()) }

        fun withBlurScale(bitmapScale: Float): Builder {
            if (this.blurScale == null) this.blurScale = bitmapScale
            return this
        }

        fun withBlurRadius(bitmapBlurRadius: Float): Builder {
            if (this.blurRadius == null) this.blurRadius = bitmapBlurRadius
            return this
        }

        fun build(): RenderScriptOptions =
                RenderScriptOptions(
                        renderScript,
                        blurScale ?: BLUR_SCALE,
                        blurRadius ?: BLUR_RADIUS
                )
    }

    companion object {
        private const val BLUR_SCALE: Float = 0.5f
        private const val BLUR_RADIUS: Float = 10f

        fun withDefaultOptions(
                context: Context
        ): RenderScriptOptions = RenderScriptOptions.Builder(context).build()
    }
}