document.getElementById("btn__iniciar-sesion").addEventListener("click", iniciarSesion);
document.getElementById("btn__registrarse").addEventListener("click", register);


//Declaracion de variables
var contenedor_login_register = document.querySelector(".contenedor__login-register");
var formulario_login= document.querySelector(".formulario__login");
var formulario_register= document.querySelector(".formulario__register");
var caja_trasera_login= document.querySelector(".caja__trasera-login");
var caja_trasera_register= document.querySelector(".caja__trasera-register");

function iniciarSesion(){
    formulario_register.style.display = "none";
    contenedor_login_register.style.left="220px"
    formulario_login.style.display="block";
    caja_trasera_register.style.opacity="1";
    caja_trasera_login.style.opacity="0";
}

function register(){
    formulario_register.style.display = "block";
    contenedor_login_register.style.left="620px"
    formulario_login.style.display="none";
    caja_trasera_register.style.opacity="0";
    caja_trasera_login.style.opacity="1";
}


document.getElementById('formulario__login').addEventListener('Entrar', function(event) {
    event.preventDefault();

    const usuario = document.getElementById('User_Name').value;
    const password = document.getElementById('Password').value;

    // Enviar los datos al servidor
    const mysql = require('mysql');

    // Configuración de la conexión a la base de datos
    const db = mysql.createConnection({
        host: 'localhost',
        user: 'root',       // Tu usuario de MySQL
        password: 'Chimuelo1514.', // Tu contraseña de MySQL
        database: 'villamoncouer' // Nombre de la base de datos
    });
    
    db.connect((err) => {
        if (err) throw err;
        console.log('Conectado a la base de datos MySQL');
    });
    
});
