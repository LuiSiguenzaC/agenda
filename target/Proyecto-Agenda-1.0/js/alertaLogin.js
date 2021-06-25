$('#formulariLogin').submit(validar);

function validar(e) {
    e.preventDefault();

    const usuarioForm = $('#usuario').val(),
            passwordForm = $('#pass').val();

    $.post("LoginServlet", {
        usuario: usuarioForm,
        pass: passwordForm
    }, function (response) {
        if (response === "true") {
            //credenciales correctas, se redirige al usuario al index
            window.location = "index.jsp";
        } else {
            Swal.fire({
                html: '<p style="color: white; font-size: 18px;">Usuario o contraseña incorrectos</p>',
                position: 'top-start',
                showConfirmButton: false,
                backdrop: false,
                timer: 1500,
                timerProgressBar: true,
                background: '#FF0035'

            });
            
            document.querySelector("#pass").reset;
        }
    });
}

