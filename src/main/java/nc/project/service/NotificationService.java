package nc.project.service;

import com.google.common.net.HttpHeaders;
import nc.project.model.Event;
import nc.project.model.TriggerFlags;
import nc.project.model.dto.TriggerDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NotificationService {

    @Value("${nc.project.notification.url}")
    private String notificationUrl;

    public void triggerNotificationServie(Event event, TriggerFlags triggerFlag)
    {
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
                .retrieve();
    }
}
