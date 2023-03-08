package com.example.userdatams.web;

import com.example.userdatams.model.UserDataDto;
import com.example.userdatams.model.UserDto;
import com.example.userdatams.service.UserDataService;
import com.example.userdatams.service.UserService;
import jakarta.websocket.OnError;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
@Log4j2
@Controller
@RequiredArgsConstructor
public class WebController {

    private final UserService userService;
    private final UserDataService userDataService;
    @GetMapping("/login")
    public String login(@RequestParam(name="status", required = false) String status, Model model){
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("status", status);
        return "login";
    }

    @PostMapping("/login")
    public String handleLoginProcess(UserDto userDto){
       try{
           Long activeUserId = userService.validateUserInfo(userDto);
           return "redirect:my-profile?userId="+activeUserId;
       }catch (Exception e){
           return "redirect:login?status=user_not_found";
       }
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model){
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String completeRegistration(UserDto userDto){
        Long userId = userService.saveUser(userDto);
        log.info("user with id: {} created", userId);
        return "redirect:complete-registration?userId="+userId;
    }

    @GetMapping("/complete-registration")
    public String registration(Model model,  @RequestParam(name="userId", required = false)Long userId){
        model.addAttribute("userId", userId);
        log.info("userid: {}", userId);
        UserDataDto userDataDto =  new UserDataDto();
        model.addAttribute("userDataDto", userDataDto);
        return "complete-registration";
    }

    @GetMapping("/user-info")
    public String completeRegistration(){
        return "user-info";
    }

    @PostMapping("save-user-data")
    public String saveUserData(UserDataDto userDataDto, String role){
        userService.changeUserRole(userDataDto.getUserId(), role);
        userDataService.saveUserData(userDataDto);
        return "redirect:/my-profile";
    }

    @GetMapping("/my-profile")
    public String myProfilePage(Model model, @RequestParam (name="userId", required = false)Long userId){
        model.addAttribute("userId", userId);
        UserDataDto activeUser = new UserDataDto();
        String role;
        try{
            activeUser = userDataService.getUser(userId);
            role = userService.getUserRole(userId);
        }catch (Exception e){
            activeUser.setFirstName("User");
            role="user";
        }
        model.addAttribute("firstName", activeUser.getFirstName());
        model.addAttribute("role", role);
        return "my-profile";
    }

    @GetMapping("/update-profile")
    public String updateMyProfile(Model model){
//        model.addAttribute("user",)
        return "update-profile";
    }


}
