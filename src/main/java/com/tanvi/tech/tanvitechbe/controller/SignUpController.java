package com.tanvi.tech.tanvitechbe.controller;

import com.tanvi.tech.tanvitechbe.converter.ConverterFacade;
import com.tanvi.tech.tanvitechbe.dto.UserDTO;
import com.tanvi.tech.tanvitechbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/")
public class SignUpController {

    private final UserService service;

    private final ConverterFacade converterFacade;

    @Autowired
    public SignUpController(final UserService service,
                            final ConverterFacade converterFacade) {
        this.service = service;
        this.converterFacade = converterFacade;
    }

    @RequestMapping(value = "auth/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody final UserDTO dto) {
        if (service.findByEmail(dto.getEmail()) == null) {
            return new ResponseEntity<>(service.create(converterFacade.convert(dto)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Email already found", HttpStatus.BAD_REQUEST);
        }
    }
}
