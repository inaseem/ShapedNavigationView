# ShapedNavigationView
This is an Android library to make Shaped NavigationViews inside your Android applications.

<img src="https://raw.githubusercontent.com/naseemali925/ShapedNavigationView/master/images/waves_indefinite.png" width="303"> <img src="https://raw.githubusercontent.com/naseemali925/ShapedNavigationView/master/images/bottom_round.png" width="303"> <img src="https://raw.githubusercontent.com/naseemali925/ShapedNavigationView/master/images/rounded_corner.png" width="303">

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

    <softpro.shapednavigationview.ShapedNavigationView
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
