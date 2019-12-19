$(document).ready(inicio);
    function inicio(){
        DTCliente();
    }

    function DTCliente(){
        $("#Ctable").DataTable({
            ajax : {
                "url" : "http://localhost:8080/cliente/listar",
                "method" : "Get"
            },
            columns : [{
                data : "idCliente",
                "width": "20%"
            },{
                data : "nombre",
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

    