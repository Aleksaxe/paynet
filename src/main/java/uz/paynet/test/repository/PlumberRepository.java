package uz.paynet.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.paynet.test.model.Plumber;

@Repository
public interface PlumberRepository extends JpaRepository<Plumber, Long> {
    @Query("select building.plumber from Building as building where building.Id =:buildingId")
    Plumber getPlumberByBuildingId(@Param("buildingId")long buildingId);
    @Modifying
    @Transactional
    @Query("update Plumber as p set p.active = false where p.Id = :plumberId")
    void fire(@Param("plumberId") long plumberId);
}
