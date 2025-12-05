package quantv.indentityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import quantv.indentityservice.dto.request.RoleRequest;
import quantv.indentityservice.dto.response.RoleResponse;
import quantv.indentityservice.entity.Permission;
import quantv.indentityservice.entity.Role;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    // Tự động map Permission -> String
    @Mapping(target = "permissions", source = "permissions", qualifiedByName = "permissionSetToStringSet")
    RoleResponse toRoleResponse(Role role);

    // Map từng Permission -> String
    @Named("permissionToString")
    static String permissionToString(Permission permission) {
        return permission.getName(); // Lấy field name
    }

    // Map Set<Permission> -> Set<String>
    @Named("permissionSetToStringSet")
    static Set<String> permissionSetToStringSet(Set<Permission> permissions) {
        if (permissions == null) return null;
        return permissions.stream()
                .map(RoleMapper::permissionToString)
                .collect(Collectors.toSet());
    }
}
