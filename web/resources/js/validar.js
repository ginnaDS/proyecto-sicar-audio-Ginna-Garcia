function validar(e) {
    
    $('#validaciones').formValidation({
  
    });
    //});
}
	function sololetras(e){
 			key=e.keyCode || e.which ;

 			teclado=String.fromCharCode(key) ;

 			letras="abcdefghijklmnñopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
                            
 			especiales="8-37-38-46";

 			teclado_especial=false ;

 			for(var i in especiales){

 				if(key==especiales){
 					teclado_especial=true;
 				}
 			}

 			if(letras.indexOf(teclado)==-1 && !teclado_especial){
 				return false; 
 			}
 		}
 	
 		
 		function solonumeros(e){
 			key=e.keyCode || e.which ;

 			teclado=String.fromCharCode(key) ;

 			numeros="0123456789";

 			especiales="8-37-38-46";

 			teclado_especial=false ;

 			for(var i in especiales){

 				if(key==especiales){
 					teclado_especial=true;
 				}
 			}

 			if(numeros.indexOf(teclado)==-1 && !teclado_especial){
 				return false; 
 			}
 		}
                
         function placa(e){
             expr = /^([A-Z]{3})+([0-9]{3})+$/;
//             expr = /^([A-Z]{3})+([0-9]{3})+$/;
               
         }       

//function validarEmail( email ) {
////    expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
//      expr = /^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$/;
//// expresion regular para la placa   expr = /^([A-Z]{3})+([0-9]{3})+$/;
//    if ( !expr.test(email) )
//        alert("Error: La dirección de correo " + email + " es incorrecta.");
//}

//function validarEmail(valor) {
//  if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3,4})+$/.test(valor)){
//   alert("La dirección de email " + valor + " es correcta.");
//  }else {
//   alert("La dirección de email es incorrecta.");
//  }
//}
//
// public function aCar(){
// var producto= $("#productos").value();
//  var cant= $("#cantidad").val();
//  if (producto==0) {
//    alert(
//        'Debe seleccionar algun producto'
//        );
//  }else{
//    if (cant=="") {
//        alert('Debe seleccionar alguna cantidad');
//    }if (cant>5) {
//      alert('Maximo 5 productos');
//    }else{
//  if (cant<=0) {
//      alert('Minimo 1 producto');
//    }else{
//
//
//
// 
//     document.getElementById("Pr1").innerHTML ="";
//
//
//  }
// }
//
//
//
// 
//
// }
//
// }