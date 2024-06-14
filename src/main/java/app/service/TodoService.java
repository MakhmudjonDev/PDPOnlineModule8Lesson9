package app.service;

import app.entity.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();
    Todo getTodoById(Integer id);
    void addTodo(Todo todo);
    void updateTodo(Todo todo);
    void deleteTodo(Integer id);
}
