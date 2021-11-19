package com.example.simpleapplication.controller;

import com.example.simpleapplication.service.Impl.MovieServiceImpl;
import com.example.simpleapplication.service.MovieService;
import com.example.simpleapplication.util.ErrorCode;
import com.example.simpleapplication.util.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @GetMapping("/list")
    public List<Map> getAll() {
        List<Map> response = movieService.viewDetaiMovie();
        return response;
    }

    @GetMapping("/getRajaOngkir")
    public Map getRajaOngkir(@RequestParam(value = "id", required = false) String id){
        long startTime = System.currentTimeMillis();
        Map response = movieService.viewRajaOngkir(id);
        logger.info("Execution Time = "+(System.currentTimeMillis()-startTime));
        return response;
    }

    @PostMapping("/create")
    public ResponseEntity<Map> create(@RequestParam(value = "tittle", required = false) String tittle,
                                      @RequestParam(value = "description", required = false) String description,
                                      HttpServletRequest request) {
        Map response = movieService.saveMovie(request, tittle, description);

        return ResponseEntity.ok(response);
    }


}
