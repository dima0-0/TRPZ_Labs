package com.example.office_communicator.auth.service;

import com.example.office_communicator.auth.dto.AuthenticationRequest;
import com.example.office_communicator.auth.dto.AuthenticationResponse;
import com.example.office_communicator.auth.dto.RegisterRequest;



public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse auth(AuthenticationRequest request);
}
