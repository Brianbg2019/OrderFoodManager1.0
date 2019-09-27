package com.personal.ofm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.org.apache.regexp.internal.recompile;

@Controller
@RequestMapping("orden")
public class OrdenController {

	@RequestMapping(value = "index")
	public String vista() {
		return "vistaCliente/index";
	}
}
