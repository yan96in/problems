获取LayoutInflater的三种写法
```
// 1.最原始的写法
LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//2.改善后的写法
LayoutInflater.from(this);
//3.最优写法
getLayoutInflater();
```
