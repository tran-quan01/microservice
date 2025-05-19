package quantv.indentityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import quantv.indentityservice.dto.request.UserCreationRequest;
import quantv.indentityservice.dto.request.UserUpdateRequest;
import quantv.indentityservice.dto.response.UserResponse;
import quantv.indentityservice.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreationRequest request);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
    UserResponse toUserResponse(User user);
    List<UserResponse> toUserResponses(List<User> users);
}
