/*自定义EditView控件*/
public class EditTextWithDelete extends EditText {
    Context context;
    Drawable deleteAllOn;//点击抬起时候的删除图标
    Drawable deleteAllDown;//点击按下时候的删除图标

    public EditTextWithDelete(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public EditTextWithDelete(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public EditTextWithDelete(Context context) {
        super(context);
        this.context = context;
        init();
    }


    private void init(){
        deleteAllOn = context.getResources().getDrawable(R.drawable.delete);
        deleteAllDown = context.getResources().getDrawable(R.drawable.delete_gray);
        addTextChangedListener(new TextWatcher(){//监听用户输入
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                setDraw();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setDraw(){//重绘
        if(length() > 1){
            setCompoundDrawablesWithIntrinsicBounds(null,null,deleteAllOn,null);
          //  setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)表示左、上、右、下
        }
        else{
            setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int) event.getRawX();
        int touchY = (int) event.getRawY();
        int touchX1 = (int) event.getX();
        int touchY1 = (int) event.getY();
        /*getX()是表示view相对于自身左上角的x坐标,
        而getRawX()是表示相对于屏幕左上角的x坐标值
        (注意:这个屏幕左上角是手机屏幕左上角,
        不管activity是否有titleBar或是否全屏幕)
         */
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);//用Rect将控件框起来
        rect.left = rect.right - 40;//将Rect的范围缩小到图片显示区域，只有点击落在改区域才会删除输入文字

        if(length()>1){//当有文字输入时在进行操作
            if(rect.contains(touchX, touchY)){



                setCompoundDrawablesWithIntrinsicBounds(null,null,deleteAllDown,null);
            }else{
                setCompoundDrawablesWithIntrinsicBounds(null,null,deleteAllOn,null);
            }

            if(event.getAction() == MotionEvent.ACTION_UP){
                if(rect.contains(touchX, touchY)){
                    setText("");//清除输入的字符串
                }
                setDraw();//重绘
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
