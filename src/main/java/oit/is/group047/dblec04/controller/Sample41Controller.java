package oit.is.group047.dblec04.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.group047.dblec04.model.*;

@Controller
@RequestMapping("/sample4")
public class Sample41Controller {
  @Autowired
  ChamberMapper chamberMapper;

  @GetMapping("step1")
  public String sample41() {
    return "sample41.html";
  }

  @GetMapping("step2/{id}")
  public String sample42(@PathVariable Integer id, ModelMap model) {
    Chamber chamber2 = chamberMapper.selectById(id);
    model.addAttribute("chamber2", chamber2);

    return "sample41.html";
  }

  @GetMapping("step3")
  public String sample43() {
    return "sample43.html";
  }

  @PostMapping("step3")
  public String sample44(@RequestParam String chamberName, ModelMap model, Principal prin) {
    String loginUser = prin.getName();
    Chamber chamber3 = new Chamber();
    chamber3.setChamberName(chamberName);
    chamber3.setUserName(loginUser);
    chamberMapper.insertChamber(chamber3);
    model.addAttribute("chamber3", chamber3);
    return "sample43.html";
  }
}
