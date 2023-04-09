import "./App.css";
import React from "react";
import TodoComponent from "./components/TodoComponent/TodoComponent";

class App extends React.Component {
  constructor(props: never) {
    super(props);
  }

  render(): React.ReactNode {
    return (
      <div>
        <TodoComponent></TodoComponent>
      </div>
    );
  }
}

export default App;
