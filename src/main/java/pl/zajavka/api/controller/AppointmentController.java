package pl.zajavka.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.zajavka.api.dto.*;
import pl.zajavka.api.dto.mapper.DoctorMapper;
import pl.zajavka.api.dto.mapper.PatientMapper;
import pl.zajavka.api.dto.mapper.PlannedAppointmentMapper;
import pl.zajavka.business.CompletedAppointmentService;
import pl.zajavka.business.DoctorService;
import pl.zajavka.business.PatientService;
import pl.zajavka.business.PlannedAppointmentService;
import pl.zajavka.domain.Doctor;
import pl.zajavka.domain.PlannedAppointment;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(AppointmentController.APPOINTMENT)
public class AppointmentController {

    public static final String APPOINTMENT = "/appointment";

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final PatientService patientService;
    private final PatientMapper patientMapper;
    private final PlannedAppointmentService plannedAppointmentService;
    private final PlannedAppointmentMapper plannedAppointmentMapper;
    private final CompletedAppointmentService completedAppointmentService;

    @GetMapping("/details/{plannedAppointmentId}")
    public ModelAndView appointmentDetails(@PathVariable String plannedAppointmentId) {
        Map<String, ?> data = prepareDataForAppointmentDetails(plannedAppointmentId);
        return new ModelAndView("appointment_details", data);
    }

    @PostMapping("/details/{plannedAppointmentId}")
    @Transactional
    public String addAppointmentDetails(
            @PathVariable String plannedAppointmentId,
            @Valid @ModelAttribute("completedAppointmentDTO") CompletedAppointmentDTO dto
    ) {
        PlannedAppointment plannedAppointment =
                plannedAppointmentService.findPlannedAppointmentById(plannedAppointmentId);
        Integer doctorId = plannedAppointment.getDoctor().getDoctorId();

        dto.setPlannedAppointment(plannedAppointmentMapper.map(plannedAppointment));
        completedAppointmentService.saveDTO(dto);

        return String.format("redirect:/doctor/%s/history", doctorId);
    }

    @GetMapping("/new/{patientId}")
    public ModelAndView makeAppointment(@PathVariable String patientId) {
        Map<String, ?> data = prepareDataForNewAppointment(patientId);
        return new ModelAndView("appointment_new", data);
    }

    @PostMapping("/new/{patientId}")
    @Transactional
    public String makeAppointment(@PathVariable String patientId,
            @Valid @ModelAttribute("newAppointmentDTO") NewAppointmentDTO dto
    ) {
        Doctor doctor = doctorService.findByPesel(dto.getDoctorPesel());
        DoctorDTO doctorDTO = doctorMapper.map(doctor);
        PatientDTO patientDTO = patientMapper.map(patientService.findPatientById(patientId));
        PlannedAppointmentDTO plannedAppointmentDTO = PlannedAppointmentDTO.builder()
                .patientComment(dto.getPatientComment())
                .dateTime(dto.getDateTime())
                .patient(patientDTO)
                .doctor(doctorDTO)
                .build();
        plannedAppointmentService.saveDTO(plannedAppointmentDTO);

        return String.format("redirect:/patient/%s", patientId);
    }

    private Map<String, ?> prepareDataForAppointmentDetails(String plannedAppointmentId) {
        PlannedAppointment plannedAppointment = plannedAppointmentService.findPlannedAppointmentById(plannedAppointmentId);
        PlannedAppointmentDTO plannedAppointmentDTO = plannedAppointmentMapper.map(plannedAppointment);

        return Map.of(
                "plannedAppointmentDTO", plannedAppointmentDTO,
                "completedAppointmentDTO", CompletedAppointmentDTO.buildDefault()
        );
    }

    private Map<String, ?> prepareDataForNewAppointment(String patientId) {
        List<String> doctorPesels = doctorService.findDoctors().stream()
                .map(Doctor::getPesel)
                .toList();

        return Map.of(
                "patientId", patientId,
                "doctorPesels", doctorPesels,
                "newAppointmentDTO", NewAppointmentDTO.buildDefault()
        );
    }
}
