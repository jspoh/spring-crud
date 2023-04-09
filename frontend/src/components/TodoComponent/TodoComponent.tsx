import React from "react";
import "./TodoComponent.css";
import { Todo } from "../../DataService";
import {
  getTodos,
  getTodo,
  postTodo,
  updateTodo,
  deleteTodo,
} from "../../DataService";

interface TodoComponentState {
  todos: Todo[];
}

class TodoComponent extends React.Component<{}, TodoComponentState> {
  constructor(props: {}) {
    super(props);
    this.state = { todos: [] };
  }

  componentDidMount(): void {
    this.loadTodos();
  }

  async loadTodos() {
    const todos = await getTodos();
    this.setState({ todos: todos });
  }

  onSubmit = (e: any) => {
    e.preventDefault();
    const iptEl = document.querySelector("input")!;
    if (!iptEl.value) {
      return;
    }

    const payload: Todo = {
      id: null,
      value: iptEl.value,
    };
    postTodo(payload)
      .then(() => {
        this.loadTodos();
        iptEl.value = "";
      })
      .catch(() => alert("Adding failed. Please try again later."));
  };

  removeTodo = (e: any) => {
    const id = e.currentTarget.dataset.todoid;
    deleteTodo(id).then(() => this.loadTodos());
  };

  editTodo = (e: any) => {
    const id = e.currentTarget.dataset.todoid;
    const todoEl = document.getElementById(id)!;
    todoEl.setAttribute("contentEditable", "true");
    todoEl.classList.add("editing");

    const editingHandler = (ke: KeyboardEvent) => {
      if (ke.key !== "Enter") {
        return;
      }

      const payload: Todo = {
        id: id,
        value: todoEl.innerText,
      };

      updateTodo(payload).then(() => this.loadTodos());

      todoEl.classList.remove("editing");
      todoEl.setAttribute("contentEditable", "false");

      // prevent memory leaks
      todoEl.removeEventListener("keydown", editingHandler);
    };

    todoEl.addEventListener("keydown", editingHandler);
  };

  render() {
    const { todos } = this.state;
    return (
      <div id="main">
        <form onSubmit={this.onSubmit}>
          <section>
            <input type="text" maxLength={300} />
            <button>Add</button>
          </section>
          <ul className="todoWrapper">
            {todos.map((todo) => (
              <li key={todo.id} className="todoContainer">
                <span id={todo.id!.toString()}>{todo.value}</span>

                <span className="btnContainer">
                  <i
                    data-todoid={todo.id!.toString()}
                    onClick={this.editTodo}
                    className="fa-solid fa-pencil"
                  ></i>
                  <i
                    data-todoid={todo.id!.toString()}
                    onClick={this.removeTodo}
                    className="fa-solid fa-trash"
                  ></i>
                </span>
              </li>
            ))}
          </ul>
        </form>
      </div>
    );
  }
}

export default TodoComponent;
