/*
 * 方式1：xml中配置inputType。 常用于限制为 Date，time，number，Email，phone等常用的格式
 * 方式2：xml中配置digits。可以自定义限制的区间。
 * 方式3：java中使用setKeyListener，添加DigitsKeyListener。（方法2就是最终就是通过该方法实现）
 * 方法4：java中使用setFilters，添加InputFilter。可以在回调函数filter中自己写处理，最后返回处理过的CharSequence对象。
 * 
 * 方法：java中使用addTextChangedListener，添加 TextWatcher。不可行！！！ （具体见最下面注释）
 */
public class MainActivity extends Activity {

	private EditText et3, et4;
	private final static String ET3_DIGITS = "abcd";
	private final static String ET4_DIGITS = "wxyz";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et3 = (EditText) findViewById(R.id.et3);
		et4 = (EditText) findViewById(R.id.et4);

		//方式3：java中使用setKeyListener，添加DigitsKeyListener。
		et3.setKeyListener(DigitsKeyListener.getInstance(ET3_DIGITS));
		
		//方法4：java中使用setFilters，添加InputFilter。
		et4.setFilters(new InputFilter[] {new InputFilter() {
			
			@Override
			public CharSequence filter(CharSequence source, int start, int end,
					Spanned dest, int dstart, int dend) {			
				//source：你即将输入的字符序列
				Log.d("jie","source = "+source);
				
				//start：默认为0，  end：你即将输入的字符序列的长度
				Log.d("jie","start = "+ start);
				Log.d("jie","end = "+ end);
				
				//dest：当前EditText显示的内容
				Log.d("jie","dest = "+dest);
				
				//经测试dstart和dend 总是相等，都表示输入前光标所在位置
				Log.d("jie","dstart = "+dstart);
				Log.d("jie","dend = "+dend);
				
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < source.length(); i++) {
                    if (ET4_DIGITS.indexOf(source.charAt(i)) >= 0) {
                        sb.append(source.charAt(i));
                    }
                }
                return sb;
			}
		}});
		
		
		//使用TextWatcher，虽然能够检测到text 内容修改，但是不能进行重新赋值，因为你如果重新setText，又会重新触发该监听，会形成死循环，最终导致栈溢出。故该方法不可行
       /*et3.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < s.length(); i++) {
					if (ET3_DIGITS.indexOf(s.charAt(i)) >= 0) {
						sb.append(s.charAt(i));
					}
				}
				et3.setText(sb);
			}
		});*/
		
	}

}
