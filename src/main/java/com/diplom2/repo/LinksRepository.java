package com.diplom2.repo;

import com.diplom2.models.Links;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LinksRepository extends JpaRepository<Links, Long> {

    @Query("select b from Links b where b.short_link = :link_short")
    Links findByShort_link(@Param("link_short") String short_link);

}
