package pl.zajavka.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zajavka.api.dto.PlannedAppointmentDTO;
import pl.zajavka.api.dto.mapper.PlannedAppointmentMapper;
import pl.zajavka.business.PlannedAppointmentService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(DoctorController.DOCTOR)
public class DoctorController {

    public static final String DOCTOR = "/doctor";

    private final PlannedAppointmentService plannedAppointmentService;
    private final PlannedAppointmentMapper plannedAppointmentMapper;

    @GetMapping
    public String homePage(Model model) {
        List<PlannedAppointmentDTO> plannedAppointments =
                plannedAppointmentService.findPlannedAppointments().stream()
                        .map(plannedAppointmentMapper::map)
                        .toList();

        model.addAttribute("plannedAppointmentDTOs", plannedAppointments);

        return "doctor_portal";
    }
}
