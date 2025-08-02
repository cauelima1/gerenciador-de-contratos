async function fazerLogin() {

  const login = document.getElementById("login").value;
  const password = document.getElementById("password").value;

  try {
    const response = await fetch('http://localhost:8080/login', {
      method: 'POST',
      credentials: 'include',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ login: login, password: password })
    });

    console.log(new Headers({ 'Content-Type': 'application/json' }).get('Content-Type'));

    const msg = await response.text();
    console.log(msg);

    if (response.ok) {
      window.location.href = "index";
    } else {
      alert("Login inválido!");
      document.getElementById("login").value = "";
      document.getElementById("password").value = "";
      const msg = await response.text();
      console.log(response.status);
    }

  } catch (error) {
    alert("Verifique as credenciais!");
    console.error(error);
  }
}

