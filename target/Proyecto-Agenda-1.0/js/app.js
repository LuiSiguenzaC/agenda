$('#nuevaNota').submit(callback); //llamado al formualrio
const botonEliminar = document.querySelector('#tabla-notas tbody');

botonEliminar.addEventListener('click', callback2);

//llamando funciones simultaneas
function callback(e) {
    e.preventDefault();
    agregarNota();
    setTimeout(function(){
        contarNotas("agregar");
    },1000);
}

function callback2(e) {
    e.preventDefault();
    eliminarNota(e);
    setTimeout(function(){
        contarNotas("eliminar");
    },1000);
}

//petición asíncrona para agregar una nueva nota
function agregarNota() {
    const descripcionForm = $('#descripcion').val(),
            diaForm = $('#dia').val(),
            mesForm = $('#mes').val(),
            agnoForm = $('#agno').val();

    if (descripcionForm === "" || diaForm === "" || mesForm === "" || agnoForm === "") {
        alert("Debes especificar todos los campos");
    } else {
        //Se realiza el llamado a Ajax
        $.post("AgendaServlet", {
            descripcion: descripcionForm,
            dia: diaForm,
            mes: mesForm,
            agno: agnoForm
        }, function (response) {
            $('#notas').html(response);
            document.querySelector('#nuevaNota').reset();
        });
    }

}

//petición asíncrona para contar las notas en la base de datos
function contarNotas(text) {
    //se realiza el llamado a Ajax
    $.post("ContarNotas", {
        text: text
    }, function (response) {
        $('#contador').html(response);
    });

}

//petición asíncrona para eliminar una nota de la base de datos
function eliminarNota(e) {

    //se ejecutará la función si el elemento al que le hemos dado click contiene la clase especificada
    if (e.target.parentElement.classList.contains('btn-borrar')) {
        const respuesta = confirm('Deseas eliminar esta nota?');

        if (respuesta) {
            const id = e.target.parentElement.getAttribute('data-id');
            const indice = e.target.parentElement.getAttribute('data-indice');
            //llamado Ajax
            $.get("BorrarNota", {
                idnota: id,
                index: indice
            }, function (response) {
                $('#notas').html(response);
            })

        }
    }

}

