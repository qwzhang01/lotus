function selectAll(list){
    var result = new Array();
    list = openFolder(list);
    for(i = 0; i < list.length; i++){
        if ('folder' != list[i].folder) {
            result.push(list[i]);
        }
    }
    return result;
}
/**
 * 递归将文件夹中所有的文件去出放在list中
 */
function openFolder(list){
    for(i = 0; i < list.length; i++){
        if ('folder' == list[i].folder) {
            var folederId = list[i].id;
            $.ajax({
                url : "/audio/list/" + folederId,
                data : {
                    "date" : new Date
                },
                type : 'post',
                async : false,
                dataType : 'json',
                success : function(data) {
                    list = list.concat(openFolder(data));
                }
            });
        }
    }
    return list;
}


/**
 * 生成列的工具
 * @type {{folderTitle, fileTitle, imgDom}}
 */
var attUtil = (function ($) {
    var defaults = {
        value: '',
        href: '',
        clickFn: ''
    };

    return {
        /**
         * 生成文件夹标题html片段
         * @param options
         * @returns {string}
         */
        folderTitle: function (options) {
            var setting = $.extend({}, defaults, options);
            return '<img style="display: block;white-space: nowrap; float: left;"'
                + ' src="/static/admin/image/folder_icon.png" />'
                + '<a style="padding-left:2%;" onclick="return ' + options.clickFn + ';" href="#">'
                + options.value + '</a>';
        },
        /**
         * 生成文件标题html片段
         * @param options
         * @returns {string}
         */
        fileTitle: function (options) {
            var setting = $.extend({}, defaults, options);
            return '<img style="display: block;white-space: nowrap; float: left;"'
                + ' src="/static/admin/image/file_icon.png" />'
                + '<a '+ (options.target? 'target="_blank"' : '')
                + ' style="padding-left:2%;" href="' + options.href + '">'
                + options.value + '</a>';
        },
        /**
         * 生成列表图片html片段
         * @param options
         * @returns {*}
         */
        imgDom: function (options) {
            var setting = $.extend({}, defaults, options);
            if (setting.value && '' != setting.value) {
                return '<img style="width: 50px; height: 50px;"'
                    + ' src="' + options.rootPath + '/' + options.value + '"/>';
            }
            return '';

        }
    };
})(jQuery);

/**
 * 导航工具
 * @param options
 * @constructor
 */
function NavUtil(options) {
    this.params = $.extend({}, options);
    /**
     * 生成导航
     * @param $table
     * @param folderId
     */
    this.initNav = function () {
        wrightNav(this.params.table, this.params.position, this.params.clickFn, this.params.folderId);
    }
    /**
     * 搜索时调用
     */
    this.searchNav = function () {
        var $dom = getNavDom(this.params.table, this.params.position);
        $dom.html('搜索文件名称');
    }
    /**
     * 刷新
     */
    this.refresh = function () {
        wrightNav(this.params.table, this.params.position, this.params.clickFn,this.params.folderId);
    }

    /**
     * 将html写入导航
     */
    function wrightNav($table, position, clickFn, folderId) {
        var fold = null;
        if (0 == folderId) {
            fold = folder.rootFolder();
        } else {
            fold = folder.getById(folderId);
        }
        var html = '';
        var pathArray = fold.fullPath.split('/');
        for (i = 0; i < pathArray.length; i++) {
            if ('' != pathArray[i]) {
                var pathObj = folder.getById(pathArray[i]);
                html += genHtml(clickFn, pathObj);
            }
        }
        html += genHtml(clickFn, fold);

        var $dom = getNavDom($table, position);
        $dom.html(html);
    }

    /**
     * 获取导航位置
     * @param $table
     * @param position
     */
    function getNavDom($table, position) {
        var navDom = $table.children('thead')
            .children('tr').children('th')
            .eq(position)
            .children('div').eq(0);
        return navDom;
    }

    /**
     * 生成导航html
     * @param fn
     * @param pathObj
     * @returns {string}
     */
    function genHtml(fn, pathObj) {
        var html = '<a href="#" onclick="return ' + fn + '(' + pathObj.id + ');">' + pathObj.name + '/</a>';
        return html;
    }
}

/**
 * 操作文件夹的工具类
 * @type {{getById, rootFolder, allFolder}}
 */
var folder = (function ($) {
    return {
        getById: function (folderId) {
            var tmp = {};
            $(allFolder).each(function (k, v) {
                if (folderId == v.id) {
                    tmp = v;
                }
            });
            return tmp;
        },
        rootFolder: function () {
            var tmp = {};
            $(allFolder).each(function (k, v) {
                if ('/' == v.fullPath) {
                    tmp = v;
                }
            });
            return tmp;
        },
        allFolder: function () {
            var result;
            $.ajax({
                type: "POST",
                url: "/folder/all",
                async: false,
                data: {
                    date: new Date()
                },
                dataType: "json",
                success: function (data) {
                    result = data;
                }
            });
            return result;
        }
    }
})(jQuery);