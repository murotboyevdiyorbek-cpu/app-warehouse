package uz.diyor.appwarehouse.controllrer;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.diyor.appwarehouse.dto.OutputDto;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Output;
import uz.diyor.appwarehouse.service.OutputService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/output")
public class OutputController {

    final OutputService outputService;


    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto) {
        return outputService.addOutput(outputDto);
    }


    @GetMapping
    public List<Output> getOutputs() {
        return outputService.getAllOutput();
    }

    @DeleteMapping("/{id}")
    public Result deleteOutput(@PathVariable Integer id) {
        return outputService.deleteByIdOutput(id);
    }

    @PutMapping("id")
    public Result updateOutput(@PathVariable @RequestBody OutputDto outputDto,
                               Integer id) {
        return outputService.updateOutput(outputDto,id);
    }


}
