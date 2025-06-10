package com.portfolio.community.resource.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource,ResourceId> {
}
