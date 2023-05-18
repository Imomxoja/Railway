package com.example.railway.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @param <PR> is Post Request
 * @param <BS> is Base Response
 */

@Service
public interface BaseService<PR, BS> {
    BS create(PR pr);
    BS getById(UUID id);
    BS deleteById(UUID id);
}
