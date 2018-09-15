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

package uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.guided

import android.os.Bundle
import android.text.InputType
import androidx.leanback.app.GuidedStepSupportFragment
import androidx.leanback.widget.GuidanceStylist
import androidx.leanback.widget.GuidedAction
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.activity.MainLeanbackActivity
import uz.pentoligy.smartbackgroundmanagerdemo.presentation.fragment.browse.MainBrowseFragment

class BackgroundManagerGuidedStepFragment : GuidedStepSupportFragment() {

    private var updateDelayValue: Long? = null
    private var renderScriptBlurScaleValue: Float? = null
    private var renderScriptBlurRadiusValue: Float? = null

    override fun onCreateGuidance(savedInstanceState: Bundle?): GuidanceStylist.Guidance {
        return GuidanceStylist.Guidance(
                "SmartBackgroundManager",
                "For more information, please visit https://git.io/vpHB5",
                null, null
        )
    }

    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        actions.apply {
            add(GuidedAction.Builder(requireContext())
                    .id(GUIDED_ACTION_UPDATE_DELAY)
                    .editable(true)
                    .editTitle("")
                    .inputType(InputType.TYPE_CLASS_NUMBER)
                    .title("Background Update Delay")
                    .description(
                            "Setting up the delay of changing background in milliseconds\n" +
                                    "(default: 150)"
                    )
                    .build())

            add(GuidedAction.Builder(requireContext())
                    .id(GUIDED_ACTION_RENDERSCRIPT_BLUR_SCALE)
                    .editable(true)
                    .editTitle("")
                    .inputType(InputType.TYPE_NUMBER_FLAG_DECIMAL)
                    .title("Background Image Blur Scale")
                    .description(
                            "Setting up the image blur scale value\n" +
                                    "(default: 0.5)"
                    )
                    .build())

            add(GuidedAction.Builder(requireContext())
                    .id(GUIDED_ACTION_RENDERSCRIPT_BLUR_RADIUS)
                    .editable(true)
                    .editTitle("")
                    .inputType(InputType.TYPE_NUMBER_FLAG_DECIMAL)
                    .title("Background Image Blur Radius")
                    .description(
                            "Setting up the image blur radius value\n" +
                                    "(default: 10)"
                    )
                    .build())
        }
    }

    override fun onCreateButtonActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        actions.add(
                GuidedAction.Builder(requireContext())
                        .clickAction(GUIDED_ACTION_APPLY)
                        .title("Apply")
                        .enabled(true)
                        .build()
        )
    }

    override fun onGuidedActionEditedAndProceed(action: GuidedAction?): Long {
        when (action?.id) {
            GUIDED_ACTION_UPDATE_DELAY ->
                updateDelayValue = action.editTitle.toString().toLongOrNull()

            GUIDED_ACTION_RENDERSCRIPT_BLUR_SCALE ->
                renderScriptBlurScaleValue = action.editTitle.toString().toFloatOrNull()

            GUIDED_ACTION_RENDERSCRIPT_BLUR_RADIUS -> {
                val blurRadius = action.editTitle.toString().toFloatOrNull()
                blurRadius?.let { renderScriptBlurRadiusValue = if (it < 1) null else it }
            }
        }

        return GuidedAction.ACTION_ID_NEXT
    }

    override fun onGuidedActionClicked(action: GuidedAction?) = when (action?.id) {
        GUIDED_ACTION_APPLY -> startMainBrowseFragment(
                updateDelayValue, renderScriptBlurScaleValue, renderScriptBlurRadiusValue
        )
        else -> super.onGuidedActionClicked(action)
    }

    private fun startMainBrowseFragment(
            updateDelay: Long? = null, blurScale: Float? = null, blurRadius: Float? = null
    ) = (requireActivity() as MainLeanbackActivity)
            .startFragment(MainBrowseFragment.newInstance(updateDelay, blurScale, blurRadius))

    companion object {
        private const val GUIDED_ACTION_APPLY: Long = 0
        private const val GUIDED_ACTION_UPDATE_DELAY: Long = 1
        private const val GUIDED_ACTION_RENDERSCRIPT_BLUR_SCALE: Long = 2
        private const val GUIDED_ACTION_RENDERSCRIPT_BLUR_RADIUS: Long = 3

        fun newInstance(): BackgroundManagerGuidedStepFragment =
                BackgroundManagerGuidedStepFragment().apply { arguments = Bundle() }
    }

}