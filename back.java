/*按两次回退键退出程序。
通过重写onKeyDown方法处理
*/

//解决方案1
private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isEixt = false;
        }
    };
    
 @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //如果isExit标记为false，提示用户再次按键
            if (!isEixt) {
                isEixt=true;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
                //如果用户在2秒内没有再次按返回键，就发送消息标记用户为不退出状态
                handler.sendEmptyMessageDelayed(0, 2000);
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }
    
    //解决方案2
  private long mExitTime;
  @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) < 2000) {
                System.exit(0);
            } else {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
                mExitTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
