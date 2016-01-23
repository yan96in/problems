##用Collections.sort方法对list排序
###两种方法
第一种是list中的对象实现Comparable接口，如下：
```
/**
* 根据order对User排序
*/
public class User implements Comparable<User>{
    private String name;
    private Integer order;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getOrder() {
        return order;
    }
    public void setOrder(Integer order) {
        this.order = order;
    }
    @override
    public int compareTo(User arg0) {
        return this.getOrder().compareTo(arg0.getOrder());
    }
}
```
测试一下：
```
public class Test{

    public static void main(String[] args) {
        User user1 = new User();
        user1.setName("a");
        user1.setOrder(1);
        User user2 = new User();
        user2.setName("b");
        user2.setOrder(2);
        List<User> list = new ArrayList<User>();
        //此处add user2再add user1
        list.add(user2);
        list.add(user1);
        Collections.sort(list);
        for(User u : list){
            System.out.println(u.getName());
        }
    }
}
```
输出结果如下<br>
a<br>
b<br>
第二种方法是根据Collections.sort重载方法来实现，例如：
```
/**
* 根据order对User排序
*/
public class User { //此处无需实现Comparable接口
    private String name;
    private Integer order;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getOrder() {
        return order;
    }
    public void setOrder(Integer order) {
        this.order = order;
    }
}
```
主类中这样写即可：
```
public class Test{
    public static void main(String[] args) {
        User user1 = new User();
        user1.setName("a");
        user1.setOrder(1);
        User user2 = new User();
        user2.setName("b");
        user2.setOrder(2);
        List<User> list = new ArrayList<User>();
        list.add(user2);
        list.add(user1);
       
        Collections.sort(list,new Comparator<User>(){
            @override
            public int compare(User arg0, User arg1) {
                return arg0.getOrder().compareTo(arg1.getOrder());
            }
        });
        
        for(User u : list){
            System.out.println(u.getName());
        }
    }
}
```
输出结果如下<br>
a<br>
b<br>
前者代码结构简单，但是只能根据固定的属性排序，后者灵活，可以临时指定排序项，但是代码不够简洁
