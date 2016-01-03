重写onTick和onFinsh这两个方法，onFinish()中的代码是计时器结束的时候要做的事情；<br>
onTick(Long m)中的代码是你倒计时开始时要做的事情，参数m是直到完成的时间
```
new CountdownTimer(30000, 1000) {  
    public void onTick(long millisUntilFinished) {  
        mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);  
    }  
    public void onFinish() {  
        mTextField.setText("done!");  
    }  
 }.start();  
```
