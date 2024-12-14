package com.seetizen.backend.service;

import com.seetizen.backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = """
            SELECT * ,
                   (6371 * acos(
                   cos(radians(:latitude)) * cos(radians(p.latitude)) *
                   cos(radians(p.longitude) - radians(:longitude)) + 
                   sin(radians(:latitude)) * sin(radians(p.latitude))
                )) AS distance
            FROM post p
            HAVING distance <= :radius
            ORDER BY distance
            """, nativeQuery = true)
    List<Post> findAllNearByCoordinate(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("radius") double radius);
}
