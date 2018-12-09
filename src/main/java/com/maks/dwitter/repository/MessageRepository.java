package com.maks.dwitter.repository;

import com.maks.dwitter.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
