package quantv.indentityservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import quantv.indentityservice.dto.request.RoleRequest;
import quantv.indentityservice.dto.response.ApiResponse;
import quantv.indentityservice.dto.response.RoleResponse;
import quantv.indentityservice.service.RoleService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/v1/api/roles")
public class RoleController {

    RoleService roleService;

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .results(roleService.getAll()).build();
    }

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder().results(roleService.createRole(request)).build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> deleteRole(String role){
        roleService.delete(role);
        return ApiResponse.<Void>builder().build();
    }
}
