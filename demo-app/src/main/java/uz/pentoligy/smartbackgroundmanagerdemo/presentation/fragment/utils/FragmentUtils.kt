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

package uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.utils

import android.os.Bundle
import androidx.fragment.app.Fragment

object FragmentUtils {

    private const val BUNDLE_LONG_BACKGROUND_UPDATE_DELAY_TAG: String =
            "bundle_long_background_update_delay"

    private const val BUNDLE_FLOAT_RENDERSCRIPT_OPTIONS_BLUR_SCALE_TAG: String =
            "bundle_float_renderscript_options_blur_scale"

    private const val BUNDLE_FLOAT_RENDERSCRIPT_OPTIONS_BLUR_RADIUS_TAG: String =
            "bundle_float_renderscript_options_blur_radius"

    fun getRequestedBackgroundUpdateDelay(
            fragment: Fragment
    ): Long? = fragment.arguments?.let {
        if (it.get(BUNDLE_LONG_BACKGROUND_UPDATE_DELAY_TAG) != null)
            it.getLong(BUNDLE_LONG_BACKGROUND_UPDATE_DELAY_TAG)
        else null
    }

    fun getRequestedRenderScriptOptionsBlurScale(
            fragment: Fragment
    ): Float? = fragment.arguments?.let {
        if (it.get(BUNDLE_FLOAT_RENDERSCRIPT_OPTIONS_BLUR_SCALE_TAG) != null)
            it.getFloat(BUNDLE_FLOAT_RENDERSCRIPT_OPTIONS_BLUR_SCALE_TAG)
        else null
    }

    fun getRequestedRenderScriptOptionsBlurRadius(
            fragment: Fragment
    ): Float? =  fragment.arguments?.let {
        if (it.get(BUNDLE_FLOAT_RENDERSCRIPT_OPTIONS_BLUR_RADIUS_TAG) != null)
            it.getFloat(BUNDLE_FLOAT_RENDERSCRIPT_OPTIONS_BLUR_RADIUS_TAG)
        else null
    }

    fun baseBundle(
            updateDelay: Long? = null,
            blurScale: Float? = null,
            blurRadius: Float? = null
    ): Bundle {

        return Bundle().apply {
            updateDelay?.let { putLong(BUNDLE_LONG_BACKGROUND_UPDATE_DELAY_TAG, it) }
            blurScale?.let { putFloat(BUNDLE_FLOAT_RENDERSCRIPT_OPTIONS_BLUR_SCALE_TAG, it) }
            blurRadius?.let { putFloat(BUNDLE_FLOAT_RENDERSCRIPT_OPTIONS_BLUR_RADIUS_TAG, it) }
        }
    }

}