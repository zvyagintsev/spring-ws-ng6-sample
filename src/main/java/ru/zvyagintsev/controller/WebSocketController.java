package ru.zvyagintsev.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.zvyagintsev.config.Constants;
import ru.zvyagintsev.entity.Word;
import ru.zvyagintsev.repository.WordRepository;

@Controller
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    SimpMessagingTemplate msgTemplate;

    @Autowired
    WordRepository repository;

    @MessageMapping("/add")
    public void onMessage(String word) {
        logger.info("Received a word: {}", word);
        if(StringUtils.isEmpty(word))
            return;
        String preparedWord = StringUtils.reverse(StringUtils.trimToEmpty(word));
        Word wrd = new Word(preparedWord);
        repository.save(wrd);
        logger.info("Sending a data {} to front-end", repository.findAll());
        msgTemplate.convertAndSend(Constants.BROKER_NAME, repository.findAll());

    }

}
