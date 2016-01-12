###Activity之间通过意图传值
FirstActivity.class
```
private void method(){
  Intent intent=new Intent(From.this,To.class);
  intent.putExtra("key","value");
  startActivityForResult(intent,0);
  }
  ...//if need to handle msg sent back from SecondActivity, rewrite the method: onAcitivityResult
  @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                value=data.getBooleanExtra("key","value");
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode,resultCode, data);
    }
```
SecondActivity.class
```
Intent intent=getIntent();
value =intent.getExtra().getString("key");
...//do sth. and then:
intent.putExtra("key","newValue");
setResult(0, intent);
finish();
```
