const API = "http://localhost:8081";

// ---------------- AUTH ----------------
function register() {
    fetch(API + "/auth/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            name: document.getElementById("name").value,
            email: document.getElementById("email").value,
            password: document.getElementById("password").value,
        }),
    })
    .then(r => r.text())
    .then(msg => { document.getElementById("msg").innerText = msg; });
}

function login() {
    fetch(API + "/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            email: document.getElementById("email").value,
            password: document.getElementById("password").value,
        }),
    })
    .then(res => {
        if (!res.ok) throw new Error("Check email or password");
        return res.json();
    })
    .then(data => {
        localStorage.setItem("token", data.token);
        window.location.href = "todo.html";
    })
    .catch(err => alert(err.message));
}

// ---------------- TODOS ----------------
function loadTodos() {
    const token = localStorage.getItem("token");
    if (!token) { window.location.href = "index.html"; return; }

    fetch(API + "/todos", {
        headers: { "Authorization": "Bearer " + token }
    })
    .then(r => {
        if (!r.ok) throw new Error("Unauthorized");
        return r.json();
    })
    .then(todos => {
        const list = document.getElementById("todoList");
        list.innerHTML = "";

        if (todos.length === 0) {
            list.innerHTML = `<div class="empty-state">
                <p>No tasks yet.<br>Start by adding one above!</p>
            </div>`;
            return;
        }

        todos.forEach(t => {
            list.innerHTML += `
                <div class="task">
                    <div class="task-title ${t.completed ? 'done' : ''}">${t.title}</div>
                    <div class="btn-group">
                        <button class="icon-btn btn-check" onclick="toggle(${t.id})">✓</button>
                        <button class="icon-btn btn-del" onclick="removeTask(${t.id})">✕</button>
                    </div>
                </div>
            `;
        });
    })
    .catch(err => { logout(); });
}

function addTodo() {
    const input = document.getElementById("newTodo");
    if(!input.value) return;

    fetch(API + "/todos", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify({ title: input.value })
    }).then(() => {
        input.value = "";
        loadTodos();
    });
}

function toggle(id) {
    fetch(`${API}/todos/${id}/toggle`, {
        headers: { "Authorization": "Bearer " + localStorage.getItem("token") }
    }).then(loadTodos);
}

function removeTask(id) {
    fetch(`${API}/todos/${id}`, {
        method: "DELETE",
        headers: { "Authorization": "Bearer " + localStorage.getItem("token") }
    }).then(loadTodos);
}

function logout() {
    localStorage.removeItem("token");
    window.location.href = "index.html";
}

if (window.location.pathname.includes("todo.html")) {
    loadTodos();
}