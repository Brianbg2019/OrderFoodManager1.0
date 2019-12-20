$(document).ready(inicio);
function inicio() {
    DTEmpleado();
    getRoles();
    $("#guardarUsuario").click(guardar);
    $("#btnModificarUsuario").click(modificar);
    $("#EliminarUser").click(eliminar);
}

function DTEmpleado() {
    $("#Edatos").DataTable({
        ajax: {
            "url": "http://localhost:8080/usuario/listar",
            "method": "Get"
        },
        columns: [{
            data: "idUsuario",
            "width": "10%"
        }, {
            data: "nombre",
            "width": "10%"
        }, {
            data: "dui",
            "width": "10%"
        }, {
            data: "telefono",
            "width": "10%"
        }, {
            data: "rol",
            "width": "10%"
        }, {
            data: "operaciones",
            "width": "10%"
        }],
        "language": {
            "lengthMenu": "Mostrar _MENU_ ",
            "zeroRecords": "Datos no encontrados",
            "info": "Mostar páginas _PAGE_ de _PAGES_",
            "infoEmpty": "Datos no encontrados",
            "infoFiltered": "(Filtrados por _MAX_ total registros)",
            "search": "Buscar:",
            "paginate": {
                "first": "Primero",
                "last": "Anterior",
                "next": "Siguiente",
                "previous": "Anterior"
            },
        }
    });
}



function getRoles() {
    $.ajax({
        url: "http://localhost:8080/usuario/getAllRoles",
        method: "Get",
        data: null,
        success: function (response) {
            $("#roles").html("");
            response.forEach(i => {
                $("#roles").append(""
                    + "<option value='" + i.idRol + "'>" + i.rol + "</option>"
                    + "");
                $("#roles2").append(""
                    + "<option value='" + i.idRol + "'>" + i.rol + "</option>"
                    + "");
            });


        },
        error: errorPeticion
    });
}

function guardar() {
    $.ajax({
        url: "http://localhost:8080/usuario/save",
        method: "Get",
        data: {
            nombre: $("#nombre").val(),
            dui: $("#dui").val(),
            telefono: $("#telefono").val(),
            idRol: $("#roles").val()
        },
        success: function (response) {
            console.log(response.mensaje);
            location.reload();
        }
    });


}

function preModificar(id) {
    $("#modificarEmpleado").modal();
    $.ajax({
        url: "http://localhost:8080/usuario/getUsuario/" + id,
        method: "Get",
        data: null,
        success: function (response) {
            $("#idUsuario").val(response.idUsuario);
            $("#nombre2").val(response.nombre);
            $("#dui2").val(response.dui);
            $("#telefono2").val(response.telefono);
            $("#roles2").val(response.idRol.idRol);
        },
        error: errorPeticion
    });
}

function modificar(){
    var id = $("#idUsuario").val();
    $.ajax({
        url : "http://localhost:8080/usuario/update/" + id,
        method : "Get",
        data : {
            idUsuario : id,
            nombre : $("#nombre2").val(),
            dui : $("#dui2").val(),
            telefono : $("#telefono2").val(),
            idRol : $("#roles2").val()
        },
        success : function(response){
            console.log(response.Mensaje);
            location.reload();
        },
        error : errorPeticion
    });
}

function preEliminar(id){
    $("#idUser").val(id);
    $("#modalEliminar").modal();
}

function eliminar(){
    var id = $("#idUser").val();
    $.ajax({
        url : "http://localhost:8080/usuario/delete/" + id,
        method : "Get",
        data : null,
        success : function(response){
            console.log(response.Mensaje);
            location.reload();
        },
        error : errorPeticion
    });
}

function errorPeticion(response) {
    alert("Error al realizar la peticion: " + response);
    console.log("Error al realizar la peticion: " + response);
}
    /* nombre : $("#nombre").val(),
        dui : $("#dui").val(),
        telefono : $("#telefono").val(),
        idRol : $("#roles").val()*/