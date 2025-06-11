package com.portfolio.community.resource.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface ResourceRepository extends JpaRepository<Resource,ResourceId> {

    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query("update Resource re set re.state=:state where re.id in :ids")
    int bulkUpdateResourceState(@Param("state")ResourceState state,@Param("ids") List<ResourceId> ids);
}
