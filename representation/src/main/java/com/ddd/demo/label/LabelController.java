package com.ddd.demo.label;

import com.ddd.demo.application.LabelApplicationService;
import com.ddd.demo.command.CreateLabelCommand;
import com.ddd.demo.request.CreateLabelRequest;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RestSchema(schemaId = "label")
@RequestMapping("/label")
public class LabelController {

    @Resource
    LabelApplicationService labelApplicationService;


    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody CreateLabelRequest request) {
        CreateLabelCommand createCommand = new CreateLabelCommand();
        BeanUtils.copyProperties(request,createCommand);
        Long id = labelApplicationService.create(createCommand);
        return ResponseEntity.ok(id);
    }

}
