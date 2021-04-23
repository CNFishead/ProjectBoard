package com.cooksys.server.services;

import com.cooksys.server.models.CredentialDto;
import com.cooksys.server.models.UpdatePasswordDto;
import com.cooksys.server.models.UserDto;

public interface ValidateService {

    UserDto login(CredentialDto credentialDto);

    UserDto changePassword(UpdatePasswordDto updatePasswordDto);

}
