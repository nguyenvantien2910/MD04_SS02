package com.ra.session02.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeRequest {
    private String fullname;
    private Integer page;
    private Integer perPage;
    private String sortBy;
    private String direction;
}
