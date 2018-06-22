package ru.zvyagintsev.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zvyagintsev.entity.Word;

public interface WordRepository extends CrudRepository<Word, Long> {
}
