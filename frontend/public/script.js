const API_ENDPOINT = "http://localhost:8080/api/todos";

let data;

// network requests

const getTodos = () =>
  fetch(API_ENDPOINT)
    .then((res) => res.json())
    .then((d) => (data = d))
    .then(() => updateList())
    .catch((err) => {
      console.error("Request failed", err);
    });

const postTodo = (payload) =>
  fetch(API_ENDPOINT, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  })
    .then(() => getTodos())
    .catch((err) => {
      console.error("Request failed", err);
    });

// update ui

const updateList = () => {
  emptyList();

  data.forEach((todo) => {
    const newLi = document.createElement("li");
    // newLi.classList.add("todo");
    newLi.innerText = todo.value;
    todoListEl.append(newLi);
  });
};

const emptyList = () => {
  while (todoListEl.firstChild) {
    todoListEl.removeChild(todoListEl.lastChild);
  }
};

module.exports = { getTodos, postTodo };
