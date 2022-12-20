package com.codegym.service;

import java.util.List;

public interface IGeneralService<T> {
    List<T> findAll();
    boolean save(T t);
    boolean delete(Long id);
    T findById(Long id);
}
