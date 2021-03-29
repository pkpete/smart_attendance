package graduation_project.smart_attendance.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

//        String msg = "아이디 혹은 비밀번호가 일치하지 않습니다.";
//
//        if(exception instanceof BadCredentialsException){
//
//        }else if(exception instanceof InsufficientAuthenticationException){
//            msg = "유효하지 않은 비밀번호입니다.";
//        }
//
//        setDefaultFailureUrl("/?error=true&exception="+msg);

        super.onAuthenticationFailure(request,response,exception);
    }
}
