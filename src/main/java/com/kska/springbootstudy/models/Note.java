package com.kska.springbootstudy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
public class Note implements Serializable {
    private String id;
    private String name;
    private String description;
}
