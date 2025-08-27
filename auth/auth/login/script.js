// Cambia esto si tu backend está en otro puerto o IP
const BASE_URL = 'http://localhost:8080/api';

// Cambiar entre formularios
function mostrarRegistro() {
  document.getElementById('loginForm').classList.remove('activo');
  document.getElementById('registerForm').classList.add('activo');
}

function mostrarLogin() {
  document.getElementById('registerForm').classList.remove('activo');
  document.getElementById('loginForm').classList.add('activo');
}

// ✅ Registrar nuevo usuario
async function crearCuenta() {
  const email = document.getElementById('registerEmail').value;
  const password = document.getElementById('registerPassword').value;

  try {
    const res = await fetch(`${BASE_URL}/registro`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ email, password })
    });

    const data = await res.text();

    if (res.ok) {
      alert('✅ Registro exitoso: ' + data);
      mostrarLogin();
    } else {
      alert('❌ Error en el registro: ' + data);
    }
  } catch (error) {
    alert('❌ No se pudo conectar con el servidor');
  }
}

// ✅ Iniciar sesión
async function iniciarSesion() {
  const email = document.getElementById('loginEmail').value;
  const password = document.getElementById('loginPassword').value;

  try {
    const res = await fetch(`${BASE_URL}/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ email, password })
    });

    const data = await res.text();

    if (res.ok) {
      alert('✅ Inicio de sesión exitoso: ' + data);
    } else {
      alert('❌ Error al iniciar sesión: ' + data);
    }
  } catch (error) {
    alert('❌ No se pudo conectar con el servidor');
  }
}
