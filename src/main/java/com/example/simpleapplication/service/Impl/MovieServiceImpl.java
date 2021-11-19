package com.example.simpleapplication.service.Impl;

import com.example.simpleapplication.config.ConfigProperties;
import com.example.simpleapplication.datasource.movie.model.Movie;
import com.example.simpleapplication.datasource.movie.repository.MovieRepository;
import com.example.simpleapplication.dto.RestResponse;
import com.example.simpleapplication.service.MovieService;
import com.example.simpleapplication.util.ErrorCode;
import com.example.simpleapplication.util.HttpRequestUtilities;
import com.example.simpleapplication.util.JsonUtility;
import com.example.simpleapplication.util.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("MovieService")
public class MovieServiceImpl implements MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    ConfigProperties configProperties;

    @Override
    public List<Map> viewDetaiMovie(){
        List<Map> response = new ArrayList<>();
        try {
            List<Movie> movies = new ArrayList<Movie>();
            movieRepository.findAll().forEach(movies::add);

            for (Movie movie : movies){
                Map data = new HashMap();
                data.put("id", movie.getId());
                data.put("title", movie.getTitle());
                data.put("description", movie.getDescription());
                data.put("published", movie.isPublished());
                response.add(data);
            }
            logger.info("Result data : "+response);
            logger.error("Result data : "+response);
            logger.debug("Result data : "+response);
            logger.trace("Result data : "+response);

        }catch (Exception e){
            logger.error(e.getMessage());
            Map data = new HashMap();
            data.put("status", 404);
            data.put("error", ErrorCode.ERROR.DATA_NOT_FOUND);
            data.put("description", e.getMessage());
            response.add(data);
        }
        return response;
    }

    @Override
    @Cacheable(cacheNames="movie")
    public Map viewRajaOngkir(String id){
        Map response = new HashMap();
        String rajaOngkir = configProperties.getUrlRajaOngkir();
        try {
            logger.info("url : "+rajaOngkir+"?id="+id);
            RestResponse restResponse = HttpRequestUtilities.sendGetRequest(rajaOngkir+"?id="+id);
            if (restResponse.getHttpCode().equalsIgnoreCase("200")){
                Map<String, Object> map = (Map<String, Object>) JsonUtility.parse(restResponse.getResponse(), HashMap.class);
                map = (Map<String, Object>) JsonUtility.parse(JsonUtility.toJson(map.get("rajaongkir")), HashMap.class);
                Map<String, Object> results = (Map<String, Object>) JsonUtility.parse(JsonUtility.toJson(map.get("results")), HashMap.class);
                Map data = new HashMap();
                data.put("province_id", results.get("province_id"));
                data.put("province", results.get("province"));
                response.put("result", data);
                response.put("responnseCode", 200);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return response;
    }

    @Override
    public Map saveMovie(HttpServletRequest request, String tittle, String description){

        Map response = new HashMap();
        try {
            Movie movie = new Movie();
            movie.setTitle(tittle);
            movie.setDescription(description);
            movie.setPublished(true);
            movieRepository.save(movie);

            response.put("messages", "Berhasil membuat list movie");
            logger.info("Berhasil save movie");
        }catch (Exception e){
            logger.error(e.getMessage());
            response.put("error", "Gagal insert data");
        }
        return response;
    }


}
