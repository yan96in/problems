Handler在Fragment中的用法
*1
```
Message message;
View view;
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_xxx, container, false);
        final MyHandler handler = new MyHandler(this);
        //在新线程中发送消息
        new Thread() {
            @Override
            public void run() {
            //使用线程中的looper管理消息
                Looper.prepare();
                message = handler.obtainMessage();
                message.what = 100;
                message.obj = "这里是要传递的数据";
                handler.sendMessage(message);
                Looper.loop();
            }
        }.start();
        //这里要返回view
        return view;
    }
```
```
static class MyHandler extends Handler{
          //弱引用，保证了不会常驻内存
          WeakReference<XXXFragment> xxx;
          TextView tv;
        public MyHandler(WeatherFragment frag) {
            xxx = new WeakReference<>(frag);
        }
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100&&xxx!=null) {
              tv= (TextView) xxx.get().view.findViewById(R.id.tv);
              tv.setText(msg.obj.toString());
            }
        }
    }
```
*2还有一种是用Thread（ThreadPoolExecutor）+Runnable+Handler 还没成功
*3重写Fragment的onDestroy（）方法，
if（handler!=null){handler.onDestroy();}
[一个处理库](https://github.com/badoo/android-weak-handler)
