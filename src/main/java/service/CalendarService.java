package service;

import model.rules.WorkingDayType;

import java.util.Date;

public interface CalendarService {

    public WorkingDayType typeOfDay(Date date);
}
