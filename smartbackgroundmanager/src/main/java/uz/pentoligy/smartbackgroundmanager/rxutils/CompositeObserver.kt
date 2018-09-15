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

package uz.pentoligy.smartbackgroundmanager.rxutils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

internal class CompositeObserver : Disposable, DisposableContainer {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val size: Int get() = compositeDisposable.size()

    override fun dispose() = compositeDisposable.dispose()

    override fun isDisposed(): Boolean = compositeDisposable.isDisposed

    override fun add(d: Disposable): Boolean = compositeDisposable.add(d)

    override fun remove(d: Disposable): Boolean = compositeDisposable.remove(d)

    override fun delete(d: Disposable): Boolean = compositeDisposable.delete(d)

    fun clear() = compositeDisposable.clear()
}