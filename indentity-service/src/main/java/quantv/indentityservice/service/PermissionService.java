package quantv.indentityservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quantv.indentityservice.dto.request.PermissionRequest;
import quantv.indentityservice.dto.response.PermissionResponse;
import quantv.indentityservice.entity.Permission;
import quantv.indentityservice.mapper.PermissionMapper;
import quantv.indentityservice.repository.PermissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse createPermission(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    public List<PermissionResponse> getAll(){
        var permissions = permissionRepository.findAll();
        return permissionMapper.toPermissionResponses(permissions);
    }

    public void delete(String permission){
        permissionRepository.deleteById(permission);
    }
}
