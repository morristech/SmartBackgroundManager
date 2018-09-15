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

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

internal open class DisposableObserver<T>(private val observer: CompositeObserver) : Observer<T> {

    final override fun onSubscribe(d: Disposable) { observer.add(d) }

    override fun onNext(item: T) { /* ignored */ }

    final override fun onComplete() { /* ignored */ }

    override fun onError(e: Throwable) { /* ignored */ }

}