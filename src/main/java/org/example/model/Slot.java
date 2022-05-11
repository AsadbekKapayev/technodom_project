package org.example.model;

import java.sql.Date;
import java.sql.Time;

public class Slot {
    private long id;
    private int transport_id;
    private Date transport_day;
    private Time start_slot;
    private Time end_slot;

    public Slot() {}

    public Slot(long id, int transport_id, Date transport_day, Time start_slot, Time end_slot) {
        this.id = id;
        this.transport_id = transport_id;
        this.transport_day = transport_day;
        this.start_slot = start_slot;
        this.end_slot = end_slot;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTransport_id() {
        return transport_id;
    }

    public void setTransport_id(int transport_id) {
        this.transport_id = transport_id;
    }

    public Date getTransport_day() {
        return transport_day;
    }

    public void setTransport_day(Date transport_day) {
        this.transport_day = transport_day;
    }

    public Time getStart_slot() {
        return start_slot;
    }

    public void setStart_slot(Time start_slot) {
        this.start_slot = start_slot;
    }

    public Time getEnd_slot() {
        return end_slot;
    }

    public void setEnd_slot(Time end_slot) {
        this.end_slot = end_slot;
    }
}
