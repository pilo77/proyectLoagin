package com.example.loginback.reminder.service;

import com.example.loginback.reminder.entity.Reminder;

import java.util.List;
import java.util.Optional;

public interface ReminderService {
    List<Reminder> findAll();

    Optional<Reminder> findById(Long id);

    Reminder save(Reminder reminder);

    void update(Reminder reminder, Long id);

    void delete(Long id);


}