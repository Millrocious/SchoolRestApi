package com.endropioz.schoolrestapp.auth.event;

import com.endropioz.schoolrestapp.auth.dto.auth.AuthEmailRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    String appUrl;
    Locale locale;
    AuthEmailRequestDto userDto;

    public OnRegistrationCompleteEvent(AuthEmailRequestDto userDto, Locale locale, String appUrl) {
        super(userDto);
        this.userDto = userDto;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
