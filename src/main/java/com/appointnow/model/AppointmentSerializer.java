package com.appointnow.model;

import com.appointnow.entity.Appointment;
import com.appointnow.entity.AppointmentStatus;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.ZoneOffset;

public class AppointmentSerializer extends StdSerializer<Appointment> {

    public AppointmentSerializer() {
        this(null);
    }

    public AppointmentSerializer(Class<Appointment> t) {
        super(t);
    }

    @Override
    public void serialize(Appointment appointment, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("id", appointment.getId());
        generator.writeStringField("title", appointment.getWork().getName());
        generator.writeNumberField("start", appointment.getStart().toInstant(ZoneOffset.UTC).toEpochMilli());
        generator.writeNumberField("end", appointment.getEnd().toInstant(ZoneOffset.UTC).toEpochMilli());
        generator.writeStringField("url", "/appointments/" + appointment.getId());
        generator.writeStringField("color",appointment.getStatus().equals(AppointmentStatus.SCHEDULED) ? "#28a745" : "grey");
        generator.writeEndObject();
    }
}
