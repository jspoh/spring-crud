# REST controller

## Return JSON data

Create java class for item, complete with constructors, getters, and setters
```java
public class Todo {
  private Integer id;
  private String value;

  @Autowired
  public Todo(Integer id, String value) {
    this.id = id;
    this.value = value;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
```
Then, just return the data normally as a Todo object or an `ArrayList`
```java
@RestController
@RequestMapping("/api")
public class Controller {
  private List<Todo> todos;

  @PostConstruct  // kinda like onInit
  public void loadData() {
    this.todos = new ArrayList<>();
    this.todos.add(new Todo(1, "value1"));
    this.todos.add(new Todo(2, "value2"));
    this.todos.add(new Todo(3, "value3"));
  }

  @GetMapping("/todos")
  public List<Todo> getTodos() {
    return this.todos;
  }

  @GetMapping("/todo/{id}")
  public Todo getTodo(@PathVariable Integer id) {
    if (id >= this.todos.size() || id < 0) {
      throw new TodoNotFoundException("Todo can not be found - " + id);
    }

    return todos.get(id);
  }
}
```
The data here is hardcoded in `@PostConstruct`

# Exception handling

Create Exception class extending `RuntimeException` class. Can use Intellij IDE to generate superclass constructors.
```java
public class TodoNotFoundException extends RuntimeException {
  public TodoNotFoundException(String message) {
    super(message);
  }
}
```
Create response class to return JSON (with Jackson)
```java
public class TodoErrorResponse {
  private int status;
  private String message;
  private long timeStamp;

  public TodoErrorResponse() {}

  public TodoErrorResponse(int status, String message, long timeStamp) {
    this.status = status;
    this.message = message;
    this.timeStamp = timeStamp;
  }

  // getters and setters
  
  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public long getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(long timeStamp) {
    this.timeStamp = timeStamp;
  }
}
```

Add exception handlers with `@ExceptionHandler` syntax in rest controller
```java
@RestController
@RequestMapping("/api")
public class Controller {
  
  ...
  
  // handle exceptions of type TodoNotFoundException.
  @ExceptionHandler
  public ResponseEntity<TodoErrorResponse> handleException(TodoNotFoundException err) {
    TodoErrorResponse res = new TodoErrorResponse(HttpStatus.NOT_FOUND.value(), err.getMessage(), System.currentTimeMillis());
//    res.setStatus(HttpStatus.NOT_FOUND.value());
//    res.setMessage(err.getMessage());
//    res.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
  }

  // handle all other exceptions (see parameter of Exception type catches all exceptions)
  @ExceptionHandler
  public ResponseEntity<TodoErrorResponse> handleException(Exception err) {
    TodoErrorResponse res = new TodoErrorResponse(HttpStatus.BAD_REQUEST.value(), err.getMessage(), System.currentTimeMillis());

    return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
  }
}
```

# GLOBAL exception handlers

Use `@ControllerAdvice`. Controller Advice is like a middleman for client and controller.

Create new java class `TodoExceptionHandler`, decorate with `@ControllerAdvice` and add the `@ExceptionHandler`s.
```java
@ControllerAdvice
public class TodoExceptionHandler {
  // handle exceptions of type TodoNotFoundException.
  @ExceptionHandler
  public ResponseEntity<TodoErrorResponse> handleException(TodoNotFoundException err) {
    TodoErrorResponse res = new TodoErrorResponse(HttpStatus.NOT_FOUND.value(), err.getMessage(), System.currentTimeMillis());

    return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
  }

  // handle all other exceptions (see parameter of Exception type catches all exceptions)
  @ExceptionHandler
  public ResponseEntity<TodoErrorResponse> handleException(Exception err) {
    TodoErrorResponse res = new TodoErrorResponse(HttpStatus.BAD_REQUEST.value(), err.getMessage(), System.currentTimeMillis());

    return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
  }
}
```