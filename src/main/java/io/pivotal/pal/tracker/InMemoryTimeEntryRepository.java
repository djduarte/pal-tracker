package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private List<TimeEntry> timeEntryList;
    private long lastId;

    public InMemoryTimeEntryRepository() {

        this.lastId = 1;
        this.timeEntryList = new ArrayList<>();
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        TimeEntry newTimeEntry = new TimeEntry(timeEntry, this.lastId);
        timeEntryList.add(newTimeEntry);
        this.lastId++;
        return newTimeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {

       for (TimeEntry t : timeEntryList) {
            if (t.getId() == timeEntryId) {
                return t;
            }
        }

        return null;

    }

    @Override
    public List<TimeEntry> list() {
        return this.timeEntryList;
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {

        TimeEntry result = this.find(timeEntryId);

        if (result != null) {
            this.delete(timeEntryId);
            TimeEntry newToEntry = new TimeEntry(timeEntry,timeEntryId);
            this.timeEntryList.add(newToEntry);

            return newToEntry;
        }

        return null;
    }

    @Override
    public void delete(long timeEntryId) {

        TimeEntry result = this.find(timeEntryId);

        if (result != null) {
            this.timeEntryList.remove(result);
        }

    }
}
