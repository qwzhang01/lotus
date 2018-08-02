/**
 * 初始化分页列表
 *
 * @param $table
 * @param options
 */
function initPageList($table, options) {
    var defaults = {
        classes: 'table table-striped',
        cache: false,
        buttonsClass: 'default',
        undefinedText: ' ',
        striped: true,
        method: 'post',
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        dataType: 'json',
        dataField: 'list',
        totalField: 'totalRow',
        queryParamsType: 'limits',
        pagination: true,
        paginationLoop: false,
        sidePagination: 'server',
        totalRows: 0,
        pageNumber: 1,
        pageSize: 10,
        pageList: [10, 25, 50, 100],
        maintainSelected: false,
        clickToSelect: false,
        cardView: false,
        detailView: false,
        showFooter: false,
        rowStyle: function (row, index) {
            var classes = ["jp-onmouse"];
            return {
                classes: classes[0]
            };
        },
        rowAttributes: function (row, index) {
            return {
                onmouseover: 'showRowAction(this)',
                onmouseout: 'hideRowActoin(this)'
            };
        }
    };
    options = $.extend(defaults, options);// 需要传入url，搜索参数
    $table.bootstrapTable(options);
}

function showRowAction(dom) {
    $(dom).find(".row-actions").show();
}

function hideRowActoin(dom) {
    $(dom).find(".row-actions").hide();
}

/**
 * 列表序号方法（服务器端分页列表序号）
 */
function initIndex($table, value, item, index) {
    var ind = $table.bootstrapTable('getOptions').pageNumber;
    var size = $table.bootstrapTable('getOptions').pageSize;
    return (ind - 1) * size + index + 1;
}
/**
 * 添加行功能面板
 */
function rowAction(options) {
    var defaultOption = {
        strong: true,//是否使用strong标签包装
        href: '',//是否是链接
        value: '',//显示的值
        action: []
    };
    var defaultAction = {
        spanCss: "spam",
        aCss: "vim-s",
        value: "操作",
        href: "javascript:;",
        onFun: "",
        param: []
    };
    options = $.extend(defaultOption, options);// 需要传入url，搜索参数
    if (options.action.length > 0) {
        for (i = 0; i < options.action.length; i++) {
            options.action[i] = $.extend({}, defaultAction, options.action[i]);
        }
    }

    var html = '';
    //拼接显示内容
    if (options.strong) {
        html += '<strong>';
    }
    if ('' != options.href) {
        html += '<a href="' + options.href + '">' + options.value + '</a>';
    } else {
        html += options.value;
    }
    if (options.strong) {
        html += '</strong>';
    }
    //拼接功能面板
    if (options.action.length > 0) {
        html += '<div class="jp-flash-comment"><p class="row-actions jp-cancel-pad">';//开头
        //中间部分
        for (i = 0; i < options.action.length; i++) {
            var spanCss = options.action[i].spanCss;
            var aCss = options.action[i].aCss;
            var value = options.action[i].value;
            var href = options.action[i].href;
            var onFun = options.action[i].onFun;
            var param = options.action[i].param;

            html += ' <span class="' + spanCss + '">';
            if (i != 0) {
                html += '|';
            }
            html += ' <a class="' + aCss + '" href="' + href + '"';
            if (onFun != '') {
                html += 'onclick="' + onFun + '(';
                if (param && param.length > 0) {
                    for (j = 0; j < param.length; j++) {
                        if (j == 0) {
                            html += '\'' + param[j] + '\'';
                        } else {
                            html += ', \'' + param[j] + '\'';
                        }
                    }
                }
                html += ')"';
            }
            html += '>' + value + '</a>';
            html += '</span>';
        }
        html += '</p></div>';//结尾
    }
    return html;
}
/**
 * 默认列表分页序号（服务器端分页列表序号）
 *
 * @param value
 * @param item
 * @param index
 * @returns
 */
function indexNum(value, item, index) {
    return initIndex($listTabId, value, item, index);
}

/**
 * 将搜索条件与插件的排序、分页 参数合并
 *
 * @param $
 */
(function ($) {
    $.fn.serializeJson = function (obj) {
        var serializeObj = {};
        var array = this.serializeArray();
        var str = this.serialize();
        $(array).each(
            function () {
                if (serializeObj[this.name]) {
                    if ($.isArray(serializeObj[this.name])) {
                        serializeObj[this.name].push(this.value);
                    } else {
                        serializeObj[this.name] = [
                            serializeObj[this.name], this.value];
                    }
                } else {
                    serializeObj[this.name] = this.value;
                }
            });
        return $.extend(serializeObj, obj);
    };
})(jQuery);