package com.aet.api.model;

import javax.persistence.*;

@Entity
@Table(name = "veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veiculo_id")
    private Integer id;

    @Column(name = "veiculo_placa", length = 40)
    private String veiculoPlaca;
}

