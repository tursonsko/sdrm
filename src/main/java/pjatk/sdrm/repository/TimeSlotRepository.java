package pjatk.sdrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pjatk.sdrm.model.entity.TimeSlot;
import pjatk.sdrm.model.entity.UserData;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

     Optional<TimeSlot> findById(Long id);
     List<TimeSlot> findTimeSlotByUserDataAndIsBooked(UserData userData, boolean booked);
     List<TimeSlot> findTimeSlotByUserDataAndIsBookedAndBookingCancelTimeIsNotNull(UserData userData, boolean booked);
}
