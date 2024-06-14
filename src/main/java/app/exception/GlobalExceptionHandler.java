package app.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("app")
public class GlobalExceptionHandler {

    @ExceptionHandler(IndexCantBeNegativeException.class)
    public ModelAndView invalidInput(HttpServletRequest request, IndexCantBeNegativeException e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
    }

}
