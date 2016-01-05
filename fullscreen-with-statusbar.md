#全屏模式下半透明显示系统状态栏
问题：当 Activity 以全屏模式运行时，如何允许 Android 系统状态栏在顶层出现，而不迫使 Activity 重新布局让出空间？
回答:
```
  private void hideStatusBar() {
    WindowManager.LayoutParams attrs = getWindow().getAttributes();
    attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
    getWindow().setAttributes(attrs);
  }    
  private void showStatusBar() {
    WindowManager.LayoutParams attrs = getWindow().getAttributes();
    attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
    getWindow().setAttributes(attrs);
  }
```
关键是，在做了该Activity的全屏设置的前提下，还要在onCreate()方法中加入如下语句：
```
  getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
  getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
```
