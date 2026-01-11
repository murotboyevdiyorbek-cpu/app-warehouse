package uz.diyor.appwarehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Measurement;
import uz.diyor.appwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    final MeasurementRepository measurementRepository;


    public Result addMeasurement(Measurement measurement) {
        final boolean exist = measurementRepository.existsByName(measurement.getName());
        if (exist) {
            return new Result("Already exists", false);
        }
        measurementRepository.save(measurement);
        return new Result("Successfully added measurement",true);
    }


    public List<Measurement> getAllMeasurement() {
        return measurementRepository.findAll();
    }

    public Measurement getByIdMeasurement(Integer id) {
        return measurementRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Measurement not found with id: " + id)
                );
    }

    public Result deleteIdMeasurement(Integer id) {
        if(!measurementRepository.existsById(id)) {
            new Result("Measurement not found with id: " + id, false);
        }
        measurementRepository.deleteById(id);
        return new Result("Measurement deleted successfully",true);

    }

    public Result updateMeasurement(Integer id, Measurement dto) {
        Optional<Measurement> optional = measurementRepository.findById(id);
        if(optional.isEmpty()) {
            new Result("Measurement not found with id: " + id, false);
        }

        Measurement measurement = optional.get();
        measurement.setName(dto.getName());
        measurement.setActive(dto.isActive());
        measurementRepository.save(measurement);
        return new Result("Measurement updated successfully",true);
    }
}
