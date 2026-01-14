package uz.diyor.appwarehouse.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.dto.UserDto;
import uz.diyor.appwarehouse.entity.User;
import uz.diyor.appwarehouse.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;

    public  Result addUser(UserDto userDto) {
        boolean exists = userRepository.existsByPhoneNumber(userDto.phoneNumber());
        if (exists) {
            return new Result("Already exist",false);
        }
        User user = new User();
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setPhoneNumber(userDto.phoneNumber());
        user.setPassword(userDto.password());
        user.setActive(true);
        user.setCode(generateCode());
        user.setWarehouses(userDto.warehouses());
        userRepository.save(user);
        return new Result("Success",true);
    }

    public String generateCode() {
        Integer maxCode = userRepository.getMaxCode();
        if (maxCode == null) return "1";
        return String.valueOf(maxCode + 1);
    }


    public List<User> getAllUser() {
       return userRepository.findAll();
    }

    public Result deteleByIdUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return new Result("Success",true);
        }
        return new Result("Not found",false);
    }

    public Result updateUser(Integer id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return new Result("Not found",false);
        }
        User oldUser = userOptional.get();
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        oldUser.setPassword(user.getPassword());
        oldUser.setActive(user.isActive());
        oldUser.setCode(generateCode());
        oldUser.setWarehouses(user.getWarehouses());
        userRepository.save(oldUser);
        return new Result("Success",true);

    }
}
