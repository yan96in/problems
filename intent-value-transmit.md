###Activity之间通过意图传值
```
  Intent intent=new Intent(From.this,To.class);
  intent.putExtra("key","value");
  startActivityForResult(intent,0);
```
