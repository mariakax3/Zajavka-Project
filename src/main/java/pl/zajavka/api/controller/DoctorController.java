package pl.zajavka.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zajavka.api.ControllerUtils;
import pl.zajavka.api.dto.CompletedAppointmentDTO;
import pl.zajavka.api.dto.DoctorAvailabilityDTO;
import pl.zajavka.api.dto.PlannedAppointmentDTO;
import pl.zajavka.api.dto.mapper.CompletedAppointmentMapper;
import pl.zajavka.api.dto.mapper.DoctorAvailabilityMapper;
import pl.zajavka.business.CompletedAppointmentService;
import pl.zajavka.business.DoctorAvailabilityService;

import java.time.LocalDate;
import java.util.List;

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
        List<String> doctorAvailabilityDTOs =
                doctorAvailabilityService.findByDoctorId(doctorId).stream()
                        .map(doctorAvailabilityMapper::map)
                        .map(DoctorAvailabilityDTO::toString)
                        .toList();

        List<DoctorAvailabilityDTO> thisMonth = controllerUtils.getAvailableDatesForMonth(LocalDate.now().getMonthValue());
        List<DoctorAvailabilityDTO> nextMonth = controllerUtils.getAvailableDatesForMonth(LocalDate.now().getMonthValue()+1);

        model.addAttribute("thisMonth", thisMonth);
        model.addAttribute("nextMonth", nextMonth);
        model.addAttribute("doctorAvailabilityDTOs", doctorAvailabilityDTOs);

        return "doctor_calendar_update";
    }
}
