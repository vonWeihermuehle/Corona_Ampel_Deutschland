package net.mbmedia.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(path = "/api/data")
public interface IDataController {

    @PostMapping("/get")
    String get(@RequestParam(value = "hash") String hash);

    @PostMapping("/getLastUpdate")
    String getLastUpdate(@RequestParam(value = "hash") String hash);
}
