package pl.zajavka.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.zajavka.api.ControllerUtils;
import pl.zajavka.api.dto.*;
import pl.zajavka.api.dto.mapper.DoctorAvailabilityMapper;
import pl.zajavka.api.dto.mapper.DoctorMapper;
import pl.zajavka.api.dto.mapper.PatientMapper;
import pl.zajavka.api.dto.mapper.PlannedAppointmentMapper;
import pl.zajavka.business.*;
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
    private final ControllerUtils controllerUtils;

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final PatientService patientService;
    private final PatientMapper patientMapper;
    private final PlannedAppointmentService plannedAppointmentService;
    private final PlannedAppointmentMapper plannedAppointmentMapper;
    private final CompletedAppointmentService completedAppointmentService;
    private final DoctorAvailabilityService doctorAvailabilityService;
    private final DoctorAvailabilityMapper doctorAvailabilityMapper;

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
            @Valid @ModelAttribute("newAppointmentDTO") NewAppointmentDTO dto,
            Model model
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

        List<DoctorAvailabilityDTO> doctorAvailabilityDTOs =
                doctorAvailabilityService.findByDoctorId(doctor.getDoctorId().toString()).stream()
                        .map(doctorAvailabilityMapper::map)
                        .toList();

        model.addAttribute("doctorAvailabilityDTOs", doctorAvailabilityDTOs);

//        return String.format("redirect:/patient/%s", patientId);
        return "appointment_new";
    }

    @GetMapping("/cancel/patient/{patientId}")
    public ModelAndView cancelAppointmentByPatient(@PathVariable String patientId) {
        List<PlannedAppointmentDTO> plannedAppointmentDTOs = controllerUtils.getPlannedAppointmentsForPatient(patientId);

        Map<String, ?> data = Map.of(
                "patientId", patientId,
                "plannedAppointmentDTOs", plannedAppointmentDTOs
        );
        return new ModelAndView("appointment_cancel_patient", data);
    }

    @DeleteMapping("/cancel/{plannedAppointmentId}/patient/{patientId}")
    @Transactional
    public String cancelAppointmentByPatient(@PathVariable String patientId,
            @PathVariable String plannedAppointmentId
    ) {
        plannedAppointmentService.cancelAppointment(plannedAppointmentId);
        return String.format("redirect:/patient/%s", patientId);
    }

    @GetMapping("/cancel/doctor/{doctorId}")
    public ModelAndView cancelAppointmentByDoctor(@PathVariable String doctorId) {
        List<PlannedAppointmentDTO> plannedAppointmentDTOs = controllerUtils.getPlannedAppointmentsForDoctor(doctorId);

        Map<String, ?> data = Map.of(
                "doctorId", doctorId,
                "plannedAppointmentDTOs", plannedAppointmentDTOs
        );
        return new ModelAndView("appointment_cancel_doctor", data);
    }

    @DeleteMapping("/cancel/{plannedAppointmentId}/doctor/{doctorId}")
    @Transactional
    public String cancelAppointmentByDoctor(@PathVariable String doctorId,
            @PathVariable String plannedAppointmentId
    ) {
        plannedAppointmentService.cancelAppointment(plannedAppointmentId);
        return String.format("redirect:/doctor/%s", doctorId);
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
