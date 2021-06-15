package test.java.controller;


import com.czhang.common.result.ResultCodeEnum;
import com.czhang.model.model.MediCenterSet;
import com.czhang.service.medicenter.ServiceMediCenterApplication;
import com.czhang.service.medicenter.controller.MediCenterSetController;
import com.czhang.service.medicenter.service.impl.MediCenterSetServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ActiveProfiles("junit")
@WebMvcTest(controllers = {MediCenterSetController.class})
@ContextConfiguration(classes = {ServiceMediCenterApplication.class})
public class MediCenterSetControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MediCenterSetServiceImpl mediCenterSetService;

    @Test
    @DisplayName("Test Find ALL Center Sets with Data")
    public void test_findAllCenterSets() throws Exception{
        given(mediCenterSetService.findAllCenterSets()).willReturn(Collections.singletonList(new MediCenterSet()));
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/center/mediCenterSet/api/v1/findAll")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test Find All Center Sets with No data")
    public void test_findAllCenterSets_NoData() throws Exception{
        given(mediCenterSetService.findAllCenterSets()).willReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/center/mediCenterSet/api/v1/findAll")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("resultCode").value(ResultCodeEnum.FAIL.getCode()));
    }
}
