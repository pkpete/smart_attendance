package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.dto.FindAccountDto;
import graduation_project.smart_attendance.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class PythonController {

    private final AccountService accountService;

    @PostMapping("/python/login")
    public Boolean login(HttpServletRequest request){
        FindAccountDto findAccountDto = new FindAccountDto();
        findAccountDto.setUsername(request.getParameter("username"));
        findAccountDto.setPassword(request.getParameter("password"));
        System.out.println("username: " + findAccountDto.getUsername() + " password: " + findAccountDto.getPassword());
        return accountService.findUser(findAccountDto);
    }
}
