package pjatk.sdrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pjatk.sdrm.model.entity.SpaceCoordinates;

public interface SpaceCoordinatesRepository extends JpaRepository<SpaceCoordinates, Long> {
}
