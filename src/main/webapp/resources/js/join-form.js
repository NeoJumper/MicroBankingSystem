
document.addEventListener("DOMContentLoaded", function(){
    var myModal = new bootstrap.Modal(document.getElementById('certificateModal'));
    myModal.show();
});


$('#certificateBtn').click(certificateBtnClickHandler);

function certificateBtnClickHandler(){

    certificate();
}

function certificate() {
    let email = $("#email-input1").val() + "@" + $("#email-input2").val();
    let authenticationCode = $("#authentication-code-input1").val() + $("#authentication-code-input2").val();


    $.ajax({
        type: "PATCH",
        url: "/api/employees/certificate?authenticationCode=" + authenticationCode + "&email=" + email,
        success: function (data) {
            $('#certificateModal').modal('hide');
        },
        error: function(data){
            $("#loginFailMessageByEmail").css("display", "none");
            $("#loginFailMessageByCode").css("display", "none");


            if(data.status == 404){
                $("#loginFailMessageByEmail").css("display", "block");
            }
            else if(data.status == 400){
                $("#loginFailMessageByCode").css("display", "block");
            }
            console.log("실패");

        }
    });
}