package app.service;

import app.dao.TodoDao;
import app.entity.Todo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@NoArgsConstructor
@Service
public class TodoServiceImpl implements TodoService{

    private TodoDao todoDao;

    @Autowired
    public TodoServiceImpl(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoDao.getAllTodos();
    }

    @Override
    public Todo getTodoById(Integer id) {
        return todoDao.getTodoById(id);
    }

    @Override
    public void addTodo(Todo todo) {
        todoDao.addTodo(todo);
    }

    @Override
    public void updateTodo(Todo todo) {
        todoDao.updateTodo(todo);
    }

    @Override
    public void deleteTodo(Integer id) {
        todoDao.deleteTodo(id);
    }
}
