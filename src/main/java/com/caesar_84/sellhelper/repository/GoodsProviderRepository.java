package com.caesar_84.sellhelper.repository;

import com.caesar_84.sellhelper.domain.GoodsProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface GoodsProviderRepository extends JpaRepository<GoodsProvider, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT gp FROM GoodsProvider gp WHERE gp.id=:id AND gp.user.id=:user_id")
    GoodsProvider get(@Param("id") int id, @Param("user_id") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM GoodsProvider gp WHERE gp.id=:id AND gp.user.id=:user_id")
    int delete(@Param("id") int id, @Param("user_id") int userId);

    @Transactional(readOnly = true)
    @Query("SELECT gp FROM GoodsProvider gp WHERE gp.user.id=:user_id ORDER BY gp.name")
    List<GoodsProvider> getAll(@Param("user_id") int userId);
}
