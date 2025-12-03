package quantv.indentityservice.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import quantv.indentityservice.dto.request.UserCreationRequest;
import quantv.indentityservice.dto.request.UserUpdateRequest;
import quantv.indentityservice.dto.response.ApiResponse;
import quantv.indentityservice.dto.response.UserResponse;
import quantv.indentityservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    UserService userService;

    @PostMapping("")
    ApiResponse<UserResponse> addUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResults(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<?> findAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username : {} ", authentication.getName());
        authentication.getAuthorities().forEach(
             grantedAuthority -> log.info(grantedAuthority.getAuthority())
        );

        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        List<UserResponse> users = userService.getAllUsers();
        apiResponse.setResultList(users);
        return apiResponse;
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> findById(@PathVariable("userId") String id) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        UserResponse users = userService.getUserById(id);
        apiResponse.setResults(users);
        return apiResponse;
    }

    @GetMapping("/info")
    ApiResponse<UserResponse> getMyInfo() {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        UserResponse users = userService.getMyInfo();
        apiResponse.setResults(users);
        return apiResponse;
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable("userId") String id,
                                 @RequestBody UserUpdateRequest userUpdateRequest) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        UserResponse users = userService.updateUserById(id, userUpdateRequest);
        apiResponse.setResults(users);
        return apiResponse;
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<?> deleteUser(@PathVariable("userId") String id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted!");
    }
}
