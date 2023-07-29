package pl.zajavka.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zajavka.api.dto.DoctorDTO;
import pl.zajavka.api.dto.mapper.DoctorMapper;
import pl.zajavka.business.DoctorService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(PatientController.PATIENT)
public class PatientController {

    public static final String PATIENT = "/patient";

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    @GetMapping
    public String homePage(Model model) {
        List<DoctorDTO> doctors = doctorService.findDoctors().stream()
                .map(doctorMapper::map)
                .toList();

        model.addAttribute("doctorDTOs", doctors);

        return "patient_portal";
    }

    @GetMapping("/history/{patientId}")
    public String patientHistory(@PathVariable String patientId) {

        return "patient_history";
    }
}
