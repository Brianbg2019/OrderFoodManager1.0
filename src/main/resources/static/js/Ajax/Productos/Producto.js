$(document).ready(inicio);
function inicio() {
    DTProductos();
    getCategorias();
    $("#guardarProducto").click(guardar);
    $("#btnmodificarProducto").click(modificar);
    $("#eliminarProducto").click(eliminar);
}

function DTProductos() {
    $("#Pdatos").DataTable({
        ajax: {
            "url": "http://localhost:8080/producto/listar",
            "method": "Get"
        },
        columns: [{
            data: "idProducto",
            "width": "10%"
        }, {
            data: "imagen",
            "width": "10%"
        }, {
            data: "nombre",
            "width": "10%"
        }, {
            data: "categoria",
            "width": "10%"
        }, {
            data: "precio",
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

function guardar(){
    $.ajax({
        url : "http://localhost:8080/producto/save",
        method : "Get",
        data : {
            imagen : $("#imagen").val(),
            nombre : $("#nombre").val(),
            idCategoria : $("#categoria").val(),
            precio : $("#precio").val()
        },
        success : function(response){
            //DTProductos();
            location.reload();
        },
        error : errorPeticion
    });
}

function getCategorias() {
    $.ajax({
        url: "http://localhost:8080/producto/allCategorias",
        method: "Get",
        data: null,
        success: function (response) {
            $("#categoria").html("");
            response.forEach(i => {
                $("#categoria").append(""
                + "<option value='"+i.idCategoria+"'>" + i.nombreCategoria + "</option>"
                + "");

                $("#categoria2").append(""
                + "<option value='"+i.idCategoria+"'>" + i.nombreCategoria + "</option>"
                + "");
            });
        }
    });
}

function errorPeticion(response) {
    alert("Error al realizar la peticion: " + response);
    console.log("Error al realizar la peticion: " + response);
}

function preModificar(id){
    $("#modificarProducto").modal();
    $.ajax({
        url : "http://localhost:8080/producto/getProducto/" + id,
        method : "Get",
        success : function(response){
            $("#idProducto").val(response.idProducto);
            $("#nombre2").val(response.nombreProducto);
            $("#precio2").val(response.precio);
            $("#imagen2").val(response.imagen);
            $("#categoria2").val(response.idCategoria.idCategoria);
           
        },
        error: errorPeticion
    });
}

function modificar(){
    var id = $("#idProducto").val();
    $.ajax({
        url : "http://localhost:8080/producto/update/" + id,
        method : "Get",
        data : {
            idProducto : id,
            imagen : $("#imagen2").val(),
            nombre : $("#nombre2").val(),
            idCategoria : $("#categoria2").val(),
            precio : $("#precio2").val()
        },
        success : function(response){
            console.log(response.mensaje);
            location.reload();
            //location.reload();
        },
        error : errorPeticion
    });
};

function preEliminar(id){
    $("#idProduct").val(id);
    $("#deleteProductoModal").modal();
}

function eliminar(){
    var id = $("#idProduct").val();
    $.ajax({
        url : "http://localhost:8080/producto/delete/" + id,
        method : "Get",
        data : null,
        success : function(response){
            console.log(response.Mensaje);
            location.reload();
        },
        error : errorPeticion
    });
}