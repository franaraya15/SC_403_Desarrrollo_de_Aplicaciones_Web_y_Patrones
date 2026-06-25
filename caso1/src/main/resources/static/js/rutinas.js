/*  En este archivo sólo debe poner las funciones que ocupa el caso, lo que
 * no requiera su caso, no puede existir en este archivo, automáticamente pierde el 50%
 * de los puntos si hay más código que no se utiliza en la solución de este caso
 */

// Muestra una vista previa de la imagen seleccionada en el input file del modal de agregar cleta
function mostrarImagen(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            document.getElementById('blah').setAttribute('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

