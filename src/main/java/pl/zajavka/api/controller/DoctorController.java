package pl.zajavka.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zajavka.api.dto.CompletedAppointmentDTO;
import pl.zajavka.api.dto.PlannedAppointmentDTO;
import pl.zajavka.api.dto.mapper.CompletedAppointmentMapper;
import pl.zajavka.api.dto.mapper.PlannedAppointmentMapper;
import pl.zajavka.business.CompletedAppointmentService;
import pl.zajavka.business.PlannedAppointmentService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(DoctorController.DOCTOR)
public class DoctorController {

    public static final String DOCTOR = "/doctor";

    private final PlannedAppointmentService plannedAppointmentService;
    private final PlannedAppointmentMapper plannedAppointmentMapper;
    private final CompletedAppointmentService completedAppointmentService;
    private final CompletedAppointmentMapper completedAppointmentMapper;

    @GetMapping("/{doctorId}")
    public String homePage(@PathVariable String doctorId, Model model) {
        List<PlannedAppointmentDTO> plannedAppointments =
                plannedAppointmentService.findPlannedAppointmentsForDoctor(doctorId).stream()
                        .map(plannedAppointmentMapper::map)
                        .toList();

        model.addAttribute("plannedAppointmentDTOs", plannedAppointments);

        return "doctor_portal";
    }

    @GetMapping("/{doctorId}/history")
    public String doctorHistory(@PathVariable String doctorId, Model model) {
        List<CompletedAppointmentDTO> completedAppointments =
                completedAppointmentService.findCompletedAppointmentsByDoctorId(doctorId).stream()
                        .map(completedAppointmentMapper::map)
                        .toList();

        model.addAttribute("completedAppointmentDTOs", completedAppointments);

        return "doctor_history";
    }
}
