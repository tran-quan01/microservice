package quantv.indentityservice.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
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

    UserService userService;

    @PostMapping("")
    ApiResponse<?> addUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResults(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping("")
    ApiResponse<?> findAllUsers() {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        List<UserResponse> users = userService.getAllUsers();
        apiResponse.setResultList(users);
        return apiResponse;
    }

    @GetMapping("/{userId}")
    ResponseEntity<?> findById(@PathVariable("userId") String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{userId}")
    ResponseEntity<?> updateUser(@PathVariable("userId") String id,
                                 @RequestBody UserUpdateRequest userUpdateRequest) {
        return ResponseEntity.ok(userService.updateUserById(id, userUpdateRequest));
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<?> deleteUser(@PathVariable("userId") String id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted!");
    }
}
