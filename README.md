# ShapedNavigationView

[![](https://jitpack.io/v/naseemali925/ShapedNavigationView.svg)](https://jitpack.io/#naseemali925/ShapedNavigationView)

This is an Android library to make Shaped NavigationViews inside your Android applications.

<img src="https://raw.githubusercontent.com/naseemali925/ShapedNavigationView/master/images/waves_indefinte.png" width="250">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://raw.githubusercontent.com/naseemali925/ShapedNavigationView/master/images/full_rounded.png" width="250">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://raw.githubusercontent.com/naseemali925/ShapedNavigationView/master/images/rounded_corner.png" width="250">

# Usage

```xml
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start">
    
    ...

    <softpro.naseemali.ShapedNavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        android:background="@android:color/white"
        app:itemBackground="@android:color/white"
        app:headerLayout="@layout/nav_header_main"
        app:drawerShape="waves_indefinite"
        android:layout_gravity="start"
        app:menu="@menu/activity_main_drawer" />

</android.support.v4.widget.DrawerLayout>
```

## With Java Code

```java
	navigationView = (ShapedNavigationView) findViewById(R.id.nav_view);
        navigationView.getSettings().setShapeType(ShapedViewSettings.WAVES);
```

# Download

Step 1. Add the JitPack repository to your Project build.gradle file

```grrovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency to your App build.gradle file

```groovy
	dependencies {
	        compile 'com.github.naseemali925:ShapedNavigationView:1.0.0'
	}
```

## Values Table

<table>
<tr><th>Attribute</th><th>Value</th><th>Shape Type</th></tr>
<tr><td>drawerShape</td><td>arcConvex</td><td><img src="https://raw.githubusercontent.com/naseemali925/ShapedNavigationView/master/images/convex.png" width="150"></td></tr>
<tr><td>drawerShape</td><td>arcConcave</td><td><img src="https://raw.githubusercontent.com/naseemali925/ShapedNavigationView/master/images/concave.png" width="150"></td></tr>
<tr><td>drawerShape</td><td>roundedRect</td><td><img src="https://raw.githubusercontent.com/naseemali925/ShapedNavigationView/master/images/rounded_corner.png" width="150"></td></tr>
<tr><td>drawerShape</td><td>waves</td><td><img src="https://raw.githubusercontent.com/naseemali925/ShapedNavigationView/master/images/waves.png" width="150"></td></tr>
<tr><td>drawerShape</td><td>bottom_round</td><td><img src="https://raw.githubusercontent.com/naseemali925/ShapedNavigationView/master/images/bottom_rounded.png" width="150"></td></tr>
<tr><td>drawerShape</td><td>full_round</td><td><img src="https://raw.githubusercontent.com/naseemali925/ShapedNavigationView/master/images/full_rounded.png" width="150"></td></tr>
<tr><td>drawerShape</td><td>waves_indefinite</td><td><img src="https://raw.githubusercontent.com/naseemali925/ShapedNavigationView/master/images/waves_indefinte.png" width="150"></td></tr>
</table>

## Sample App

You can check out the [sample app](https://github.com/naseemali925/ShapedNavigationView/tree/master/app)

## Acknowledgements

Thanks to [Roman Zavarnitsyn](https://github.com/rom4ek) for his [ArcNavigationView](https://github.com/rom4ek/ArcNavigationView) Project Idea.
Thank to [Hama Omer](https://github.com/hama-Omer) for the Wavy Look Idea.

# License

MIT License

Copyright (c) 2017 Naseem Ali

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the license conditions.
