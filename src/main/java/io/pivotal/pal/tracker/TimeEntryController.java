package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        return new ResponseEntity<TimeEntry>(this.timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {

        TimeEntry timeEntry = this.timeEntryRepository.find(id);

        if (timeEntry == null) {
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<List<TimeEntry>>(this.timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry expected) {

        TimeEntry timeEntry = this.timeEntryRepository.update(id, expected);

        if (timeEntry == null) {
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        this.timeEntryRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
