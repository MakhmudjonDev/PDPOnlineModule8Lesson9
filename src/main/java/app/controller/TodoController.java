package app.controller;

import app.controller.dto.UserDTO;
import app.entity.Todo;
import app.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("dto", new UserDTO());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("dto") UserDTO userDTO, BindingResult errors){

        if (errors.hasErrors()){ // dont forgot to add jakarta-validation-api
            return "login";
        }

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            errors.rejectValue("confirmPassword", "", "confirm.not.equal");
            return "login";
        }

        return "redirect:/home";
    }

    @GetMapping("/home")
    public ModelAndView showAllTodos() {
        List<Todo> allTodos = todoService.getAllTodos();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("todos", allTodos);
        return modelAndView;
    }

    @GetMapping("/deleteTodo/{id}")
    public ModelAndView deleteTodo(@PathVariable Integer id) {
        todoService.deleteTodo(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/updateTodo/{id}")
    public ModelAndView updateTodo(@PathVariable Integer id) {
        Todo todo = todoService.getTodoById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update");
        modelAndView.addObject("toDo", todo);
        return modelAndView;
    }

    @PostMapping("/updateTodo")
    public ModelAndView updateTodo2(@RequestParam("newTitle") String newTitle,
                                    @RequestParam("newPriority") String newPriority,
                                    @RequestParam("id") Integer id) {
        Todo todo = todoService.getTodoById(id);
        todo.setTitle(newTitle);
        todo.setPriority(newPriority);

        todoService.updateTodo(todo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

//    @GetMapping("/addTodo")
//    public ModelAndView addTodo() {
//        ModelAndView modelAndView = new ModelAndView("addTodo");
//        return modelAndView;
//    }
    @GetMapping("/addTodo")
    public String addTodo(Model model) {
        model.addAttribute("todo", new Todo());
        return "addTodo";
    }

//    @PostMapping("/addTodo")
//    public ModelAndView addingTodo(//@RequestParam("id") Integer id,
//                                   @RequestParam("title") String title,
//                                   @RequestParam("priority") String priority){
//        Todo todo = new Todo(title, priority);
//        todoService.addTodo(todo);
//        ModelAndView modelAndView = new ModelAndView("redirect:/home");
//        return modelAndView;
//
//    }

    @PostMapping("/addTodo")
    public String addingTodo(@Valid @ModelAttribute("todo") Todo todo, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "addTodo";
        }
        todo.setCreatedAt(LocalDateTime.now());
        todoService.addTodo(todo);
        return "redirect:/home";
    }

}
