package pl.zajavka.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.zajavka.api.dto.CompletedAppointmentDTO;
import pl.zajavka.api.dto.PlannedAppointmentDTO;
import pl.zajavka.api.dto.mapper.PlannedAppointmentMapper;
import pl.zajavka.business.CompletedAppointmentService;
import pl.zajavka.business.PlannedAppointmentService;
import pl.zajavka.domain.PlannedAppointment;

import java.util.Map;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(AppointmentController.APPOINTMENT)
public class AppointmentController {

    public static final String APPOINTMENT = "/appointment";

    private final PlannedAppointmentService plannedAppointmentService;
    private final PlannedAppointmentMapper plannedAppointmentMapper;
    private final CompletedAppointmentService completedAppointmentService;

    @GetMapping("/details/{plannedAppointmentId}")
    public ModelAndView appointmentDetails(@PathVariable String plannedAppointmentId) {
        Map<String, ?> data = prepareNecessaryData(plannedAppointmentId);
        return new ModelAndView("appointment_details", data);
    }

    @PostMapping("/details/{plannedAppointmentId}")
    public String addAppointmentDetails(
            @PathVariable String plannedAppointmentId,
            @ModelAttribute("completedAppointmentDTO") CompletedAppointmentDTO dto,
            ModelMap modelMap
    ) {
        PlannedAppointment plannedAppointment =
                plannedAppointmentService.findPlannedAppointmentById(plannedAppointmentId);
        Integer doctorId = plannedAppointment.getDoctor().getDoctorId();

        dto.setPlannedAppointment(plannedAppointmentMapper.map(plannedAppointment));
        completedAppointmentService.saveDTO(dto);

        return String.format("redirect:/doctor/%s/history", doctorId);
    }

    private Map<String, ?> prepareNecessaryData(String plannedAppointmentId) {
        PlannedAppointment plannedAppointment = plannedAppointmentService.findPlannedAppointmentById(plannedAppointmentId);
        PlannedAppointmentDTO plannedAppointmentDTO = plannedAppointmentMapper.map(plannedAppointment);

        return Map.of(
                "plannedAppointmentDTO", plannedAppointmentDTO,
                "completedAppointmentDTO", CompletedAppointmentDTO.buildDefault()
        );
    }


}
