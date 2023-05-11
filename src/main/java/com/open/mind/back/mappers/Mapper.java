package com.open.mind.back.mappers;

import com.open.mind.back.dto.AccountCredentialsRequest;
import com.open.mind.back.dto.AccountLoginRequest;
import com.open.mind.back.dto.CommentaryRequest;
import com.open.mind.back.dto.CommentaryResponse;
import com.open.mind.back.dto.PostRequest;
import com.open.mind.back.dto.PostResponse;
import com.open.mind.back.dto.UserDataRequest;
import com.open.mind.back.dto.UserResponse;
import com.open.mind.back.model.Commentary;
import com.open.mind.back.model.Post;
import com.open.mind.back.model.SecurityUser;
import com.open.mind.back.model.User;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/** Mapper for service layer. */
@org.mapstruct.Mapper(
    unmappedTargetPolicy = ReportingPolicy.WARN,
    builder = @Builder(disableBuilder = true))
public interface Mapper {
  /** Instance. */
  Mapper I = Mappers.getMapper(Mapper.class);

  /**
   * UserEntity to SecurityUserModel mapper.
   *
   * @param user userEntity.
   * @return SecurityUserModel.
   */
  SecurityUser userEntityToSecurityUserModel(com.open.mind.back.entity.User user);

  /**
   * SecurityUserModel to UserModel mapper.
   *
   * @param securityUser SecurityUserModel.
   * @return SecurityUserModel.
   */
  User securityUserModelToModel(SecurityUser securityUser);

  /**
   * UserEntity to UserModel mapper.
   *
   * @param user UserEntity.
   * @return UserModel.
   */
  User userEntityToModel(com.open.mind.back.entity.User user);

  /**
   * UserModel to UserEntity mapper.
   *
   * @param user UserModel.
   * @return UserEntity.
   */
  com.open.mind.back.entity.User userModelToEntity(User user);

  /**
   * Update UserEntity From UserModel.
   *
   * @param userModel UserModel.
   * @return UserEntity.
   */
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  com.open.mind.back.entity.User updateUserEntityFromModel(
      User userModel, @MappingTarget com.open.mind.back.entity.User user);

  /**
   * PostEntity to PostModel.
   *
   * @param post PostEntity.
   * @return PostModel.
   */
  Post postEntityToModel(com.open.mind.back.entity.Post post);

  /**
   * List of PostEntity to List of PostModel.
   *
   * @param post PostEntity.
   * @return List of PostModel.
   */
  List<Post> postEntitiesToModels(List<com.open.mind.back.entity.Post> post);

  /**
   * CommentaryModel to CommentaryEntity.
   *
   * @param commentary CommentaryModel.
   * @return CommentaryEntity.
   */
  com.open.mind.back.entity.Commentary commentaryModelToEntity(Commentary commentary);

  /**
   * CommentaryEntity to Commentary.
   *
   * @param commentary CommentaryEntity.
   * @return Commentary.
   */
  Commentary commentaryEntityToModel(com.open.mind.back.entity.Commentary commentary);

  /**
   * List of CommentaryEntity to List of Commentary.
   *
   * @param commentaryEntities List of CommentaryEntity.
   * @return List of Commentary.
   */
  List<Commentary> commentaryEntitiesToModels(
      List<com.open.mind.back.entity.Commentary> commentaryEntities);

  /**
   * UserDataRequestDto to UserModel mapper.
   *
   * @param requestDto UserDataRequestDto.
   * @return UserModel.
   */
  User userDataRequestDtoToUserModel(UserDataRequest requestDto);

  /**
   * AccountCredentialsRequestDto to UserModel mapper.
   *
   * @param requestDto AccountCredentialsRequestDto.
   * @return UserModel.
   */
  User accountCredentialsRequestDtoToUser(AccountCredentialsRequest requestDto);

  /**
   * AccountLoginRequestDto to UserModel mapper.
   *
   * @param requestDto AccountLoginRequestDto.
   * @return UserModel.
   */
  User accountLoginRequestDtoToUser(AccountLoginRequest requestDto);

  /**
   * PostRequestDto to PostModel mapper.
   *
   * @param requestDto PostRequestDto.
   * @return PostModel.
   */
  Post postRequestDtoToModel(PostRequest requestDto);

  /**
   * PostModel to PostResponseDto mapper.
   *
   * @param post PostModel.
   * @return PostResponseDto.
   */
  PostResponse postModelToResponseDto(Post post);

  /**
   * UserModel to UserModelResponseDto mapper.
   *
   * @param user UserModel.
   * @return UserModelResponseDto.
   */
  UserResponse userModelToResponseDto(User user);

  /**
   * List of PostModel to List of PostResponseDto mapper.
   *
   * @param post PostModel
   * @return PostResponseDto
   */
  List<PostResponse> postModelsToResponseDto(List<Post> post);

  /**
   * CommentaryModel to CommentaryResponseDto.
   *
   * @param commentary CommentaryModel.
   * @return CommentaryResponseDto.
   */
  CommentaryResponse commentaryModelToResponseDto(Commentary commentary);

  /**
   * CommentaryRequestDto to CommentaryModel mapper.
   *
   * @param commentaryRequest CommentaryRequestDto.
   * @return CommentaryModel.
   */
  Commentary commentaryRequestDtoToModel(CommentaryRequest commentaryRequest);

  /**
   * List of CommentaryModel to List of CommentaryResponseDto.
   *
   * @param commentaries List of CommentaryModel.
   * @return List of CommentaryResponseDto.
   */
  List<CommentaryResponse> commentaryModelsToResponseDtoList(List<Commentary> commentaries);
}
