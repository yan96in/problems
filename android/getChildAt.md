###ListView中getChildAt(index)的使用注意事项
 * 1.原理 
在很多时候ListView列表数据不需要全部刷新，只需刷新有数据变化的那一条，这时可以用getChildAt(index)获取某个指定position的view，并对该view进行刷新。 
<br>
注意：在ListView中，使用getChildAt(index)的取值，只能是当前可见区域（列表可滚动）的子项！ <br>
即取值范围在 >= ListView.getFirstVisiblePosition() &&  <= ListView.getLastVisiblePosition(); <br>
1）所以如果想获取前部的将会出现返回Null值空指针问题； <br>
2）getChildCount跟getCount获取的值将会不一样（数量多时）； <br>
3 ）如果使用了getChildAt(index).findViewById(...)设置值的话，滚动列表时值就会改变了。 <br>
   需要使用getFirstVisiblePosition()获得第一个可见的位置，再用当前的position-它,再用getChildAt取值！即getChildAt(position - ListView。getFirstVisiblePosition()).findViewById(...)去设置值 <br>
 * 2.如果想更新某一行数据，需要配合ListView的滚动状态使用，一般不滚动时才加载更新数据 <br>
```
//全局变量，用来记录ScrollView的滚动状态，1表示开始滚动，2表示正在滚动，0表示停止滚动  
//伪代码 
//ListView设置 
  public int scrollStates; 
  class OnScrollListenerImpl implements OnScrollListener{ 
    @Override 
    public void onScrollStateChanged(AbsListView view, int scrollState) { 
    scrollStates = scrollState;  
  } 

  @Override 
  public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) { 
    int lastInScreen = firstVisibleItem + visibleItemCount; 
  } 
  listView.setOnScrollListener(new OnScrollListenerImpl()); 


  //Activity中 
  if(scrollStates==OnScrollListener.SCROLL_STATE_IDLE){ 

  //更新视图数据 
  } 
```
