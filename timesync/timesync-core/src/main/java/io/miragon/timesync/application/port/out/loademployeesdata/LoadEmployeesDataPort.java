package io.miragon.timesync.application.port.out.loademployeesdata;

import io.miragon.timesync.domain.EmployeeData;

import java.util.List;

public interface LoadEmployeesDataPort {

    List<EmployeeData> loadEmployeesData();
}