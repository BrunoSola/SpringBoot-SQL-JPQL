package com.brunosola.uri2611.repositories;

import com.brunosola.uri2611.dto.MovieMinDTO;
import com.brunosola.uri2611.entities.Movie;
import com.brunosola.uri2611.projections.MovieMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true, value = "SELECT movies.id, movies.name " +
            "FROM movies " +
            "INNER JOIN genres ON movies.id_genres = genres.id " +
            "WHERE UPPER(genres.description) = UPPER(:genreName)")
    List<MovieMinProjection> search1(String genreName);

    @Query("SELECT new com.brunosola.uri2611.dto.MovieMinDTO(obj.id, obj.name) " +
            "FROM Movie obj " +
            "WHERE UPPER(obj.genre.description) = UPPER(:genreName)")
    List<MovieMinDTO> search2(String genreName);
}
