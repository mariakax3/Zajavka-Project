package pl.zajavka.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.zajavka.api.dto.DoctorDTO;
import pl.zajavka.api.dto.mapper.DoctorMapper;
import pl.zajavka.business.DoctorService;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private static final String PATIENT = "/patient";

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    @GetMapping(PATIENT)
    public String homePage(Model model) {
        List<DoctorDTO> doctors = doctorService.findDoctors().stream()
                .map(doctorMapper::map)
                .toList();

        model.addAttribute("doctorDTOs", doctors);

        return "patient_portal";
    }
}
