- web页面可编辑:document.body.contentEditable ="true"
- 只有两种选择的if else 语句也可以使用三目运算符来做
- [js链式调用](http://blog.csdn.net/CherishLyf/article/details/50517425)
- const定义的变量可以重新赋值,但不可重新定义

      const ctx;var ctx;// error!
- forEach用法:
 
      arr.forEach(function(value,index,array){
        //value=array[index]
      })
- h5 dom增加了3个选择器方法querySelectorAll()、querySelector()和matchesSelector()，极大的改善了性能。（也增加了一个不用jquery的重要理由）
- [HTML DOM add() 方法](http://www.w3school.com.cn/jsref/met_select_add.asp)
- [JS中的call()和apply()方法](http://uule.iteye.com/blog/1158829)
- [使用apply实现js方法的链式调用](https://www.cnblogs.com/youxin/p/3410185.html)
- [通过Promise实现异步调用](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/async_function)
  下面是我自己的一个实验：
      var a;
      function delay(time){
          new Promise(resolve=>{
              setTimeout(()=>{
                  if(a!==undefined){
                      console.log(a)
                      resolve('resolved');
                  }
                  else{
                      console.log('a is undefined');
                      delay(time);
                  }
              },time)
          })
      }
      async function asyncCall() {
          setTimeout(function(){
              a=100;
          },1000);
          delay(100);
      }

      asyncCall();
