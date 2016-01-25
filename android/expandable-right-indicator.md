#ExpandableListView箭头右边显示方法
 * 1 首先 设置ExpandableListView的属性,将控件默认的左边箭头去掉：<br>
         elv.setGroupIndicator(null);//或在xml中的elv控件中设置：android:groupIndicator="@null"<br>
 * 2 在 自定义的继承自BaseExpandableListAdapter的adapter中重写方法：<br>
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_xxx, null);
            }
            ...
            //判断isExpanded就可以控制是按下还是关闭，同时更换图片 
            if(isExpanded){
                parentView.setBackgroundResource(R.drawable.arrow_down);
            }else{
                parentView.setBackgroundResource(R.drawable.arrow_up);
            } 
        
        return convertView; 
    }
