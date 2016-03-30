/**
 * Created by beaussan on 28/03/16.
 */

$(document).ready(function(){
    console.log(window.location.pathname);
    if (window.location.pathname.startsWith("/admin/")){
        if (window.location.pathname != "/admin/login.html"){
            if (readCookie("auth") == ""){
                document.location.href = "login.html";
            } else {
                $.ajax
                ({
                    type: "GET",
                    url: "../v1/admin",
                    dataType: 'json',
                    beforeSend : function(req) {
                        req.setRequestHeader("Authorization", readCookie("auth"));
                    },
                    success: function (data){
                    },
                    error : function(jqXHR, textStatus, errorThrown) {
                        document.location.href = "login.html";
                    }
                });
            }
        }
    } else {
        if (window.location.pathname != "/login.html"){
            if (readCookie("auth") == ""){
                document.location.href = "login.html";
            } else {
                $.ajax
                ({
                    type: "GET",
                    url: "../v1/me",
                    dataType: 'json',
                    beforeSend : function(req) {
                        req.setRequestHeader("Authorization", readCookie("auth"));
                    },
                    success: function (data){
                    },
                    error : function(jqXHR, textStatus, errorThrown) {
                        document.location.href = "login.html";
                    }
                });
            }
        }
    }



});

function createCookie(name,value,days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        var expires = "; expires="+date.toGMTString();
    }
    else var expires = "";
    document.cookie = name+"="+value+expires+"; path=/";
}

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

function eraseCookie(name) {
    createCookie(name,"",-1);
}


function notify(from, align, icon, type, animIn, animOut, title, message){
    $.growl({
        icon: icon,
        title: title,
        message: message,
        url: ''
    },{
        element: 'body',
        type: type,
        allow_dismiss: true,
        placement: {
            from: from,
            align: align
        },
        offset: {
            x: 20,
            y: 85
        },
        spacing: 10,
        z_index: 1031,
        delay: 2500,
        timer: 1000,
        url_target: '_blank',
        mouse_over: false,
        animate: {
            enter: animIn,
            exit: animOut
        },
        icon_type: 'class',
        template: '<div data-growl="container" class="alert" role="alert">' +
        '<button type="button" class="close" data-growl="dismiss">' +
        '<span aria-hidden="true">&times;</span>' +
        '<span class="sr-only">Close</span>' +
        '</button>' +
        '<span data-growl="icon"></span>' +
        '<span data-growl="title"></span>' +
        '<span data-growl="message"></span>' +
        '<a href="#" data-growl="url"></a>' +
        '</div>'
    });
}
