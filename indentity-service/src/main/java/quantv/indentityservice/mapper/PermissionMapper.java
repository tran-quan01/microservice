package quantv.indentityservice.mapper;

import org.mapstruct.Mapper;
import quantv.indentityservice.dto.request.PermissionRequest;
import quantv.indentityservice.dto.response.PermissionResponse;
import quantv.indentityservice.entity.Permission;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
    List<PermissionResponse> toPermissionResponses(List<Permission> permissions);
}
