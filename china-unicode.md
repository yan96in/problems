从服务器返回json数组{"citylist":"\u9f99\u5ca9,\u4e09\u660e,...},先使用String.split(",")分割
后保存在数组中，然后保存在ArrayList<String>中，然后使用排序算法让其按拼音字母进行排序
import java.util.Locale;
...
void saveAndSort(String json){
//城市列表json串对应的实体类
CityFromJson ctj=JSON.parseObject(json, CityFromJson.class);
                String[] strings=ctj.getCitylist().split(",");
                ArrayList<String> list=new ArrayList();
                for (String s:strings) {
                    list.add(s);
                }
                //进行排序
                Collections.sort(list,new SortedString());
                
}      

static class SortedString implements Comparator<String>{

        Collator cmp = Collator.getInstance(java.util.Locale.CHINA);
        @Override
        public int compare(String a, String  b) {
            if (cmp.compare(a,b)>0){
                return 1;
            }else if (cmp.compare(a, b)<0){
                return -1;
            }
            return 0;
        }
    }
