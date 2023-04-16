package com.appointnow.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.time.LocalTime;
import java.util.Objects;

@Getter
@Setter
@ToString
public class TimePeriod implements Comparable<TimePeriod> {

    private LocalTime start;
    private LocalTime end;

    public TimePeriod() {
    }

    public TimePeriod(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(@NotNull TimePeriod o) {
        return this.getStart().compareTo(o.getStart());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        var timePeriod = (TimePeriod) obj;
        return this.start.equals(timePeriod.getStart()) && this.end.equals(timePeriod.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
