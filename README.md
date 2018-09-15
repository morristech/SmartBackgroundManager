# SmartBackgroundManager

SmartBackgroundManager allows to easily change the background of your Leanback app and apply blur to it using RxJava2. Glide and Picasso are also supported.



## Getting Started

**Add the JCenter repository to your app-module or project**:

```gradle
repositories {
    jcenter()
}
```

**And then add the dependencies**:

```gradle
implementation "uz.pentoligy.leanback:smartbackgroundmanager:1.0"
```

**If you want to use Glide or Picasso, add one of the following dependencies**:

```gradle
// for Glide
implementation "uz.pentoligy.leanback:smartbackgroundmanager-glide:1.0"
implementation "com.github.bumptech.glide:glide:$latestVersion"
kapt "com.github.bumptech.glide:compiler:$latestVersion"

// for Picasso
implementation "uz.pentoligy.leanback:smartbackgroundmanager-picasso:1.0"
implementation "com.squareup.picasso:picasso:$latestVersion"
```



## Library dependencies

```gradle
implementation "androidx.leanback:leanback:$latestVersion"
implementation "io.reactivex.rxjava2:rxandroid:$latestVersion"
implementation "io.reactivex.rxjava2:rxjava:$latestVersion"
```
Note, that library requires minimum SDK version 17 or higher.



## Usage

- **Creating an instance**:

  - <u>Simple without blurring</u>:
  ```kotlin
  val backgroundManager: SmartBackgroundManager =
         SmartBackgroundManager.Builder(requireActivity()).build()
  ```

  - <u>Simple with blurring</u>:
  ```kotlin
  val backgroundManager: SmartBackgroundManager =
         SmartBackgroundManager.Builder(this)
                 .withRenderScriptOptions()
                 .build()
  ```
  - <u>Advanced</u>:
  ```kotlin
  val options: RenderScriptOptions =
         RenderScriptOptions.Builder(requireContext())
                 .withBlurScale(0.5f)
                 .withBlurRadius(10f)
                 .build()
  
  val backgroundManager: SmartBackgroundManager =
          SmartBackgroundManager.Builder(this)
                 .withRenderScriptOptions(options)
                 .withUpdateDelay(200)
                 .build()
                 
  ```

- **Applying background**:

  - <u>From ImageCardView</u>:
  ```kotlin
  (itemViewHolder?.view as? ImageCardView)?.let {
       backgroundManager.fromImageCardView(it)
  }
  ```

  - <u>From Bitmap</u>:
  ```kotlin
  backgroundManager.fromBitmap(bitmap)
  ```
  - <u>From Drawable</u>:
  ```kotlin
  backgroundManager.fromDrawable(drawable)
  // or
  backgroundManager.fromDrawable(R.id.drawable)
  ```
  - <u>From color resource</u>:
  ```kotlin
  backgroundManager.fromColor(R.color.color)
  ```
- **If you want to use Glide or Picasso**:

  ```kotlin
  // for Glide:
  private val glideTarget: SmartBackgroundManagerGlideTarget by lazy {
        SmartBackgroundManagerGlideTarget(backgroundManager)
  }
  
  ...
  
  Glide.with(requireActivity()).asBitmap().load(url).into(glideTarget)
  ```
  ```kotlin
  // for Picasso:
  private val picassoTarget: SmartBackgroundManagerPicassoTarget by lazy {
        SmartBackgroundManagerPicassoTarget(backgroundManager)
  }
  
  ...
  
  val width: Int = backgroundManager.displayWidth
  val height: Int = backgroundManager.displayHeight
  
  Picasso.get().load(url)
          .resize(width, height).centerCrop().into(picassoTarget)
  ```

**To avoid memory leaks, you must set following in your Activity/Fragment**:

```kotlin
override fun onDestroy() {
     backgroundManager.onDestroy()
     super.onDestroy()
}
```



Please, check the [sample app](https://github.com/pentoligy/SmartBackgroundManager/blob/master/demo-app/src/main/java/uz/pentoligy/smartbackgroundmanagerdemo/) for more library usages.



## Screenshots
- **Without blurring**:

  ![Without blur](./screenshots/demo_without_blur.gif)



- **With blurring**:

  ![With blur](./screenshots/demo_with_blur.gif)



## Developed by
- Sanjar Khodjaev - [pentoligyone@gmail.com](mailto://pentoligyone@gmail.com)



License
-------

    Copyright 2018 Sanjar Khodjaev
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
