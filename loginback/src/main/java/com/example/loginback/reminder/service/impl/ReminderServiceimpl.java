package com.example.loginback.reminder.service.impl;

import com.example.loginback.reminder.entity.Reminder;
import com.example.loginback.reminder.repository.ReminderRepository;
import com.example.loginback.reminder.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReminderServiceimpl implements ReminderService {

    //Conectar con los datos - IRepository
    // Inyección de dependencia
    @Autowired
    private ReminderRepository reminderRepository;

    @Override
    public List<Reminder> findAll() {
        return reminderRepository.findAll();
    }

    @Override
    public Optional<Reminder>  findById(Long id) {
        return reminderRepository.findById(id);

    }

    @Override
    public Reminder save(Reminder reminder) {
        //Obtener el objeto reminder y el id
        //Verificar con el id, si exiten los datos
        return reminderRepository.save(reminder);
    }

    @Override
    public void update(Reminder reminder, Long id) {

        //Reminder r = remimderRepository.findById(id).orElse(null);
        Optional<Reminder> r = reminderRepository.findById(id);
        if (!r.isEmpty()) {
            Reminder reminderUpdate=r.get();
            reminderUpdate.setTitle(reminder.getTitle());
            reminderUpdate.setDescription(reminder.getDescription());
            reminderUpdate.setFecha(reminder.getFecha());
            reminderUpdate.setHora(reminder.getHora());
            //Actualizar Objeto del recordatorio
            reminderRepository.save(reminderUpdate);
        }else{
            System.out.println("No existe el miembro");
        }

    }

    @Override
    public void delete(Long id) {
        reminderRepository.deleteById(id);

    }
}