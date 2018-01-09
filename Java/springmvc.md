- springMVC如何传两个以上的model
- controller之间,同一controller的方法之间跳转:

1.我在后台一个controller跳转到另一个controller，为什么有这种需求呢，是这样的。我有一个列表页面，然后我会进行新增操作，新增在后台完成之后我要跳转到列表页面，不需要传递参数，列表页面默认查询所有的。

    方式一：使用ModelAndView
    return new ModelAndView("redirect:/toList");
    这样可以重定向到toList这个方法
    方式二：返回String
    return "redirect:/ toList ";
    其它方式：其它方式还有很多，这里不再做介绍了，比如说response等等。这是不带参数的重定向。

2.第二种情况，列表页面有查询条件，跳转后我的查询条件不能丢掉，这样就需要带参数的了，带参数可以拼接url

方式一：自己手动拼接url
new ModelAndView("redirect:/toList？param1="+value1+"¶m2="+value2);
这样有个弊端，就是传中文可能会有乱码问题。
方式二：用RedirectAttributes，这个是发现的一个比较好用的一个类
这里用它的addAttribute方法，这个实际上重定向过去以后你看url，是它自动给你拼了你的url。
使用方法：
attr.addAttribute("param", value);
return "redirect:/namespace/toController";
这样在toController这个方法中就可以通过获得参数的方式获得这个参数，再传递到页面。过去的url还是和方式一一样的。

方式三：带参数不拼接url页面也能拿到值（重点是这个）
一般我估计重定向到都想用这种方式：

    @RequestMapping("/save")
    public String save(@ModelAttribute("form") Bean form,RedirectAttributes attr) throws Exception {
        String code =  service.save(form);
        if(code.equals("000")){
            attr.addAttribute("name", form.getName()); 
            attr.addAttribute("success", "添加成功!");
            return "redirect:/index";
        }else{
            attr.addAttribute("projectName", form.getProjectName()); 
            attr.addAttribute("enviroment", form.getEnviroment()); 
            attr.addFlashAttribute("msg", "添加出错!错误码为："+rsp.getCode().getCode()+",错误为："+rsp.getCode().getName());
            return "redirect:/maintenance/toAddConfigCenter";
        }
    }

    @RequestMapping("/index")
    public String save(@ModelAttribute("form") Bean form,RedirectAttributes attr) throws Exception {
        return "redirect:/main/list";
    }

页面取值不用我说了吧，直接用el表达式就能获得到，这里的原理是放到session中，session在跳到页面后马上移除对象。所以你刷新一下后这个值就会丢掉。

3. 总结

最底层还是两种跳转，只是spring又进行了封装而已
