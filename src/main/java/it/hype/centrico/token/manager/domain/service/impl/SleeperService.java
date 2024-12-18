package it.hype.centrico.token.manager.domain.service.impl;

import org.springframework.stereotype.Service;

@Service
public class SleeperService {

    public void sleep(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
