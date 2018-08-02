function timer(obj, txt) {
    obj.text(txt);
}
function showTime() {
    var today = new Date();
    var weekday = new Array(7)
    weekday[0] = "星期一"
    weekday[1] = "星期二"
    weekday[2] = "星期三"
    weekday[3] = "星期四"
    weekday[4] = "星期五"
    weekday[5] = "星期六"
    weekday[6] = "星期日"
    var y = today.getFullYear() + "年";
    var month = today.getMonth() + 1 + "月";
    var td = today.getDate() + "日";
    var d = weekday[today.getDay() - 1];
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    timer($("#Y"), y);
    timer($("#MH"), month);
    timer($("#TD"), td);
    timer($("#D"), d);
    timer($("#H"), h);
    timer($("#M"), m);
    timer($("#S"), s);
}
//根据url获取文字类型
function getTaxonomy(url) {
    var obj = {};
    $.ajax({
        type: "post",
        url: url,
        dataType: "json",
        data: {
            date: new Date
        },
        async: false,
        success: function (data) {
            obj = $.extend(obj, data);
        }
    });
    return obj;
}
// 根据url获取文章列表
function getContent(url) {
    var obj = {};
    $.ajax({
        type: "post",
        url: url,
        dataType: "json",
        data: {
            date: new Date
        },
        async: false,
        success: function (data) {
            obj = $.extend(obj, data);
        }
    });
    return obj;
}

// 初始化
var def = "1";
function mover(object) {
    // 主菜单
    var mm = document.getElementById("m_" + object);
    mm.className = "m_li_a";
    // 初始主菜单隐藏效果
    if (def != 0) {
        var mdef = document.getElementById("m_" + def);
        mdef.className = "m_li";
    }
    // 子菜单
    var ss = document.getElementById("s_" + object);
    ss.style.display = "block";
    // 初始子菜单隐藏效果
    if (def != 0) {
        var sdef = document.getElementById("s_" + def);
        sdef.style.display = "none";
    }
}

function mout(object) {
    // 主菜单
    var mm = document.getElementById("m_" + object);
    mm.className = "m_li";
    // 初始主菜单
    if (def != 0) {
        var mdef = document.getElementById("m_" + def);
        mdef.className = "m_li";
    }
    // 子菜单
    var ss = document.getElementById("s_" + object);
    ss.style.display = "none";
    // 初始子菜单
    if (def != 0) {
        var sdef = document.getElementById("s_" + def);
        sdef.style.display = "block";
    }
}

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}