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
    return todos.get(id);
  }
}
```
The data here is hardcoded in `@PostConstruct`