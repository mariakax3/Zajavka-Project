package pl.zajavka.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zajavka.api.ControllerUtils;
import pl.zajavka.api.dto.CompletedAppointmentDTO;
import pl.zajavka.api.dto.DoctorAvailabilityDTO;
import pl.zajavka.api.dto.PlannedAppointmentDTO;
import pl.zajavka.api.dto.mapper.CompletedAppointmentMapper;
import pl.zajavka.api.dto.mapper.DoctorAvailabilityMapper;
import pl.zajavka.business.CompletedAppointmentService;
import pl.zajavka.business.DoctorAvailabilityService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(DoctorController.DOCTOR)
public class DoctorController {

    public static final String DOCTOR = "/doctor";
    private final ControllerUtils controllerUtils;

    private final CompletedAppointmentService completedAppointmentService;
    private final CompletedAppointmentMapper completedAppointmentMapper;
    private final DoctorAvailabilityService doctorAvailabilityService;
    private final DoctorAvailabilityMapper doctorAvailabilityMapper;

    @GetMapping("/{doctorId}")
    public String homePage(@PathVariable String doctorId, Model model) {
        List<PlannedAppointmentDTO> plannedAppointmentDTOs = controllerUtils.getPlannedAppointmentsForDoctor(doctorId);

        model.addAttribute("doctorId", doctorId);
        model.addAttribute("plannedAppointmentDTOs", plannedAppointmentDTOs);

        return "doctor_portal";
    }

    @GetMapping("/{doctorId}/history")
    public String doctorHistory(@PathVariable String doctorId, Model model) {
        List<CompletedAppointmentDTO> completedAppointments =
                completedAppointmentService.findCompletedAppointmentsByDoctorId(doctorId).stream()
                        .map(completedAppointmentMapper::map)
                        .toList();

        model.addAttribute("doctorId", doctorId);
        model.addAttribute("completedAppointmentDTOs", completedAppointments);

        return "doctor_history";
    }

    @GetMapping("/{doctorId}/calendar")
    public String doctorCalendar(@PathVariable String doctorId, Model model) {
        List<String> doctorAvailabilityDTOs =
                doctorAvailabilityService.findByDoctorId(doctorId).stream()
                        .map(doctorAvailabilityMapper::map)
                        .map(DoctorAvailabilityDTO::toString)
                        .toList();

        model.addAttribute("doctorAvailabilityDTOs", doctorAvailabilityDTOs);

        return "doctor_calendar";
    }

    @GetMapping("/{doctorId}/calendar/update")
    public String doctorCalendarUpdate(@PathVariable String doctorId, Model model) {
        List<DoctorAvailabilityDTO> doctorAvailabilityDTOs =
                doctorAvailabilityService.findByDoctorId(doctorId).stream()
                        .map(doctorAvailabilityMapper::map)
                        .toList();

        List<DoctorAvailabilityDTO> thisMonth = controllerUtils.getAvailableDatesForMonth(LocalDate.now().getMonthValue());
        List<DoctorAvailabilityDTO> nextMonth = controllerUtils.getAvailableDatesForMonth(LocalDate.now().getMonthValue() + 1);

        thisMonth.stream()
                .filter(doctorAvailabilityDTOs::contains)
                .forEach(dto -> dto.setChecked(true));
        nextMonth.stream()
                .filter(doctorAvailabilityDTOs::contains)
                .forEach(dto -> dto.setChecked(true));

        log.info("### THIS MONTH SIZE: {}", thisMonth.size());
        log.info("### NEXT MONTH SIZE: {}", nextMonth.size());

        int numberOfAppointmentsToAdd = 30;
        List<DoctorAvailabilityDTO> availability = new ArrayList<>();
        for (int i = 0; i < numberOfAppointmentsToAdd; i++) {
            availability.add(new DoctorAvailabilityDTO());
        }
        log.info("### AVAILABILITY SIZE: {}", availability.size());

        model.addAttribute("thisMonth", thisMonth);
        model.addAttribute("nextMonth", nextMonth);
        model.addAttribute("availability", availability);

        return "doctor_calendar_update";
    }

    @PostMapping("/{doctorId}/calendar/update")
    public String doctorCalendarUpdate(
            @PathVariable String doctorId,
            @ModelAttribute ArrayList<DoctorAvailabilityDTO> availability
    ) {
        log.info("### AVAILABILITY SIZE: {}", availability.size());

        return String.format("redirect:/doctor/%s/calendar", doctorId);
    }
}
