package com.Ivoyant.GlobalScheduler.controller;

import org.Ivoyant.model.Schedule;
import org.Ivoyant.service.ScheduleImpl;
import org.Ivoyant.service.ScheduleIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("gs/api")
public class ScheduleController  implements ScheduleIn {
       @Autowired
//    private final GsPlugin gsPlugin;
    private ScheduleImpl scheduleImpl;
    @PostMapping("/createSchedule")
    public ResponseEntity createSchedule(Schedule schedule) throws SQLException {

        Schedule schedule1 = scheduleImpl.create(schedule);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("Schedule has been created");
    }
    @GetMapping("/getSchedule/{id}")
    public ResponseEntity getScheduleById(int id) throws SQLException {
        Schedule schedule = scheduleImpl.getSchedule(id);
        if (schedule == null)
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("The Schedule is not found");
        return ResponseEntity.ok(schedule);
    }
    @PutMapping("/updateSchedule")
    public ResponseEntity updateScheduleById(Schedule schedule) throws SQLException {
     Schedule schedule1 = scheduleImpl.updateSchedule(schedule);
        return ResponseEntity.ok(schedule1);
    }
    @DeleteMapping("/deleteSchedule/{id}")
    public ResponseEntity deleteScheduleById(int id) {
        boolean isRemoved = scheduleImpl.deleteSchedule(id);
        if (isRemoved)
            return ResponseEntity.ok("The schedule has been removed");
        return ResponseEntity.ok("The Schedule is not Found");
    }
    @GetMapping("/getAllSchedule")
    public ResponseEntity getAllSchedule() {
        List<Schedule> scheduleList = scheduleImpl.getAllSchedules();
        if (scheduleList == null)
            return ResponseEntity.ok("No schedules");
        return ResponseEntity.ok(scheduleList);
    }
    @GetMapping("/getScheduleHistory/{id}")
    public ResponseEntity getScheduleHistory(int id) throws SQLException {
        HashMap<String, Object> scheduleList = scheduleImpl.getScheduleHistory(id);
        if (scheduleList == null || scheduleList.isEmpty())
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("The Schedule is not found");
        return ResponseEntity.ok(scheduleList);
    }
}