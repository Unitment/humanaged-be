package vn.fsoft.humanaged.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface IService<T, K> {

    List<T> getAll();

    Optional<T> getById(K key);

    T save(T entity);

    void deleteById(K key);
}