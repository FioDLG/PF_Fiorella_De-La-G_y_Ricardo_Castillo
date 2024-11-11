// Función para manejar la galería de imágenes
function toggleGallery(sectionId, direction) {
    const gallerySection = document.getElementById(sectionId);
    const galleryImages = gallerySection.querySelectorAll('.gallery-image');
    let currentIndex = 0;

    // Encontrar la imagen activa
    for (let i = 0; i < galleryImages.length; i++) {
        if (galleryImages[i].classList.contains('active')) {
            currentIndex = i;
            break;
        }
    }

    // Desactivar la imagen actual
    galleryImages[currentIndex].classList.remove('active');

    // Calcular el índice de la siguiente imagen
    let nextIndex;
    if (direction === 'prev') {
        nextIndex = (currentIndex - 1 + galleryImages.length) % galleryImages.length;
    } else {
        nextIndex = (currentIndex + 1) % galleryImages.length;
    }

    // Activar la siguiente imagen
    galleryImages[nextIndex].classList.add('active');
}

// Evento para manejar la navegación con teclado en la galería
document.addEventListener('keydown', function (event) {
    const activeSection = document.querySelector('.gallery-section:not(.hidden)');
    if (!activeSection) return;

    const galleryImages = activeSection.querySelectorAll('.gallery-image');
    let currentIndex = -1;

    // Encontrar la imagen activa
    for (let i = 0; i < galleryImages.length; i++) {
        if (galleryImages[i].classList.contains('active')) {
            currentIndex = i;
            break;
        }
    }

    if (currentIndex === -1) return;

    // Manejar eventos de teclado
    switch (event.key) {
        case 'ArrowLeft':
            toggleGallery(activeSection.id, 'prev');
            break;
        case 'ArrowRight':
            toggleGallery(activeSection.id, 'next');
            break;
        default:
            return; // Salir si no es una tecla de flecha
    }
});

// Función para manejar el formulario de reserva
document.getElementById('data-form').addEventListener('submit', function (event) {
    event.preventDefault(); // Prevenir el envío del formulario

    // Obtener los valores del formulario
    const nombre = document.getElementById('nombre').value;
    const apellido = document.getElementById('apellido').value;
    const email = document.getElementById('email').value;
    const telefono = document.getElementById('telefono').value;
    const checkin = document.getElementById('checkin').value;
    const checkout = document.getElementById('checkout').value;
    const personas = document.getElementById('personas').value;
    const photo = document.getElementById('photo').files[0]; // Obtener la imagen seleccionada

    // Crear un objeto de reserva
    const reserva = {
        nombre,
        apellido,
        email,
        telefono,
        checkin,
        checkout,
        personas,
        photo
    };

    // Agregar la reserva al calendario
    agregarReservaAlCalendario(reserva);

    // Limpiar el formulario
    document.getElementById('data-form').reset();
});

// Función para agregar la reserva al calendario
function agregarReservaAlCalendario(reserva) {
    const tableBody = document.getElementById('reservations-table').getElementsByTagName('tbody')[0];

    // Crear una nueva fila
    const newRow = tableBody.insertRow();

    // Insertar celdas con los datos de la reserva
    const photoCell = newRow.insertCell();
    const photoImg = document.createElement('img');
    photoImg.src = URL.createObjectURL(reserva.photo); // Mostrar la imagen seleccionada
    photoImg.alt = 'Imagen de reserva';
    photoImg.classList.add('reservation-image');
    photoCell.appendChild(photoImg);

    newRow.insertCell().textContent = reserva.nombre;
    newRow.insertCell().textContent = reserva.apellido;
    newRow.insertCell().textContent = reserva.email;
    newRow.insertCell().textContent = reserva.telefono;
    newRow.insertCell().textContent = reserva.checkin;
    newRow.insertCell().textContent = reserva.checkout;
    newRow.insertCell().textContent = reserva.personas;
}

// Función para cambiar entre secciones
const homeLink = document.getElementById('home-link');
const reserveLink = document.getElementById('reserve-link');
const calendarLink = document.getElementById('calendar-link');
const aboutLink = document.getElementById('about-link');
const contactLink = document.getElementById('contact-link');

const homeSection = document.getElementById('home');
const reserveSection = document.getElementById('reserve');
const calendarSection = document.getElementById('calendar');
const aboutSection = document.getElementById('about');
const contactSection = document.getElementById('contact');

homeLink.addEventListener('click', () => {
    mostrarSeccion(homeSection);
});

reserveLink.addEventListener('click', () => {
    mostrarSeccion(reserveSection);
});

calendarLink.addEventListener('click', () => {
    mostrarSeccion(calendarSection);
});

aboutLink.addEventListener('click', () => {
    mostrarSeccion(aboutSection);
});

contactLink.addEventListener('click', () => {
    mostrarSeccion(contactSection);
});

function mostrarSeccion(section) {
    // Ocultar todas las secciones
    homeSection.classList.add('hidden');
    reserveSection.classList.add('hidden');
    calendarSection.classList.add('hidden');
    aboutSection.classList.add('hidden');
    contactSection.classList.add('hidden');

    // Mostrar la sección seleccionada
    section.classList.remove('hidden');
}