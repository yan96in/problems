if($){//
  var log = $.extend(
    function (msg) {//e.g. log("我想和你虚度时光")
        console.log(msg)
    }, {//e.g. log.success("我要找到你")
        success: function (msg) {
            msg ? console.log(msg) : console.log("success")
        }
    }, {//e.g. log.fail("没有你陪伴真的好孤单")
        fail: function (msg) {
            // msg ? console.error(msg) : console.error("fail")
        }
    })
}
