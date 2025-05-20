package quantv.indentityservice.controller;

import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quantv.indentityservice.dto.request.AuthenticationRequest;
import quantv.indentityservice.dto.request.IntrospectRequest;
import quantv.indentityservice.dto.response.ApiResponse;
import quantv.indentityservice.dto.response.AuthenticationResponse;
import quantv.indentityservice.dto.response.IntrospectResponse;
import quantv.indentityservice.service.AuthenticationService;

import java.text.ParseException;

@RestController
@RequestMapping("v1/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .results(result)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest authenticationRequest) throws ParseException, JOSEException {
        var result = authenticationService.introspect(authenticationRequest);
        return ApiResponse.<IntrospectResponse>builder()
                .results(result)
                .build();
    }

}
