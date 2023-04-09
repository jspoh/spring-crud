export interface Todo {
  id: number | null;
  value: string;
}

const API_ENDPOINT = "http://localhost:8080/api/todos";

export const getTodos = async (): Promise<Todo[]> => {
  const data = (await fetch(API_ENDPOINT)).json();
  return data;
};

export const getTodo = (id: number) => {
  const data = new Promise((resolve, reject) =>
    fetch(API_ENDPOINT + `/${id}`)
      .then((res) => {
        resolve(res.json());
      })
      .catch((err) => {
        console.error("Request failed", err);
        reject(err);
      })
  );

  return data;
};

export const postTodo = (payload: Todo): Promise<void | null> =>
  fetch(API_ENDPOINT, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  })
    .then((res) => null)
    .catch((err) => {
      console.error("Request failed", err);
    });

export const updateTodo = (payload: Todo): Promise<void | null> =>
  fetch(API_ENDPOINT, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  })
    .then(() => null)
    .catch((err) => {
      console.error("Request failed", err);
    });

export const deleteTodo = (id: number): Promise<void | null> =>
  fetch(API_ENDPOINT + `/${id}`, {
    method: "DELETE",
  })
    .then(() => null)
    .catch((err) => {
      console.error("Request failed\n", err);
    });
