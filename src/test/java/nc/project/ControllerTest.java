package nc.project;

import nc.project.controller.EventController;
import nc.project.model.dto.EventCreateDTO;
import nc.project.model.dto.EventGetDTO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@WebMvcTest(EventController.class)
public class ControllerTest {

//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private EventController eventController;
//
//    private static List<EventGetDTO> test_get_data;
//    private static List<EventCreateDTO> test_post_data;
//
//    private final ModelMapper modelMapper = new ModelMapper();
//
//    @BeforeClass
//    public static void setupTestData() {
//        test_get_data = new ArrayList<>();
//        test_get_data.add(new EventGetDTO(1,"test_event_1","test_description", new Date(), new Date(),"test_url1","test_type1","testImageUrl", 0));
//        test_get_data.add(new EventGetDTO(2,"test_event_2","test_description", new Date(), new Date(),"test_url1","test_type1", "testImageUrl1", 0));
//    }
//
//
//    @Test
//    public void getAll() throws Exception {
//        String apiUrl = "/events";
//
//        given(eventController.getAll()).willReturn(test_get_data);
//
//        mvc.perform(get(apiUrl)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)));
//                //.andDo(print());
//                //.andExpect(jsonPath("$[0].title", is(event.getTitle())));
//    }
//
//    @Test
//    public void getEventById() throws Exception {
//        String apiUrl = "/events/"+test_get_data.get(0).getId();
//
//        //given(eventController.getEventById(event.getId())).willReturn(event);
//
//        mvc.perform(get(apiUrl)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//                //.andExpect(jsonPath("city", is(event.getCity())));
//    }
}
