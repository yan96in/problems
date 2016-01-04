代码截图
1,基于Android SDK的截屏方法
(1)主要就是利用SDK提供的View.getDrawingCache()方法。网上已经有很多的实例了。首先创建一个android project，然后进行Layout，画一个按键（res/layout/main.xml）：
```
<?xmlversion="1.0"encoding="utf-8"?>

<LinearLayoutxmlns:android="http://schemas.android.com/apk/res/android"

    android:orientation="vertical"

    android:layout_width="fill_parent"

    android:layout_height="fill_parent"

    >

<TextView

    android:layout_width="fill_parent"

    android:layout_height="wrap_content"

    android:text="@string/hello"

    />

<Button

  android:text="NiceButton"

  android:id="@+id/my_button"

  android:layout_width="fill_parent"

  android:layout_height="wrap_content"

  android:layout_alignParentBottom="true"></Button>

</LinearLayout>
HelloAndroid.java实现代码为：

packagecom.example.helloandroid;

 

importjava.io.FileOutputStream;

importjava.text.SimpleDateFormat;

importjava.util.Date;

importjava.util.Locale;

 

importandroid.app.Activity;

importandroid.graphics.Bitmap;

importandroid.os.Bundle;

importandroid.view.View;

importandroid.view.View.OnClickListener;

importandroid.widget.Button;

 

publicclassHelloAndroidextendsActivity
 {

 

  privateButton
 button;

 

  /**
 Called when the activity is first created. */

  @Override

  publicvoidonCreate(Bundle
 savedInstanceState) {

 

    super.onCreate(savedInstanceState);

    this.setContentView(R.layout.main);

    this.button
 = (Button) this.findViewById(R.id.my_button);

    this.button.setOnClickListener(newOnClickListener()
 {

 

      publicvoidonClick(View
 v) {

        SimpleDateFormat
 sdf = newSimpleDateFormat(

            "yyyy-MM-dd_HH-mm-ss",
 Locale.US);

        String
 fname = "/sdcard/"+
 sdf.format(newDate())
 + ".png";

        View
 view = v.getRootView();

        view.setDrawingCacheEnabled(true);

        view.buildDrawingCache();

        Bitmap
 bitmap = view.getDrawingCache();

        if(bitmap
 != null)
 {

          System.out.println("bitmap
 got!");

          try{

            FileOutputStream
 out = newFileOutputStream(fname);

            bitmap.compress(Bitmap.CompressFormat.PNG,100,
 out);

            System.out.println("file
 " 
+ fname + "output
 done.");

          }catch(Exception
 e) {

            e.printStackTrace();

          }

        }else{

          System.out.println("bitmap
 is NULL!");

        }

      }

 

    });

 

  }

}
```
这个代码会在按下app中按键的时候自动在手机的/sdcard/目录下生成一个时间戳命名的png截屏文件。
这种截屏有一个问题，就是只能截到一部分，比如电池指示部分就截不出来了。
(2)在APK中调用“adb shell screencap -pfilepath” 命令
该命令读取系统的framebuffer，需要获得系统权限：
（1）. 在AndroidManifest.xml文件中添加
<uses-permissionandroid:name="android.permission.READ_FRAME_BUFFER"/>
（2）. 修改APK为系统权限，将APK放到源码中编译， 修改Android.mk
 LOCAL_CERTIFICATE := platform

publicvoid takeScreenShot(){ 
    String mSavedPath = Environment.getExternalStorageDirectory()+File. separator + "screenshot.png" ; 
try {                     
           Runtime. getRuntime().exec("screencap -p " + mSavedPath); 
    } catch (Exception e) { 
           e.printStackTrace(); 
    } 

(3).利用系统的API，实现Screenshot,这部分代码是系统隐藏的，需要在源码下编译，
    1).修改Android.mk， 添加系统权限
          LOCAL_CERTIFICATE := platform
         2).修改AndroidManifest.xml 文件，添加
权限
<uses-permissionandroid:name="android.permission.READ_FRAME_BUFFER"/>
```
      public boolean takeScreenShot(String imagePath){
                     
                    
                     
             if(imagePath.equals("" )){
                      imagePath = Environment.getExternalStorageDirectory()+File. separator+"Screenshot.png" ;
             }
                     
          Bitmap mScreenBitmap;
          WindowManager mWindowManager;
          DisplayMetrics mDisplayMetrics;
          Display mDisplay;
                  
          mWindowManager = (WindowManager) mcontext.getSystemService(Context.WINDOW_SERVICE);
          mDisplay = mWindowManager.getDefaultDisplay();
          mDisplayMetrics = new DisplayMetrics();
          mDisplay.getRealMetrics(mDisplayMetrics);
                                 
          float[] dims = {mDisplayMetrics.widthPixels , mDisplayMetrics.heightPixels };
          mScreenBitmap = Surface. screenshot((int) dims[0], ( int) dims[1]);
                     
          if (mScreenBitmap == null) {  
                 return false ;
          }
                  
       try {
          FileOutputStream out = new FileOutputStream(imagePath);
          mScreenBitmap.compress(Bitmap.CompressFormat. PNG, 100, out);
             
        } catch (Exception e) {
                
                
          return false ;
        }       
                            
       return true ;
}
```
2 基于Android ddmlib进行截屏
[java] view plaincopy
public class ScreenShot {  
 private BufferedImage image = null;  
 /** 
  * @param args 
  */  
 public static void main(String[] args) {  
  // TODO Auto-generated method stub  
  AndroidDebugBridge.init(false); //  
  ScreenShot screenshot = new ScreenShot();  
  IDevice device = screenshot.getDevice();  
    
  for (int i = 0; i < 10; i++) {  
   Date date=new Date();  
   SimpleDateFormat df=new SimpleDateFormat("MM-dd-HH-mm-ss");   
   String nowTime = df.format(date);  
   screenshot.getScreenShot(device, "Robotium" + nowTime);  
   try {  
    Thread.sleep(1000);  
   } catch (InterruptedException e) {  
    // TODO Auto-generated catch block  
    e.printStackTrace();  
   }  
  }  
 }  
  
   
 public void getScreenShot(IDevice device,String filename) {  
  RawImage rawScreen = null;  
  try {  
   rawScreen = device.getScreenshot();  
  } catch (TimeoutException e) {  
   // TODO Auto-generated catch block  
   e.printStackTrace();  
  } catch (AdbCommandRejectedException e) {  
   // TODO Auto-generated catch block  
   e.printStackTrace();  
  } catch (IOException e) {  
   // TODO Auto-generated catch block  
   e.printStackTrace();  
  }  
  if (rawScreen != null) {  
   Boolean landscape = false;  
   int width2 = landscape ? rawScreen.height : rawScreen.width;  
   int height2 = landscape ? rawScreen.width : rawScreen.height;  
   if (image == null) {  
    image = new BufferedImage(width2, height2,  
      BufferedImage.TYPE_INT_RGB);  
   } else {  
    if (image.getHeight() != height2 || image.getWidth() != width2) {  
     image = new BufferedImage(width2, height2,  
       BufferedImage.TYPE_INT_RGB);  
    }  
   }  
   int index = 0;  
   int indexInc = rawScreen.bpp >> 3;  
   for (int y = 0; y < rawScreen.height; y++) {  
    for (int x = 0; x < rawScreen.width; x++, index += indexInc) {  
     int value = rawScreen.getARGB(index);  
     if (landscape)  
      image.setRGB(y, rawScreen.width - x - 1, value);  
     else  
      image.setRGB(x, y, value);  
    }  
   }  
   try {  
    ImageIO.write((RenderedImage) image, "PNG", new File("D:/"  
      + filename + ".jpg"));  
   } catch (IOException e) {  
    // TODO Auto-generated catch block  
    e.printStackTrace();  
   }  
  }  
 }  
  
 /** 
  * 获取得到device对象 
  * @return 
  */  
 private IDevice getDevice(){  
  IDevice device;  
  AndroidDebugBridge bridge = AndroidDebugBridge  
    .createBridge("adb", true);//如果代码有问题请查看API，修改此处的参数值试一下  
  waitDevicesList(bridge);  
  IDevice devices[] = bridge.getDevices();  
  device = devices[0];  
  return device;  
 }  
   
 /** 
  * 等待查找device 
  * @param bridge 
  */  
 private void waitDevicesList(AndroidDebugBridge bridge) {  
  int count = 0;  
  while (bridge.hasInitialDeviceList() == false) {  
   try {  
    Thread.sleep(500);   
    count++;  
   } catch (InterruptedException e) {  
   }  
   if (count > 240) {  
    System.err.print("等待获取设备超时");  
    break;  
   }  
  }  
 }  
3 Android本地编程（Native Programming）读取framebuffer

(1)命令行，框架的截屏功能是通过framebuffer来实现的，所以我们先来介绍一下framebuffer。

framebuffer介绍
帧缓冲（framebuffer）是Linux为显示设备提供的一个接口，把显存抽象后的一种设备，他允许上层应用程序在图形模式下直接对显示缓冲区进行 读写操作。这种操作是抽象的，统一的。用户不必关心物理显存的位置、换页机制等等具体细节。这些都是由Framebuffer设备驱动来完成的。
Linux FrameBuffer 本质上只是提供了对图形设备的硬件抽象，在开发者看来，FrameBuffer 是一块显示缓存，往显示缓存中写入特定格式的数据就意味着向屏幕输出内容。所以说FrameBuffer就是一块白板。例如对于初始化为16 位色的FrameBuffer 来说， FrameBuffer中的两个字节代表屏幕上一个点，从上到下，从左至右，屏幕位置与内存地址是顺序的线性关系。
帧缓存有个地址，是在内存里。我们通过不停的向frame buffer中写入数据， 显示控制器就自动的从frame buffer中取数据并显示出来。全部的图形都共享内存中同一个帧缓存。

Android截屏实现思路
Android系统是基于Linux内核的，所以也存在framebuffer这个设备，我们要实现截屏的话只要能获取到framebuffer中的数据，然后把数据转换成图片就可以了，android中的framebuffer数据是存放在 /dev/graphics/fb0 文件中的，所以我们只需要来获取这个文件的数据就可以得到当前屏幕的内容。
现在我们的测试代码运行时候是通过RC(remote controller)方式来运行被测应用的，那就需要在PC机上来访问模拟器或者真机上的framebuffer数据，这个的话可以通过android的ADB命令来实现。

具体实现


/***********************************************************************
  *
  *   ScreenShot.java
  ***********************************************************************/
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.internal.Base64Encoder;
import com.google.common.io.Closeables;
import com.google.common.io.LittleEndianDataInputStream;

/**
 */
public class ScreenShot {

    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {    
        try {
            //分辨率大小，后续可以通过代码来获取到当前的分辨率
            int xResolution = 320;
            int yResolution = 480;
            //执行adb命令，把framebuffer中内容保存到fb1文件中
             Runtime.getRuntime().exec("adb pull /dev/graphics/fb0 C:/fb1");
             //等待几秒保证framebuffer中的数据都被保存下来，如果没有保存完成进行读取操作会有IO异常
             Thread.sleep(15000);
             //读取文件中的数据
             InputStream in = (InputStream)new FileInputStream("C:/fb1");
             DataInput frameBuffer = new LittleEndianDataInputStream(in);
             
             BufferedImage screenImage = new BufferedImage(
                     xResolution, yResolution, BufferedImage.TYPE_INT_ARGB);
                 int[] oneLine = new int[xResolution];
                for (int y = 0; y < yResolution; y++) {
                    //从frameBuffer中计算出rgb值
                    convertToRgba32(frameBuffer, oneLine);
                    //把rgb值设置到image对象中
                    screenImage.setRGB(0, y, xResolution, 1, oneLine, 0, xResolution);
                }
                Closeables.closeQuietly(in);
                
                ByteArrayOutputStream rawPngStream = new ByteArrayOutputStream();
                try {
                      if (!ImageIO.write(screenImage, "png", rawPngStream)) {
                        throw new RuntimeException(
                            "This Java environment does not support converting to PNG.");
                      }
                    } catch (IOException exception) {
                      // This should never happen because rawPngStream is an in-memory stream.
                     System.out.println("IOException=" + exception);
                    }
                byte[] rawPngBytes = rawPngStream.toByteArray();
                String base64Png = new Base64Encoder().encode(rawPngBytes);
                
                File screenshot = OutputType.FILE.convertFromBase64Png(base64Png);
                System.out.println("screenshot==" + screenshot.toString());
                screenshot.renameTo(new File("C:\\screenshottemp.png"));
                
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e);
        }
    }
    
    
    public static void convertToRgba32(DataInput frameBuffer, int[] into) {
        try {
            for (int x = 0; x < into.length; x++) {
                try{
                int rgb = frameBuffer.readShort() & 0xffff;
                int red = rgb >> 11;
                red = (red << 3) | (red >> 2);
                int green = (rgb >> 5) & 63;
                green = (green << 2) | (green >> 4);
                int blue = rgb & 31;
                blue = (blue << 3) | (blue >> 2);
                into[x] = 0xff000000 | (red << 16) | (green << 8) | blue;
                }catch (EOFException e){
                    System.out.println("EOFException=" + e);
                }
              }
        } catch (IOException exception) {
            System.out.println("convertToRgba32Exception=" + exception);
      }
    }
    
}
（2）
[java] view plaincopy
首先是直接移植SystemUI的代码，实现截图效果，这部分的代码就不贴出来了，直接去下载代码吧， 关键的代码没有几句，最最主要的是：Surface.screenshot()，请看代码吧。  
  
[java]  
<SPAN style="FONT-SIZE: 16px">package org.winplus.ss;   
   
import java.io.File;   
import java.io.FileNotFoundException;   
import java.io.FileOutputStream;   
import java.io.IOException;   
import java.text.SimpleDateFormat;   
import java.util.Date;   
   
import android.app.Activity;   
import android.content.Context;   
import android.graphics.Bitmap;   
import android.graphics.Canvas;   
import android.graphics.Matrix;   
import android.os.Bundle;   
import android.util.DisplayMetrics;   
import android.util.Log;   
import android.view.Display;   
import android.view.Surface;   
import android.view.WindowManager;   
import android.os.SystemProperties;   
   
public class SimpleScreenshotActivity extends Activity {   
   
    private Display mDisplay;   
    private WindowManager mWindowManager;   
    private DisplayMetrics mDisplayMetrics;   
    private Bitmap mScreenBitmap;   
    private Matrix mDisplayMatrix;   
   
    @Override   
    public void onCreate(Bundle savedInstanceState) {   
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.main);   
   
        new Thread(new Runnable() {   
   
            @Override   
            public void run() {   
                takeScreenshot();   
   
            }   
        }).start();   
    }   
   
    private float getDegreesForRotation(int value) {   
        switch (value) {   
        case Surface.ROTATION_90:   
            return 360f - 90f;   
        case Surface.ROTATION_180:   
            return 360f - 180f;   
        case Surface.ROTATION_270:   
            return 360f - 270f;   
        }   
        return 0f;   
    }   
   
    private void takeScreenshot() {   
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);   
        mDisplay = mWindowManager.getDefaultDisplay();   
        mDisplayMetrics = new DisplayMetrics();   
        mDisplay.getRealMetrics(mDisplayMetrics);   
        mDisplayMatrix = new Matrix();   
        float[] dims = { mDisplayMetrics.widthPixels,   
                mDisplayMetrics.heightPixels };   
   
        int value = mDisplay.getRotation();   
        String hwRotation = SystemProperties.get("ro.sf.hwrotation", "0");   
        if (hwRotation.equals("270") || hwRotation.equals("90")) {   
            value = (value + 3) % 4;   
        }   
        float degrees = getDegreesForRotation(value);   
   
        boolean requiresRotation = (degrees > 0);   
        if (requiresRotation) {   
            // Get the dimensions of the device in its native orientation    
            mDisplayMatrix.reset();   
            mDisplayMatrix.preRotate(-degrees);   
            mDisplayMatrix.mapPoints(dims);   
   
            dims[0] = Math.abs(dims[0]);   
            dims[1] = Math.abs(dims[1]);   
        }   
   
        mScreenBitmap = Surface.screenshot((int) dims[0], (int) dims[1]);   
   
        if (requiresRotation) {   
            // Rotate the screenshot to the current orientation    
            Bitmap ss = Bitmap.createBitmap(mDisplayMetrics.widthPixels,   
                    mDisplayMetrics.heightPixels, Bitmap.Config.ARGB_8888);   
            Canvas c = new Canvas(ss);   
            c.translate(ss.getWidth() / 2, ss.getHeight() / 2);   
            c.rotate(degrees);   
            c.translate(-dims[0] / 2, -dims[1] / 2);   
            c.drawBitmap(mScreenBitmap, 0, 0, null);   
            c.setBitmap(null);   
            mScreenBitmap = ss;   
        }   
   
        // If we couldn't take the screenshot, notify the user    
        if (mScreenBitmap == null) {   
            return;   
        }   
   
        // Optimizations    
        mScreenBitmap.setHasAlpha(false);   
        mScreenBitmap.prepareToDraw();   
           
        try {   
            saveBitmap(mScreenBitmap);   
        } catch (IOException e) {   
            System.out.println(e.getMessage());   
        }   
    }   
   
    public void saveBitmap(Bitmap bitmap) throws IOException {   
        String imageDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")   
                .format(new Date(System.currentTimeMillis()));   
        File file = new File("/mnt/sdcard/Pictures/"+imageDate+".png");   
        if(!file.exists()){   
            file.createNewFile();   
        }   
        FileOutputStream out;   
        try {   
            out = new FileOutputStream(file);   
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 70, out)) {   
                out.flush();   
                out.close();   
            }   
        } catch (FileNotFoundException e) {   
            e.printStackTrace();   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
    }   
}   
</SPAN>   
  
package org.winplus.ss;  
  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
  
import android.app.Activity;  
import android.content.Context;  
import android.graphics.Bitmap;  
import android.graphics.Canvas;  
import android.graphics.Matrix;  
import android.os.Bundle;  
import android.util.DisplayMetrics;  
import android.util.Log;  
import android.view.Display;  
import android.view.Surface;  
import android.view.WindowManager;  
import android.os.SystemProperties;  
  
public class SimpleScreenshotActivity extends Activity {  
  
 private Display mDisplay;  
 private WindowManager mWindowManager;  
 private DisplayMetrics mDisplayMetrics;  
 private Bitmap mScreenBitmap;  
 private Matrix mDisplayMatrix;  
  
 @Override  
 public void onCreate(Bundle savedInstanceState) {  
  super.onCreate(savedInstanceState);  
  setContentView(R.layout.main);  
  
  new Thread(new Runnable() {  
  
   @Override  
   public void run() {  
    takeScreenshot();  
  
   }  
  }).start();  
 }  
  
 private float getDegreesForRotation(int value) {  
  switch (value) {  
  case Surface.ROTATION_90:  
   return 360f - 90f;  
  case Surface.ROTATION_180:  
   return 360f - 180f;  
  case Surface.ROTATION_270:  
   return 360f - 270f;  
  }  
  return 0f;  
 }  
  
 private void takeScreenshot() {  
  mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);  
  mDisplay = mWindowManager.getDefaultDisplay();  
  mDisplayMetrics = new DisplayMetrics();  
  mDisplay.getRealMetrics(mDisplayMetrics);  
  mDisplayMatrix = new Matrix();  
  float[] dims = { mDisplayMetrics.widthPixels,  
    mDisplayMetrics.heightPixels };  
  
  int value = mDisplay.getRotation();  
  String hwRotation = SystemProperties.get("ro.sf.hwrotation", "0");  
  if (hwRotation.equals("270") || hwRotation.equals("90")) {  
   value = (value + 3) % 4;  
  }  
  float degrees = getDegreesForRotation(value);  
  
  boolean requiresRotation = (degrees > 0);  
  if (requiresRotation) {  
   // Get the dimensions of the device in its native orientation  
   mDisplayMatrix.reset();  
   mDisplayMatrix.preRotate(-degrees);  
   mDisplayMatrix.mapPoints(dims);  
  
   dims[0] = Math.abs(dims[0]);  
   dims[1] = Math.abs(dims[1]);  
  }  
  
  mScreenBitmap = Surface.screenshot((int) dims[0], (int) dims[1]);  
  
  if (requiresRotation) {  
            // Rotate the screenshot to the current orientation  
            Bitmap ss = Bitmap.createBitmap(mDisplayMetrics.widthPixels,  
                    mDisplayMetrics.heightPixels, Bitmap.Config.ARGB_8888);  
            Canvas c = new Canvas(ss);  
            c.translate(ss.getWidth() / 2, ss.getHeight() / 2);  
            c.rotate(degrees);  
            c.translate(-dims[0] / 2, -dims[1] / 2);  
            c.drawBitmap(mScreenBitmap, 0, 0, null);  
            c.setBitmap(null);  
            mScreenBitmap = ss;  
        }  
  
        // If we couldn't take the screenshot, notify the user  
        if (mScreenBitmap == null) {  
            return;  
        }  
  
        // Optimizations  
        mScreenBitmap.setHasAlpha(false);  
        mScreenBitmap.prepareToDraw();  
         
  try {  
   saveBitmap(mScreenBitmap);  
  } catch (IOException e) {  
   System.out.println(e.getMessage());  
  }  
 }  
  
 public void saveBitmap(Bitmap bitmap) throws IOException {  
  String imageDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")  
    .format(new Date(System.currentTimeMillis()));  
  File file = new File("/mnt/sdcard/Pictures/"+imageDate+".png");  
  if(!file.exists()){  
   file.createNewFile();  
  }  
  FileOutputStream out;  
  try {  
   out = new FileOutputStream(file);  
   if (bitmap.compress(Bitmap.CompressFormat.PNG, 70, out)) {  
    out.flush();  
    out.close();  
   }  
  } catch (FileNotFoundException e) {  
   e.printStackTrace();  
  } catch (IOException e) {  
   e.printStackTrace();  
  }  
 }  
}  
  
PS：1、需要在AndroidManifest.xml中加入代码：android:sharedUserId="android.uid.system"  
  
         2、由于调用了@hide的API，所以编译得时候请使用makefile编译。或者通过在Eclipse中添加Jar文件通过编译。  
  
         3、此代码只在Android4.0中使用过，2.3的就没去做测试了。  


4 利用TakeScreenShotService截图
Android手机一般都自带有手机屏幕截图的功能：在手机任何界面（当然手机要是开机点亮状态），通过按组合键，屏幕闪一下，然后咔嚓一声，截图的照片会保存到当前手机的图库中，真是一个不错的功能！

以我手头的测试手机为例，是同时按电源键+音量下键来实现截屏，苹果手机则是电源键 + HOME键，小米手机是菜单键+音量下键，而HTC一般是按住电源键再按左下角的“主页”键。那么Android源码中使用组合键是如何实现屏幕截图功能呢？前段时间由于工作的原因仔细看了一下，这两天不忙，便把相关的知识点串联起来整理一下，分下面两部分简单分析下实现流程：

Android源码中对组合键的捕获。
Android源码中对按键的捕获位于文件PhoneWindowManager.java（alps\frameworks\base\policy\src\com\android\internal\policy\impl）中，这个类处理所有的键盘输入事件，其中函数interceptKeyBeforeQueueing（）会对常用的按键做特殊处理。以我手头的测试机为例，是同时按电源键和音量下键来截屏，那么在这个函数中我们会看到这么两段代码：

 1
 2
 3
 4
 5
 6
 7
 8
 9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
.......
 case KeyEvent.KEYCODE_VOLUME_DOWN:
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_MUTE: {
                if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
                    if (down) {
                        if (isScreenOn && !mVolumeDownKeyTriggered
                                && (event.getFlags() & KeyEvent.FLAG_FALLBACK) == 0) {
                            mVolumeDownKeyTriggered = true;
                            mVolumeDownKeyTime = event.getDownTime();
                            mVolumeDownKeyConsumedByScreenshotChord = false;
                            cancelPendingPowerKeyAction();
                            interceptScreenshotChord();
                        }
                    } else {
                        mVolumeDownKeyTriggered = false;
                        cancelPendingScreenshotChordAction();
                    }
......

            case KeyEvent.KEYCODE_POWER: {
                result &= ~ACTION_PASS_TO_USER;
                if (down) {
                    if (isScreenOn && !mPowerKeyTriggered
                            && (event.getFlags() & KeyEvent.FLAG_FALLBACK) == 0) {
                        mPowerKeyTriggered = true;
                        mPowerKeyTime = event.getDownTime();
                        interceptScreenshotChord();
                    }
......
可以看到正是在这里（响应Down事件）捕获是否按了音量下键和电源键的，而且两个地方都会进入函数interceptScreenshotChord()中，那么接下来看看这个函数干了什么工作：

 1
 2
 3
 4
 5
 6
 7
 8
 9
10
11
12
13
    private void interceptScreenshotChord() {
        if (mVolumeDownKeyTriggered && mPowerKeyTriggered && !mVolumeUpKeyTriggered) {
            final long now = SystemClock.uptimeMillis();
            if (now <= mVolumeDownKeyTime + SCREENSHOT_CHORD_DEBOUNCE_DELAY_MILLIS
                    && now <= mPowerKeyTime + SCREENSHOT_CHORD_DEBOUNCE_DELAY_MILLIS) {
                mVolumeDownKeyConsumedByScreenshotChord = true;
                cancelPendingPowerKeyAction();

                mHandler.postDelayed(mScreenshotChordLongPress,
                        ViewConfiguration.getGlobalActionKeyTimeout());
            }
        }
    }
在这个函数中，用两个布尔变量判断是否同时按了音量下键和电源键后，再计算两个按键响应Down事件之间的时间差不超过150毫秒，也就认为是同时按了这两个键后，算是真正的捕获到屏幕截屏的组合键。

附言：文件PhoneWindowManager.java类是拦截键盘消息的处理类，在此类中还有对home键、返回键等好多按键的处理。

Android源码中调用屏幕截图的接口。
捕获到组合键后，我们再看看android源码中是如何调用屏幕截图的函数接口。在上面的函数interceptScreenshotChord中我们看到用handler判断长按组合键500毫秒之后，会进入如下函数：

1
2
3
4
5
    private final Runnable mScreenshotChordLongPress = new Runnable() {
        public void run() {
            takeScreenshot();
        }
    };
在这里启动了一个线程来完成截屏的功能，接着看函数takeScreenshot():

 1
 2
 3
 4
 5
 6
 7
 8
 9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
private void takeScreenshot() {
        synchronized (mScreenshotLock) {
            if (mScreenshotConnection != null) {
                return;
            }
            ComponentName cn = new ComponentName("com.android.systemui",
                    "com.android.systemui.screenshot.TakeScreenshotService");
            Intent intent = new Intent();
            intent.setComponent(cn);
            ServiceConnection conn = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    synchronized (mScreenshotLock) {
                        if (mScreenshotConnection != this) {
                            return;
                        }
                        Messenger messenger = new Messenger(service);
                        Message msg = Message.obtain(null, 1);
                        final ServiceConnection myConn = this;
                        Handler h = new Handler(mHandler.getLooper()) {
                            @Override
                            public void handleMessage(Message msg) {
                                synchronized (mScreenshotLock) {
                                    if (mScreenshotConnection == myConn) {
                                        mContext.unbindService(mScreenshotConnection);
                                        mScreenshotConnection = null;
                                        mHandler.removeCallbacks(mScreenshotTimeout);
                                    }
                                }
                            }
                        };
                        msg.replyTo = new Messenger(h);
                        msg.arg1 = msg.arg2 = 0;
                        if (mStatusBar != null && mStatusBar.isVisibleLw())
                            msg.arg1 = 1;
                        if (mNavigationBar != null && mNavigationBar.isVisibleLw())
                            msg.arg2 = 1;
                        try {
                            messenger.send(msg);
                        } catch (RemoteException e) {
                        }
                    }
                }
                @Override
                public void onServiceDisconnected(ComponentName name) {}
            };
            if (mContext.bindService(intent, conn, Context.BIND_AUTO_CREATE)) {
                mScreenshotConnection = conn;
                mHandler.postDelayed(mScreenshotTimeout, 10000);
            }
        }
    }
可以看到这个函数使用AIDL绑定了service服务到"com.android.systemui.screenshot.TakeScreenshotService"，注意在service连接成功时，对message的msg.arg1和msg.arg2两个参数的赋值。其中在mScreenshotTimeout中对服务service做了超时处理。接着我们找到实现这个服务service的类TakeScreenshotService，看看其实现的流程：

 1
 2
 3
 4
 5
 6
 7
 8
 9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
public class TakeScreenshotService extends Service {
    private static final String TAG = "TakeScreenshotService";

    private static GlobalScreenshot mScreenshot;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    final Messenger callback = msg.replyTo;
                    if (mScreenshot == null) {
                        mScreenshot = new GlobalScreenshot(TakeScreenshotService.this);
                    }
                    mScreenshot.takeScreenshot(new Runnable() {
                        @Override public void run() {
                            Message reply = Message.obtain(null, 1);
                            try {
                                callback.send(reply);
                            } catch (RemoteException e) {
                            }
                        }
                    }, msg.arg1 > 0, msg.arg2 > 0);
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(mHandler).getBinder();
    }
}
在这个类中，我们主要看调用接口，用到了mScreenshot.takeScreenshot（）传递了三个参数，第一个是个runnable，第二和第三个是之前message传递的两个参数msg.arg1和msg.arg2。最后我们看看这个函数takeScreenshot()，位于文件GlobalScreenshot.java中（跟之前的函数重名但是文件路径不一样）：

 1
 2
 3
 4
 5
 6
 7
 8
 9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
 /**
     * Takes a screenshot of the current display and shows an animation.
     */
    void takeScreenshot(Runnable finisher, boolean statusBarVisible, boolean navBarVisible) {
        // We need to orient the screenshot correctly (and the Surface api seems to take screenshots
        // only in the natural orientation of the device :!)
        mDisplay.getRealMetrics(mDisplayMetrics);
        float[] dims = {mDisplayMetrics.widthPixels, mDisplayMetrics.heightPixels};
        float degrees = getDegreesForRotation(mDisplay.getRotation());
        boolean requiresRotation = (degrees > 0);
        if (requiresRotation) {
            // Get the dimensions of the device in its native orientation
            mDisplayMatrix.reset();
            mDisplayMatrix.preRotate(-degrees);
            mDisplayMatrix.mapPoints(dims);
            dims[0] = Math.abs(dims[0]);
            dims[1] = Math.abs(dims[1]);
        }

        // Take the screenshot
        mScreenBitmap = Surface.screenshot((int) dims[0], (int) dims[1]);
        if (mScreenBitmap == null) {
            notifyScreenshotError(mContext, mNotificationManager);
            finisher.run();
            return;
        }

        if (requiresRotation) {
            // Rotate the screenshot to the current orientation
            Bitmap ss = Bitmap.createBitmap(mDisplayMetrics.widthPixels,
                    mDisplayMetrics.heightPixels, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(ss);
            c.translate(ss.getWidth() / 2, ss.getHeight() / 2);
            c.rotate(degrees);
            c.translate(-dims[0] / 2, -dims[1] / 2);
            c.drawBitmap(mScreenBitmap, 0, 0, null);
            c.setBitmap(null);
            mScreenBitmap = ss;
        }

        // Optimizations
        mScreenBitmap.setHasAlpha(false);
        mScreenBitmap.prepareToDraw();

        // Start the post-screenshot animation
        startAnimation(finisher, mDisplayMetrics.widthPixels, mDisplayMetrics.heightPixels,
                statusBarVisible, navBarVisible);
    }
这段代码的注释比较详细，其实看到这里，我们算是真正看到截屏的操作了，具体的工作包括对屏幕大小、旋转角度的获取，然后调用Surface类的screenshot方法截屏保存到bitmap中，之后把这部分位图填充到一个画布上，最后再启动一个延迟的拍照动画效果。如果再往下探究screenshot方法，发现已经是一个native方法了：

1
2
3
4
5
6
7
    /**
     * Like {@link #screenshot(int, int, int, int)} but includes all
     * Surfaces in the screenshot.
     *
     * @hide
     */
    public static native Bitmap screenshot(int width, int height);
使用JNI技术调用底层的代码，如果再往下走，会发现映射这这个jni函数在文件android_view_Surface.cpp中，这个真的已经是底层c++语言了，统一调用的底层函数是：

 1
 2
 3
 4
 5
 6
 7
 8
 9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
static jobject doScreenshot(JNIEnv* env, jobject clazz, jint width, jint height,
        jint minLayer, jint maxLayer, bool allLayers)
{
    ScreenshotPixelRef* pixels = new ScreenshotPixelRef(NULL);
    if (pixels->update(width, height, minLayer, maxLayer, allLayers) != NO_ERROR) {
        delete pixels;
        return 0;
    }

    uint32_t w = pixels->getWidth();
    uint32_t h = pixels->getHeight();
    uint32_t s = pixels->getStride();
    uint32_t f = pixels->getFormat();
    ssize_t bpr = s * android::bytesPerPixel(f);

    SkBitmap* bitmap = new SkBitmap();
    bitmap->setConfig(convertPixelFormat(f), w, h, bpr);
    if (f == PIXEL_FORMAT_RGBX_8888) {
        bitmap->setIsOpaque(true);
    }

    if (w > 0 && h > 0) {
        bitmap->setPixelRef(pixels)->unref();
        bitmap->lockPixels();
    } else {
        // be safe with an empty bitmap.
        delete pixels;
        bitmap->setPixels(NULL);
    }

    return GraphicsJNI::createBitmap(env, bitmap, false, NULL);
}
由于对C++不熟，我这里就不敢多言了。其实到这里，算是对手机android源码中通过组合键屏幕截图的整个流程有个大体了解了，一般我们在改动中熟悉按键的捕获原理，并且清楚调用的截屏函数接口即可，如果有兴趣的，可以继续探究更深的底层是如何实现的。
