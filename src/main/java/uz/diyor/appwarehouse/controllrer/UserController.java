package uz.diyor.appwarehouse.controllrer;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.dto.UserDto;
import uz.diyor.appwarehouse.entity.User;
import uz.diyor.appwarehouse.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    final UserService userService;


   @PostMapping
    public Result addUser(@RequestBody UserDto userDto) {
       return userService.addUser(userDto);
   }

   @GetMapping
    public List<User> getUsers() {
       return userService.getAllUser();
   }

   @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id) {
       return userService.deteleByIdUser(id);
    }

    @PutMapping("/{id}")
    public Result updateUser(@PathVariable Integer id,
                             @RequestBody User user) {
       return userService.updateUser(id,user);
    }
}
