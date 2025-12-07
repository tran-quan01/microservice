package quantv.indentityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import quantv.indentityservice.dto.request.UserCreationRequest;
import quantv.indentityservice.dto.request.UserUpdateRequest;
import quantv.indentityservice.dto.response.UserResponse;
import quantv.indentityservice.entity.Permission;
import quantv.indentityservice.entity.Role;
import quantv.indentityservice.entity.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    User toUser(UserCreationRequest request);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    UserResponse toUserResponse(User user);
    List<UserResponse> toUserResponses(List<User> users);

    default String map(Role role) {
        return role.getName();
    }

    default Set<String> map(Set<Permission> permissions) {
        if (permissions == null) return null;
        return permissions.stream()
                .map(Permission::getName)
                .collect(Collectors.toSet());
    }
}
