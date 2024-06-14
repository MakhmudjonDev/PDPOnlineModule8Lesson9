package app.dao;

import app.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TodoDao {
    List<Todo> getAllTodos();
    Todo getTodoById(Integer id);
    void addTodo(Todo todo);
    void updateTodo(Todo todo);
    void deleteTodo(Integer id);
}
