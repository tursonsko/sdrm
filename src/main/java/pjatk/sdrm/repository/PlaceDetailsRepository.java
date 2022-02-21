package pjatk.sdrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.sdrm.model.entity.PlaceDetails;

@Repository
public interface PlaceDetailsRepository extends JpaRepository<PlaceDetails, Long> {

}
