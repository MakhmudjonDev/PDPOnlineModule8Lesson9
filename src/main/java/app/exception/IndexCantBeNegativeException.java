package app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "You cant give negative index")
public class IndexCantBeNegativeException extends RuntimeException{

    public IndexCantBeNegativeException(String message){
        super(message);
    }

}
