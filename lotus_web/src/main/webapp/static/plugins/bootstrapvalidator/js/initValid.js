//git
//https://github.com/nghuuphuoc/bootstrapvalidator
//文档
//http://www.cnblogs.com/franson-2016/p/5842012.html
function validateFieldUtil(fields, $form){
    var options = {
        fields: fields
    };
    validateOptionUtil(options, $form)
}
function validateOptionUtil(options, $form){
    var defaults = {
        // live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        }
    };
    options = $.extend({}, defaults, options);
    $form.bootstrapValidator(options);
}
