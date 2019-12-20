$(document).ready(inicio);

function inicio(){
    $("#muestr2").hide();
    DTRoles();
    $("#guardarRol").click(guardar);
    $("#EditarRol").click(modificar);
    $("#btnEliminarRol").click(eliminar);
}

function DTRoles(){
    $("#Rdatos").DataTable({
        ajax : {
            "url" : "http://localhost:8080/roles/listar",
            "method" : "Get"
        },
        columns:[{
            data : "idRol",
            "width": "20%"
        },{
            data : "rol",
            "width": "20%"
        },{
            data : "Editar",
            "width": "20%"
        },{
            data : "Eliminar",
            "width": "20%"
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
        url : "http://localhost:8080/roles/save",
        method : "Get",
        data : {
            rol : $("#roles").val()
        },
        success : function(response){
            console.log(response.Mensaje);
            location.reload();
        },
        error:errorPeticion
    });
}
function preEditar(id){
     $("#muestr2").slideToggle(600);
     $.ajax({
         url : "http://localhost:8080/roles/getRoles/" + id,
         method : "Get",
         success : function(response){
             $("#id").val(response.idRol);
             $("#roles2").val(response.rol);
         },
         error : errorPeticion
     })
}

function modificar(){
    var id = $("#id").val();
    $.ajax({
        url : "http://localhost:8080/roles/update/" + id,
        method : "Get",
        data : {
            id : id,
            rol : $("#roles2").val()
        },
        success:function(response){
            console.log(response.Mensaje);
            location.reload();
        },
        error : errorPeticion
    });
}

function preEliminar(id){
    $("#idRoles").val(id);
    $("#Modaleliminar").modal();
}

function eliminar(){
    var id = $("#idRoles").val();
    $.ajax({
        url : "http://localhost:8080/roles/delete/" + id,
        method : "Get",
        success : function(response){
            location.reload();   
            console.log(response.Mensaje);
            
        },
        error : errorPeticion
    });
}

function errorPeticion(response) {
    alert("Error al realizar la peticion: " + response);
    console.log("Error al realizar la peticion: " + response);
}
