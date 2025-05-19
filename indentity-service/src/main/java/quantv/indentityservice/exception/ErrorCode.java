package quantv.indentityservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001, "User already existed"),
    UNCATCH_EXCEPTION(9999, "Uncaught exception"),
    USER_VALIDATION(1003, "user name must be at least 3 characters"),
    USER_PASSWORD_INVALID(1004, "password must be at least 8 characters"),
    USER_NOT_EXISTED(1005, "user not existed"),
    UNAUTHENTICATED(1006, "unauthenticated"),
    ;

    private int code;
    private String message;

}
