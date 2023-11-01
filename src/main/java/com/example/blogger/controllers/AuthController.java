package com.example.blogger.controllers;


import com.example.blogger.exception.BadRequestException;
import com.example.blogger.request.LoginRequest;
import com.example.blogger.request.SignupRequest;
import com.example.blogger.response.APIResponse;
import com.example.blogger.response.JwtResponse;
import com.example.blogger.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
	@RequestMapping("/api/auth")
	public class AuthController {

		private final AuthService authService;

		public AuthController(AuthService authService) {
			this.authService = authService;
		}

		@PostMapping("/login")
		public APIResponse<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
			JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
			return APIResponse.newSuccessResponse(jwtResponse);
		}

		@PostMapping("/signup")
		public APIResponse<String> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws BadRequestException {
			authService.registerUser(signUpRequest);
			return APIResponse.newSuccessResponse();
		}
	}
