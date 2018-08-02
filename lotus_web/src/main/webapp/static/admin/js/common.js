function fix() {
    var neg = $('.main-header').outerHeight()
        + $('.main-footer').outerHeight();
    var window_height = $(window).height();
    var sidebar_height = $(".sidebar").height();

    if (window_height >= sidebar_height + neg) {
        $(".content-wrapper, .right-side").css('min-height',
            window_height - neg);
    } else {
        $(".content-wrapper, .right-side")
            .css('min-height', sidebar_height);
    }
}

function initUrls() {
    $(".treeview-menu").each(function () {
        var pid = $(this).parent().attr("id");
        if (typeof (pid) == 'undefined' || null == pid) {
            return;
        }
        $(this).find('a').each(function () {
            var cid = $(this).parent().attr("id");
            var href = $(this).attr("href");
            if (href.indexOf("?") == -1) {
                href = href + "?p=" + pid;
            } else {
                href = href + "&p=" + pid;
            }
            if ("undefined" != typeof (cid) && "" != cid) {
                href += "&c=" + cid;
            }
            $(this).attr("href", href);
        });
    });
}

function initToast() {
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "newestOnTop": false,
        "progressBar": true,
        "positionClass": "toast-top-center",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "2000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }

}

function checkAll(checkbox) {
    var items = document.getElementsByName("dataItem");
    for (var i = 0; i < items.length; i++) {
        items[i].checked = checkbox.checked;
    }
}

/**
 * 千分位格式方法
 */
function toThousandFormat(num) {
    return '￥'
        + (num.toFixed(2) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g,
            '$&,');
}

jQuery.jp = {
    alert: function () {
        window.alert("test");
    },

    submit: function (formId, resultFunc) {
        formId = formId || "form";
        resultFunc = resultFunc || function () {
                toastr.success(data.message, '操作成功');
            }

        $(formId).ajaxSubmit({
            type: "post",
            dataType: "json",
            success: function (data) {
                resultFunc();
            },
            error: function () {
                toastr.success('信息提交错误', '错误');
            }
        });
    },

    ajax: function () {

    }
}

