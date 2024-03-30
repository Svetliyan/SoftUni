package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Volcano;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface VolcanoRepository extends JpaRepository<Volcano, Long> {

    Optional<Volcano> findByName(String name);

//    @Query("select v.name, c.name, v.elevation, v.last_eruption from volcanoes as v\n" +
//            "join countries  as c on v.country_id = c.id\n" +
//            "         where v.elevation > 3000\n")
    @Query("SELECT v FROM Volcano v WHERE v.elevation >= 3000 and v.isActive = true and v.lastEruption != null ORDER BY v.elevation DESC")
    Set<Volcano> findByAboveElevation();
}
