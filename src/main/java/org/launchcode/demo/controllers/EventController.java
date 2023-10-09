package org.launchcode.demo.controllers;

import jakarta.validation.Valid;
import org.launchcode.demo.data.EventData;
import org.launchcode.demo.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {


    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        EventData.add(newEvent);
        return "redirect:/events";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";

    }



    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds){
        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:/events";
    }

    @GetMapping("edit/{eventID}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
       Event editing = EventData.getById(eventId);
       model.addAttribute("event" ,editing);
       String title= "Edit Event" + editing.getName() + " (id=" + editing.getId() + ")";
       model.addAttribute("title", title);
       return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description) {
        Event editing = EventData.getById(eventId);
        editing.setName(name);
        editing.setDescription(description);
        return "redirect:/events";
    }
}