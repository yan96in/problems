```
boolean iskey=false;       

 //锁屏
    private void unlockScreen() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
      if (keyguardManager.inKeyguardRestrictedInputMode()){
               iskey=true;
         Log.d("Lucky","锁屏");
      }else {
         iskey=false;
         Log.d("Lucky","未锁屏");
      }
        final KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("MyKeyguardLock");
        keyguardLock.disableKeyguard();
        Log.d("Lucky","锁屏界面");
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
                | PowerManager.ACQUIRE_CAUSES_WAKEUP
                | PowerManager.ON_AFTER_RELEASE, "MyWakeLock");

        wakeLock.acquire();
    }
   private void mylock() {
      boolean active=policyManager.isAdminActive(componentName);
      if(!active)
      {
//            activeManage();//获取权限
         policyManager.lockNow();//锁屏
      }
      if(active)
      {
         policyManager.lockNow();
      }

   }
```
