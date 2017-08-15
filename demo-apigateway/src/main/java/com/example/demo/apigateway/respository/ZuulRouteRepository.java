package com.example.demo.apigateway.respository;

import com.example.demo.apigateway.entity.ZuulRouteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ZuulRouteRepository extends JpaRepository<ZuulRouteEntity, Long> {
    @Query(value = "select route from ZuulRouteEntity route order by route.id desc")
    public List<ZuulRouteEntity> findTop10Route(Pageable pageable);

    @Query(value = "select route from ZuulRouteEntity route where route.is_grpc=0 or route.is_grpc=null order by route.id desc")
    public Page<ZuulRouteEntity> findAllRest(Pageable pageable);

    @Query(value = "select route from ZuulRouteEntity route where route.is_grpc=1 order by route.id desc")
    public Page<ZuulRouteEntity> findAllGrpc(Pageable pageable);

    @Query(value = "select route from ZuulRouteEntity route where route.zuul_route_id = :route_Id")
    Optional<ZuulRouteEntity> findOneByRouteId(@Param("route_Id") String route_Id);

}
