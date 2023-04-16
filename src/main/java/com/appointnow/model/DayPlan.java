package com.appointnow.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class DayPlan {

    private TimePeriod workingHours;
    private List<TimePeriod> breaks;

    public DayPlan() {
        breaks = new ArrayList<>();
    }

    public DayPlan(TimePeriod workingHours) {
        this.workingHours = workingHours;
        this.breaks = new ArrayList();
    }

    public List<TimePeriod> getTimePeriodsWithBreaksExcluded() {
        ArrayList<TimePeriod> timePeriodsWithBreaksExcluded = new ArrayList<>();
        timePeriodsWithBreaksExcluded.add(getWorkingHours());
        List<TimePeriod> breaks = getBreaks();

        if (!breaks.isEmpty()) {
            ArrayList<TimePeriod> toAdd = new ArrayList<>();
            for (TimePeriod aBreak : breaks) {
                if (aBreak.getStart().isBefore(workingHours.getStart()))
                    aBreak.setStart(workingHours.getStart());
                if (aBreak.getEnd().isAfter(workingHours.getEnd()))
                    aBreak.setEnd(workingHours.getEnd());

                for (TimePeriod period : timePeriodsWithBreaksExcluded) {
                    if (aBreak.getStart().equals(period.getStart()) && aBreak.getEnd().isAfter(period.getStart()) && aBreak.getEnd().isBefore(period.getEnd()))
                        period.setStart(aBreak.getEnd());
                    if (aBreak.getStart().isAfter(period.getStart()) && period.getStart().isBefore(period.getEnd()) && aBreak.getEnd().equals(period.getEnd()))
                        period.setEnd(aBreak.getStart());
                    if (aBreak.getStart().isAfter(period.getStart()) && aBreak.getEnd().isBefore(period.getEnd())) {
                        toAdd.add(new TimePeriod(period.getStart(), aBreak.getStart()));
                        period.setStart(aBreak.getEnd());
                    }
                }
            }
            timePeriodsWithBreaksExcluded.addAll(toAdd);
            Collections.sort(timePeriodsWithBreaksExcluded);
        }
        return timePeriodsWithBreaksExcluded;
    }
}
