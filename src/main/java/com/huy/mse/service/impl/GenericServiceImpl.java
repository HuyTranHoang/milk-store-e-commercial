package com.huy.mse.service.impl;

import com.huy.mse.repository.GenericRepository;
import com.huy.mse.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class GenericServiceImpl<T, V> implements GenericService<T, V> {

    protected abstract GenericRepository<T> getRepository();

    @Override
    public List<V> getAll() {
        return getRepository().findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public V getById(long id) {
        return getRepository().findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public V create(V v) {
        T t = toEntity(v);
        return toDto(getRepository().save(t));
    }

    @Override
    public V update(long id, V v) {
        T t = getRepository().findById(id).orElse(null);

        if (t == null) {
            return null;
        }

        updateEntityFromDto(t, v);

        return toDto(getRepository().save(t));
    }

    @Override
    public void deleteById(long id) {
        getRepository().deleteById(id);
    }

    @Override
    public abstract V toDto(T t);

    @Override
    public abstract T toEntity(V v);

    @Override
    public abstract void updateEntityFromDto(T t, V v);
}
