package uz.diyor.appwarehouse.controllrer;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.diyor.appwarehouse.dto.InputDto;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Input;
import uz.diyor.appwarehouse.service.InputService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inputs")
public class InputController {

    private final InputService inputService;


    // CREATE
    @PostMapping
    public Result  addInput(@RequestBody InputDto dto) {
        return inputService.addInput(dto);
    }

    @GetMapping
    public List<Input> getInputs() {
        return inputService.getAllInputs();
    }

    @DeleteMapping("/{id}")
    public Result deleteInput(@PathVariable Integer id) {
        return inputService.deleteInput(id);
    }

    @PutMapping("/{id}")
    public Result updateInput(@PathVariable Integer id,
                              @RequestBody InputDto dto) {
        return inputService.updateInput(id, dto);
    }



}

