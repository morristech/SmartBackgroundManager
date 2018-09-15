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

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.util.Log
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.leanback.app.BackgroundManager
import androidx.leanback.widget.ImageCardView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import uz.pentoligy.smartbackgroundmanager.extensions.toBitmap
import uz.pentoligy.smartbackgroundmanager.rxutils.CompositeObserver
import uz.pentoligy.smartbackgroundmanager.rxutils.DisposableObserver
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
class SmartBackgroundManager private constructor(
        activity: Activity, renderScriptOptions: RenderScriptOptions?, updateDelay: Long
) {

    private val compositeObserver: CompositeObserver = CompositeObserver()

    private val publishBitmapSubject: PublishSubject<Any> = PublishSubject.create()

    private val activityReference: WeakReference<Activity> = WeakReference(activity)

    private val backgroundManagerReference: WeakReference<BackgroundManager> = WeakReference(
            BackgroundManager.getInstance(activity).apply { if (!isAttached) attach(activity.window) }
    )

    private val displayMetrics: DisplayMetrics = DisplayMetrics().also {
        activity.windowManager.defaultDisplay.getMetrics(it)
    }

    private val backgroundUpdateDelay: Long = updateDelay

    init {
        val bitmapObserver = BitmapObserver()

        publishBitmapSubject
                .debounce(backgroundUpdateDelay, TimeUnit.MILLISECONDS)
                .flatMap { bitmapObservable(it) }
                .map { renderScriptOptions?.from(it) ?: it }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(bitmapObserver)
    }

    val displayWidth: Int by lazy { displayMetrics.widthPixels }

    val displayHeight: Int by lazy { displayMetrics.heightPixels }

    fun fromColor(@ColorRes colorResId: Int) {
        val colorInt: Int? = activityReference.get()?.let { ContextCompat.getColor(it, colorResId) }
        if (colorInt != null) fromDrawable(ColorDrawable(colorInt)) else clearBackground()
    }

    fun fromDrawable(@DrawableRes drawableResId: Int) {
        val drawable: Drawable? =
                activityReference.get()?.let { ContextCompat.getDrawable(it, drawableResId) }

        fromDrawable(drawable)
    }

    fun fromDrawable(drawable: Drawable?) { if (drawable != null) publishBitmapSubject.onNext(drawable) }

    fun fromBitmap(bitmap: Bitmap?) { if (bitmap != null) publishBitmapSubject.onNext(bitmap) }

    fun fromImageCardView(imageCardView: ImageCardView?) = fromDrawable(imageCardView?.mainImage)

    fun clearBackground() { backgroundManagerReference.get()?.clearDrawable() }

    fun onDestroy() {
        activityReference.get()?.let {
            if (it.isFinishing) clearBackground(); compositeObserver.dispose()
        }
    }

    private fun bitmapObservable(any: Any): Observable<Bitmap> =
            Observable.create { emitter ->

                try {

                    var bitmap: Bitmap? = null

                    when (any) {
                        is Bitmap -> bitmap = any
                        is Drawable -> bitmap = any.toBitmap()
                        else -> emitter.onError(TypeCastException())
                    }

                    val backgroundBitmap: Bitmap =
                            bitmap?.let { Bitmap.createBitmap(it.copy(it.config, true)) } ?:
                            throw Exception(NullPointerException())

                    emitter.onNext(backgroundBitmap)

                } catch (exception: Exception) {
                    Log.wtf(this::class.java.simpleName, exception)
                    emitter.onError(exception)
                }

            }

    private inner class BitmapObserver: DisposableObserver<Bitmap>(compositeObserver) {

        override fun onNext(item: Bitmap) { backgroundManagerReference.get()?.setBitmap(item) }

        override fun onError(e: Throwable): Unit = clearBackground()

    }

    class Builder(private val activity: Activity) {

        constructor(fragment: Fragment) : this (fragment.requireActivity())

        private var renderScriptOptions: RenderScriptOptions? = null

        private var backgroundUpdateDelay: Long? = null

        fun withRenderScriptOptions(): Builder {
            if (renderScriptOptions == null)
                renderScriptOptions = RenderScriptOptions.withDefaultOptions(activity)

            return this
        }

        fun withRenderScriptOptions(renderScriptOptions: RenderScriptOptions): Builder {
            if (this.renderScriptOptions == null) this.renderScriptOptions = renderScriptOptions
            return this
        }

        fun withUpdateDelay(backgroundUpdateDelay: Long): Builder {
            if (this.backgroundUpdateDelay == null)
                this.backgroundUpdateDelay = backgroundUpdateDelay

            return this
        }

        fun build(): SmartBackgroundManager =
                SmartBackgroundManager(
                        activity,
                        renderScriptOptions,
                        backgroundUpdateDelay ?: BACKGROUND_UPDATE_DELAY
                )

        private companion object { const val BACKGROUND_UPDATE_DELAY: Long = 150 }

    }

}
