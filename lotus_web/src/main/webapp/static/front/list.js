$(function () {
    // 专题部分列表加载
    var data = getContent('/tycl/content/list/73/4');
    loadPicContentList($("#zhuantiList"), data);
});
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
