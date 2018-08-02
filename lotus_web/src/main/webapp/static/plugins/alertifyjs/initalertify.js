function initAlertify(msg, isSuccess){
    var notification =
        alertify.notify(msg, isSuccess? 'success':'error', 5, function(){
        });
    return notification;
}