$('#formularioRecuperar').submit(validar);

function validar(e) {
    e.preventDefault();

    const usuarioForm = $('#usuario').val(),
            passwordForm = $('#pass').val();

    $.post("Recuperacion", {
        usuario: usuarioForm,
        pass: passwordForm
    }, function (response) {
        if (response === "true") {
            Swal.fire({
                title: "Contraseña Modificada",
                showConfirmButton: false,
                timer: 1500,
                timerProgressBar: true
            });
            //se redirige al apartado para loguearse
            setTimeout(function () {
                window.location = "Login.jsp";
            }, 1500);

        } else {
            Swal.fire({
                html: '<p style="color: white; font-size: 18px;">El usuario no existe</p>',
                position: 'top-start',
                showConfirmButton: false,
                backdrop: false,
                timer: 1500,
                timerProgressBar: true,
                background: '#FF0035'

            });
        }
    });

}



