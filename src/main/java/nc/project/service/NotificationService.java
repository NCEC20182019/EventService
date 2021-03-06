package nc.project.service;

import com.google.common.net.HttpHeaders;
import nc.project.model.Event;
import nc.project.model.TriggerFlag;
import nc.project.model.dto.TriggerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NotificationService {

    @Value("${nc.project.notification.url}")
    private String notificationUrl;
    private static Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void triggerNotificationService(Event event, TriggerFlag triggerFlag)
    {
        try {
            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            String token = "";
            if (details instanceof OAuth2AuthenticationDetails){
                OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails)details;
                token = oAuth2AuthenticationDetails.getTokenValue();
            }
            final String ftoken = token;

            TriggerDTO trigger = new TriggerDTO(
                    event.getId(),
                    triggerFlag,
                    event.getType(),
                    event.getLocation().getLatitude(),
                    event.getLocation().getLongitude()
            );

            logger.debug("[triggerNotificationService] отправляется: {}", trigger);

            WebClient.create()
                    .post()
                    .uri(notificationUrl)
                    .body(BodyInserters.fromObject(trigger))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .headers(h -> h.setBearerAuth(ftoken))
                    .accept(MediaType.APPLICATION_JSON)
                    //.attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction
                    //      .clientRegistrationId("event"))
                    .retrieve()
                    .bodyToMono(Object.class)
                    .subscribe(s -> {
                            },
                            e -> logger.debug(e.getMessage()),
                            () -> logger.debug("[triggerNotificationService] успешно отправлено!"));
        }catch (Exception ex) //TODO найти какой тут эксепшен кидается
        {
            logger.debug(ex.getMessage());
        }
    }
}
