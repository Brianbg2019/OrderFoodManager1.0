$(document).ready(inicio);

function inicio(){
    DTCategoria();
    $("#guardarCategoria").click(guardar);
}

function DTCategoria(){
    $("#TablaCategoria").DataTable({
        ajax :{
            "url" : "http://localhost:8080/categoria/listar",
            "method" : "Get"
        },
        columns :[{
            data : "idCategoria",
            "width": "20%"
        },{
            data : "categoria",
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
        url : "http://localhost:8080/categoria/save",
        method : "Get",
        data:{
            categoria : $("#categoria").val()
        },
        success:function(response){
            $("#categoria").val("");
            location.reload();
        },
        error : errorPeticion
    });
}

function errorPeticion(response) {
	alert("Error al realizar la peticion: " + response);
	console.log("Error al realizar la peticion: " + response);
}