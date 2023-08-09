package pl.zajavka.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;
import pl.zajavka.api.dto.CompletedAppointmentDTO;
import pl.zajavka.api.dto.DoctorAvailabilityDTO;
import pl.zajavka.api.dto.PlannedAppointmentDTO;
import pl.zajavka.api.dto.mapper.CompletedAppointmentMapper;
import pl.zajavka.api.dto.mapper.PlannedAppointmentMapper;
import pl.zajavka.business.CompletedAppointmentService;
import pl.zajavka.business.PlannedAppointmentService;

import java.sql.Time;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ControllerUtils {

    private final CompletedAppointmentService completedAppointmentService;
    private final CompletedAppointmentMapper completedAppointmentMapper;
    private final PlannedAppointmentService plannedAppointmentService;
    private final PlannedAppointmentMapper plannedAppointmentMapper;

    public List<PlannedAppointmentDTO> getPlannedAppointmentsForPatient(String patientId) {
        List<PlannedAppointmentDTO> completedAppointmentIds =
                completedAppointmentService.findCompletedAppointmentsByPatientId(patientId).stream()
                        .map(completedAppointmentMapper::map)
                        .map(CompletedAppointmentDTO::getPlannedAppointment)
                        .toList();

        return plannedAppointmentService.findPlannedAppointmentsForPatient(patientId).stream()
                .map(plannedAppointmentMapper::map)
                .filter(appointment -> !completedAppointmentIds.contains(appointment))
                .toList();
    }

    public List<PlannedAppointmentDTO> getPlannedAppointmentsForDoctor(String doctorId) {
        List<PlannedAppointmentDTO> completedAppointmentIds =
                completedAppointmentService.findCompletedAppointmentsByDoctorId(doctorId).stream()
                        .map(completedAppointmentMapper::map)
                        .map(CompletedAppointmentDTO::getPlannedAppointment)
                        .toList();

        return plannedAppointmentService.findPlannedAppointmentsForDoctor(doctorId).stream()
                .map(plannedAppointmentMapper::map)
                .filter(appointment -> !completedAppointmentIds.contains(appointment))
                .toList();
    }

    public List<DoctorAvailabilityDTO> getAvailableDatesForMonth(Integer month) {
        List<DoctorAvailabilityDTO> availableDates = new ArrayList<>();
        LocalDate today = LocalDate.now();

        YearMonth ym = YearMonth.of(today.getYear(), month);
        LocalDate beginWith = ym.atDay(today.getMonthValue() != month ? 1 : today.getDayOfMonth());
        LocalDate endWith = ym.plusMonths(1).atDay(1);
        beginWith.datesUntil(endWith).forEach(date ->
                availableDates.add(DoctorAvailabilityDTO.builder()
                .day(date.getDayOfMonth())
                .month(date.getMonthValue())
                .year(date.getYear())
                .build()));

        return setHours(availableDates);
    }

    private List<DoctorAvailabilityDTO> setHours(List<DoctorAvailabilityDTO> availableDates) {
        List<DoctorAvailabilityDTO> datesAndTimes = new ArrayList<>();
        Time endTime = Time.valueOf("15:30:00");

        for (DoctorAvailabilityDTO date : availableDates) {
            Time time = Time.valueOf("10:00:00");
            while (time.before(endTime)) {
                DoctorAvailabilityDTO newDate = DoctorAvailabilityDTO.copyDTO(date);
                newDate.setHour(time);
                datesAndTimes.add(newDate);
                time = Time.valueOf(DateUtils.addMinutes(time, 30)
                        .toString().split(" ")[3]);
            }
        }

        return datesAndTimes;
    }
}
