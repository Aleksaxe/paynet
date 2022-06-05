package uz.paynet.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.paynet.test.model.Building;


import java.util.Collection;
import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    @Query("SELECT building from Building as building where building.plumber.Id =:plumberId")
    Collection<Building> getBuildingsByPlumberId(long plumberId);
    @Modifying
    @Transactional
    @Query("update Building as b set b.plumber = null where b.plumber.Id =:plumberId")
    void removeFromAllBuildings(@Param("plumberId") long plumberId);
}
