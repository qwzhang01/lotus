var i = 1;
$(function () {
    // 通知公告区域文章列表加载
    var data = getContent('/tycl/content/list/40/5');
    loadContentList($("#leftlist"), data);
    // 专题部分列表加载
    data = getContent('/tycl/content/list/73/4');
    loadPicContentList($("#zhuantiList"), data);
    // 工作动态
    data = getContent('/tycl/content/list/46/5');
    loadContentBeauList($("#gzdtlist"), data);
    // 图片幻灯片区域
    data = getContent('/tycl/content/slide-toggle');
    genPicAndBut(data);
    if (data.errorCode == 0) {
        var max = data.data.length;
        setInterval(function () {
            change(i);
            if (i == max) {
                i = 1;
            } else {
                i++;
            }
        }, 1000);
    }
    // 基层残联
    data = getContent('/tycl/content/list/47/5');
    loadContentBeauList($("#jccllist"), data);
    // 媒体报道
    data = getContent('/tycl/content/list/49/5');
    loadContentList($("#mtbdlist"), data);
    // 他山之石
    data = getContent('/tycl/content/list/50/5');
    loadContentBeauList($("#tszslist"), data);
    // 领导讲话
    data = getContent('/tycl/content/list/41/5');
    loadContentBeauList($("#ldjhlist"), data);
    // 业务工作
    var id = $("#businessTable").attr("data");
    data = getTaxonomy("/tycl/content/taxonomies/" + id);
    genBusinessNav($("#businessTable"), data);
});

// 首页目录tab效果
function appendLi(id) {
    var data = getContent('/tycl/content/list/' + id + '/5');
    $("#businessUl").empty();
    loadContentBeauList($("#businessUl"), data);
    //html = '<li><a style="padding-left: 608px; font-size: 12px;" href="ywgz.aspx?mid=康复">更多>></a></li>';
}
// 加载业务教育目录
function genBusinessNav(obj, data) {
    var html = '';
    if (data.errorCode == 0) {
        $.each(data.data, function (k, v) {
            if (0 == k) {
                html += '<td class="aac1" align="center">';
                appendLi(v.id);
            } else {
                html += '<td class="aac" align="center">';
            }
            html += '<span onMouseMove="JavaScript:appendLi(' + v.id + ')" style="cursor: hand;">';
            html += '<a href="/tycl/search/' + v.parentId + '/' + v.id + '">' + v.title + '</a>';
            html += '</span></td>';

        });

    } else {
        html = '获取失败';
    }
    $(obj).children("tbody").children("tr").append(html);
}
// 加载图片文字列表
function loadPicContentList(obj, data) {
    var html = '';
    if (data.errorCode == 0) {
        $.each(data.data, function (k, v) {
            html += '<a href="' + v.linkTo + '">';
            html += '<img style="margin-top:7px;" alt="' + v.title + '" src="' + v.thumbnail + '" height="58" width="210" border="0">';
            html += '</a>';
        });

    } else {
        html = '获取失败';
    }
    $(obj).append(html);
}
// 加载文字列表，待日期
function loadContentBeauList(obj, data) {
    var html = '';
    if (data.errorCode == 0) {
        $.each(data.data, function (k, v) {
            html += '<li><a href="/tycl/content/'
                + v.id + '"><b>· </b>'
                + v.title + '</a><span>'
                + new Date(v.created).Format("MM-dd") + '</span></li>';
        });

    } else {
        html = '获取失败';
    }
    $(obj).append(html);
}
// 加载文章列表
function loadContentList(obj, data) {
    var html = '';
    if (data.errorCode == 0) {
        $.each(data.data, function (k, v) {
            html += '<li><a href="/tycl/content/' + v.id + '"><b>· </b>' + v.title + '</a></li>';
        });

    } else {
        html = '获取失败';
    }
    $(obj).append(html);
}
// 根据返回的数据 生成图片、按钮
function genPicAndBut(obj) {
    if (obj.errorCode == 0) {
        var picHtml = genPic(obj.data);
        var butHtml = genBut(obj.data.length);
        document.getElementById("NewsPic").innerHTML = picHtml + butHtml;
        genTitle(obj.data[0].title);
    } else {
        genTitle(obj.message);
    }
}
// 生成圖片區域
function genPic(list) {
    var picHtml = '';
    $.each(list, function (k, v) {
        picHtml += '<a title="' + v.title + '"';
        if (0 == k) {
            picHtml += 'style="display: block; visibility: visible"';
        } else {
            picHtml += 'style="display: none; visibility:hidden"';
        }
        picHtml += 'href="/tycl/content/' + v.id + '" target="_blank">';
        picHtml += '<img class="Picture" alt="' + v.title + '"';
        picHtml += 'src="' + v.thumbnail + '"';
        picHtml += 'border="0" width="270" height="170"></a>';
    });
    return picHtml;
}
// 生成图片序号按钮
function genBut(num) {
    var butHtml = '<div class="Nav">';
    for (var i = num; i > 0; i--) {
        if (1 == i) {
            butHtml += '<span class="Cur">';
        } else {
            butHtml += '<span>';
        }
        butHtml += '<a href="#" onclick="return taggle(' + i + ');">' + i
            + '</a>';
        butHtml += '</span>';
    }
    butHtml += '</div>';
    return butHtml;
}
// 生成文字描述
function genTitle(msg) {
    var titleArea = document.getElementById("NewsPicTxt");
    titleArea.innerHTML = msg;
}
// 图片切换
function taggle(num) {
    i = num;
    change(num);
    return false;
}
// 维护切换图片时的各种状态
function change(num) {
    var pic = $('#NewsPic').children('a');
    var but = $('#NewsPic').children('.Nav').children('span');
    $.each(pic, function (k, p) {
        $(p).css("display", "none");
        $(p).css("visibility", "hidden");
    });
    $(pic[num - 1]).css("display", "block");
    $(pic[num - 1]).css("visibility", "visible");
    $('#NewsPicTxt').text($(pic[num - 1]).attr('title'));
    $.each(but, function (k, p) {
        if (num == $(p).children().text()) {
            $(p).addClass("Cur");
        } else {
            $(p).removeClass("Cur");
        }
    });
}
