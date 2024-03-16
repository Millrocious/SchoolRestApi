package com.endropioz.schoolrestapp.attendance.event;

import com.endropioz.schoolrestapp.attendance.entity.Attendance;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttendanceEvent extends ApplicationEvent {
    Attendance attendance;

    public AttendanceEvent(Object source, Attendance attendance) {
        super(source);
        this.attendance = attendance;
    }
}
