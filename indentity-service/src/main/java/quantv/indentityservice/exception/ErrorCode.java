package quantv.indentityservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    USER_EXISTED(1001, "User already existed", HttpStatus.BAD_REQUEST),
    UNCATCH_EXCEPTION(9999, "Uncaught exception", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_VALIDATION(1003, "user name must be at least 3 characters", HttpStatus.BAD_REQUEST),
    USER_PASSWORD_INVALID(1004, "password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "user not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN)
    ;
    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
