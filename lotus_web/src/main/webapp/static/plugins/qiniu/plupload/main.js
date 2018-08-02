$(function() {
	if(uploader){
		uploader.stop();
	}
  var uploader = Qiniu.uploader({
    disable_statistics_report: false,
    runtimes: 'html5,flash,html4',
    browse_button: 'pickfiles',
    container: 'container',
    drop_element: 'container',
    max_file_size: '1000mb',
    flash_swf_url: '/js/qiniu/images/Moxie.swf',
    dragdrop: true,
    // multi_selection: true,
    chunk_size: '4mb',
    multi_selection: !(moxie.core.utils.Env.OS.toLowerCase() === "ios"),
    // multi_selection:false,
    uptoken_url: $('#uptoken_url').val(),
    domain: $('#domain').val(),
    get_new_uptoken: false,
    auto_start: true,
    log_level: 2,
    init: {
      'BeforeChunkUpload': function(up, file) {
        // console.log("before chunk upload:", file.name);
      },
      'FilesAdded': function(up, files) {
        $('table').show();
        $('#success').hide();
        plupload.each(files, function(file) {
          var progress = new FileProgress(file,
            'fsUploadProgress');
          progress.setStatus("等待...");
          progress.bindUploadCancel(up);
        });
      },
      'BeforeUpload': function(up, file) {
        // console.log("this is a beforeupload function from init");
        var progress = new FileProgress(file, 'fsUploadProgress');
        var chunk_size = plupload.parseSize(this.getOption(
          'chunk_size'));
        if (up.runtime === 'html5' && chunk_size) {
          progress.setChunkProgess(chunk_size);
        }
      },
      'UploadProgress': function(up, file) {
        var progress = new FileProgress(file, 'fsUploadProgress');
        var chunk_size = plupload.parseSize(this.getOption(
          'chunk_size'));
        progress.setProgress(file.percent + "%", file.speed,
          chunk_size);
      },
      'UploadComplete': function() {
        $('#success').show();
      },
      'FileUploaded': function(up, file, info) {
        var progress = new FileProgress(file, 'fsUploadProgress');
        // console.log("file",file);
        var rInfo =  eval('(' + info.response + ')');
        var h1 = '<input type="hidden" name="fileKeyAndName" value="' + rInfo.key + '|' + file.name + '" />'
        $("#form2").append(h1);
        //console.log("response:", info.response);
        progress.setComplete(up, info.response);
      },
      'Error': function(up, err, errTip) {
          $('table').show();
          var progress = new FileProgress(err.file, 'fsUploadProgress');
          progress.setError();
          progress.setStatus(errTip);
        },
        'Key': function(up, file) {
        	 var key = genName(file.name);
        	 return key
        }
    }
  });
  // uploader.init();
  uploader.bind('BeforeUpload', function() {
    // console.log("hello man, i am going to upload a file");
  });
  uploader.bind('FileUploaded', function() {
    // console.log('hello man,a file is uploaded');
  });
  $('#container').on(
    'dragenter',
    function(e) {
      e.preventDefault();
      $('#container').addClass('draging');
      e.stopPropagation();
    }
  ).on('drop', function(e) {
    e.preventDefault();
    $('#container').removeClass('draging');
    e.stopPropagation();
  }).on('dragleave', function(e) {
    e.preventDefault();
    $('#container').removeClass('draging');
    e.stopPropagation();
  }).on('dragover', function(e) {
    e.preventDefault();
    $('#container').addClass('draging');
    e.stopPropagation();
  });



  $('#show_code').on('click', function() {
    $('#myModal-code').modal();
    $('pre code').each(function(i, e) {
      hljs.highlightBlock(e);
    });
  });


  $('body').on('click', 'table button.btn', function() {
    $(this).parents('tr').next().toggle();
  });


  var getRotate = function(url) {
    if (!url) {
      return 0;
    }
    var arr = url.split('/');
    for (var i = 0, len = arr.length; i < len; i++) {
      if (arr[i] === 'rotate') {
        return parseInt(arr[i + 1], 10);
      }
    }
    return 0;
  };

  $('#myModal-img .modal-body-footer').find('a').on('click', function() {
    var img = $('#myModal-img').find('.modal-body img');
    var key = img.data('key');
    var oldUrl = img.attr('src');
    var originHeight = parseInt(img.data('h'), 10);
    var fopArr = [];
    var rotate = getRotate(oldUrl);
    if (!$(this).hasClass('no-disable-click')) {
      $(this).addClass('disabled').siblings().removeClass('disabled');
      if ($(this).data('imagemogr') !== 'no-rotate') {
        fopArr.push({
          'fop': 'imageMogr2',
          'auto-orient': true,
          'strip': true,
          'rotate': rotate,
          'format': 'png'
        });
      }
    } else {
      $(this).siblings().removeClass('disabled');
      var imageMogr = $(this).data('imagemogr');
      if (imageMogr === 'left') {
        rotate = rotate - 90 < 0 ? rotate + 270 : rotate - 90;
      } else if (imageMogr === 'right') {
        rotate = rotate + 90 > 360 ? rotate - 270 : rotate + 90;
      }
      fopArr.push({
        'fop': 'imageMogr2',
        'auto-orient': true,
        'strip': true,
        'rotate': rotate,
        'format': 'png'
      });
    }

    $('#myModal-img .modal-body-footer').find('a.disabled').each(
      function() {

        var watermark = $(this).data('watermark');
        var imageView = $(this).data('imageview');
        var imageMogr = $(this).data('imagemogr');

        if (watermark) {
          fopArr.push({
            fop: 'watermark',
            mode: 1,
            image: 'http://www.b1.qiniudn.com/images/logo-2.png',
            dissolve: 100,
            gravity: watermark,
            dx: 100,
            dy: 100
          });
        }

        if (imageView) {
          var height;
          switch (imageView) {
            case 'large':
              height = originHeight;
              break;
            case 'middle':
              height = originHeight * 0.5;
              break;
            case 'small':
              height = originHeight * 0.1;
              break;
            default:
              height = originHeight;
              break;
          }
          fopArr.push({
            fop: 'imageView2',
            mode: 3,
            h: parseInt(height, 10),
            q: 100,
            format: 'png'
          });
        }

        if (imageMogr === 'no-rotate') {
          fopArr.push({
            'fop': 'imageMogr2',
            'auto-orient': true,
            'strip': true,
            'rotate': 0,
            'format': 'png'
          });
        }
      });

    var newUrl = Qiniu.pipeline(fopArr, key);

    var newImg = new Image();
    img.attr('src', '/static/plugins/qiniu/images/loading.gif');
    newImg.onload = function() {
      img.attr('src', newUrl);
      img.parent('a').attr('href', newUrl);
    };
    newImg.src = newUrl;
    return false;
  });

});

/**
 * 生成文件key，取当前时间以及随机数字
 * 
 * @param fileName
 * @returns
 */
function genName(fileName){
	var fmt = 'yyyyMMddhhmmssSS';
	var d = new Date();
	var o = {
	        "M+": d.getMonth() + 1, // 月份
	        "d+": d.getDate(), // 日
	        "h+": d.getHours(), // 小时
	        "m+": d.getMinutes(), // 分
	        "s+": d.getSeconds(), // 秒
	        "q+": Math.floor((d.getMonth() + 3) / 3), // 季度
	        "S": d.getMilliseconds() // 毫秒
	    };
    if (/(y+)/.test(fmt)){ 
    	fmt = fmt.replace(RegExp.$1, (d.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o){
    	if (new RegExp("(" + k + ")").test(fmt)) {
    		fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    	}
    }
    var pref = fileName.substring(fileName.lastIndexOf('.'));
    var tmp = Math.ceil(Math.random()*10000);
    return fmt + tmp + pref;
}
