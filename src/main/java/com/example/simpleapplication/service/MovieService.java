package com.example.simpleapplication.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface MovieService {
    List<Map> viewDetaiMovie();
    Map saveMovie(HttpServletRequest request, String tittle, String description);
    Map viewRajaOngkir(String id);
}
