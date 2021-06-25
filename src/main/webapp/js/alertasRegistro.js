



$('#formularioRegistro').submit(mensajeRegistro);

function mensajeRegistro(e) {
    e.preventDefault();

    const nombreForm = $('#nombre').val(),
            apellidoForm = $('#apellido').val(),
            telefonoForm = $('#telefono').val(),
            direccionForm = $('#direccion').val(),
            usuarioForm = $('#usuario').val(),
            passForm = $('#pass').val();

    $.post("RegistroServlet", {
        nombre: nombreForm,
        apellido: apellidoForm,
        telefono: telefonoForm,
        direccion: direccionForm,
        usuario: usuarioForm,
        pass: passForm
    }, function (response) {
        if (response === "true") {
            //se insertó correctamente a la base de datos
            Swal.fire({
                title: "Registro exitoso :)",
                showConfirmButton: false,
                timer: 1500,
                timerProgressBar: true
            });
            //se redirige al apartado para loguearse
            setTimeout(function () {
                window.location = "Login.jsp";
            }, 1500);
        } else {
            //ocurrió un error en la base de datos
            Swal.fire({
                title: "Ocurrió un Error :(",
                text: "Intentalo nuevamente"
            });
        }
    });
}

