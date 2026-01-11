package uz.diyor.appwarehouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.diyor.appwarehouse.entity.template.AbsEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Supplier extends AbsEntity {

    @Column(unique = true, nullable = false)
    private String phoneNumber;


}

