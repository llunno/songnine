package com.ddd.project.songnine.Business.Interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface CommomControllerMethods<RegisterReq, ID> {

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RegisterReq registerRequest);

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam ID id, @RequestBody RegisterReq registerRequest);

    @GetMapping("/get")
    public ResponseEntity<?> getById(@RequestParam ID id);

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody ID id);

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll();

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll();
}
