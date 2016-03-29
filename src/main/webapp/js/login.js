$(document).ready(function(){
    
    $("#login-button").click(function () {
        tryLoggin($("#user").val(), $("#passwd").val())
    });
    $("#passwd").on('keypress', function (event) {
        if(event.which === 13){

            //Disable textbox to prevent multiple submit
            //$(this).attr("disabled", "disabled");
            $("#login-button").click();
            //Do Stuff, submit, etc..
        }
    });

});
function tryLoggin(login,  passwd) {
    console.log(login + " : " + passwd);
    if (passwd != ""){
        $.ajax
        ({
            type: "GET",
            url: "../v1/admin",
            dataType: 'json',
            beforeSend : function(req) {
                var header = "Basic " + btoa(login + ":" + passwd);
                var nmbDays = 1;
                if ($("#keepLogged").is(":checked")){
                    nmbDays=7;
                }
                createCookie("auth",header, nmbDays);
                req.setRequestHeader("Authorization", readCookie("auth"));
            },
            success: function (data){
                document.location.href = "index.html";
            },
            error : function(jqXHR, textStatus, errorThrown) {
                //alert('error: ' + textStatus);
                if (jqXHR.status == 401){
                    notify("top", "center", "", "danger", "animated bounceIn", "animated bounceOut"," Connexion échoué : ","Mauvais mot de passe ou login !");
                } else if (jqXHR.status == 403){
                    notify("top", "center", "", "danger", "animated bounceIn", "animated bounceOut"," Connexion échoué : ","Utilisateur non autorisé");
                } else {
                    notify("top", "center", "", "danger", "animated bounceIn", "animated bounceOut"," Connexion échoué : ","Conexion error ("+jqXHR.status + ")");
                }
            }
        });
    }
}

