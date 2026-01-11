package uz.diyor.appwarehouse.controllrer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Measurement;
import uz.diyor.appwarehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/measurement")
public class MeasurementController {


    final MeasurementService measurementService;

    @PostMapping
    public Result addMeasurement(@RequestBody Measurement measurement) {
        return measurementService.addMeasurement(measurement);
    }

    @GetMapping
    public List<Measurement> getAllMeasurements() {
        return measurementService.getAllMeasurement();
    }

    @GetMapping("/{id}")
    public Measurement getMeasurementById(@RequestParam Integer id) {
        return measurementService.getByIdMeasurement(id);
    }

    @DeleteMapping("/{id}")
    public Result deleteMeasurementById(@RequestParam Integer id) {
       return measurementService.deleteIdMeasurement(id);
    }

    @PutMapping("/{id}")
    public Result updateMeasurement(
            @PathVariable Integer id,
            @RequestBody Measurement dto
    ) {
      return   measurementService.updateMeasurement(id, dto);
    }



}
