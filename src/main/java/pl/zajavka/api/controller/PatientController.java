package pl.zajavka.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zajavka.api.ControllerUtils;
import pl.zajavka.api.dto.CompletedAppointmentDTO;
import pl.zajavka.api.dto.DoctorDTO;
import pl.zajavka.api.dto.PlannedAppointmentDTO;
import pl.zajavka.api.dto.mapper.CompletedAppointmentMapper;
import pl.zajavka.api.dto.mapper.DoctorMapper;
import pl.zajavka.business.CompletedAppointmentService;
import pl.zajavka.business.DoctorService;
import pl.zajavka.business.PatientService;
import pl.zajavka.domain.Patient;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(PatientController.PATIENT)
public class PatientController {

    public static final String PATIENT = "/patient";
    private final ControllerUtils controllerUtils;

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final PatientService patientService;
    private final CompletedAppointmentService completedAppointmentService;
    private final CompletedAppointmentMapper completedAppointmentMapper;

    @GetMapping("/{patientId}")
    public String homePage(@PathVariable String patientId, Model model) {
        List<DoctorDTO> doctorDTOs = doctorService.findDoctors().stream()
                .map(doctorMapper::map)
                .toList();

        List<PlannedAppointmentDTO> plannedAppointmentDTOs = controllerUtils.getPlannedAppointmentsForPatient(patientId);

        model.addAttribute("patientId", patientId);
        model.addAttribute("doctorDTOs", doctorDTOs);
        model.addAttribute("plannedAppointmentDTOs", plannedAppointmentDTOs);

        return "patient_portal";
    }

    @GetMapping("/{patientId}/history")
    public String patientHistory(@PathVariable String patientId, Model model) {
        List<CompletedAppointmentDTO> completedAppointments =
                completedAppointmentService.findCompletedAppointmentsByPatientId(patientId).stream()
                        .map(completedAppointmentMapper::map)
                        .toList();

        Patient patient = patientService.findPatientById(patientId);

        model.addAttribute("completedAppointmentDTOs", completedAppointments);
        model.addAttribute("patientName",
                String.format("%s %s", patient.getName(), patient.getSurname()));

        return "patient_history";
    }
}
