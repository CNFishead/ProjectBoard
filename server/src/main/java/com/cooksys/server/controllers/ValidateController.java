package com.cooksys.server.controllers;

import com.cooksys.server.models.CredentialDto;
import com.cooksys.server.models.UpdatePasswordDto;
import com.cooksys.server.models.UserDto;
import com.cooksys.server.services.ValidateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ValidateController {

    private final ValidateService validateService;

    @PostMapping("/login")
    public UserDto login(@RequestBody CredentialDto credentialDto) {
        return validateService.login(credentialDto);
    }

    @PatchMapping("/changePassword")
    public UserDto changePassword(@RequestBody UpdatePasswordDto updatePasswordDto) {
        return validateService.changePassword(updatePasswordDto);
    }

}
