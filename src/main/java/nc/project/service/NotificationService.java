package nc.project.service;

import com.google.common.net.HttpHeaders;
import nc.project.controller.EventController;
import nc.project.model.Event;
import nc.project.model.TriggerFlags;
import nc.project.model.dto.TriggerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.ConnectException;

@Service
public class NotificationService {

    @Value("${nc.project.notification.url}")
    private String notificationUrl;
    private static Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void triggerNotificationService(Event event, TriggerFlags triggerFlag)
    {
        try {
            WebClient.create()
                    .post()
                    .uri(notificationUrl)
                    .body(BodyInserters.fromObject(new TriggerDTO(
                            event.getId(),
                            triggerFlag,
                            event.getType(),
                            event.getLocation().getLatitude(),
                            event.getLocation().getLongitude()
                    )))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve().bodyToMono(Object.class).block();
        }catch (Exception ex) //TODO найти какой тут эксепшен кидается
        {
            logger.debug(ex.getMessage());
        }
    }
}
