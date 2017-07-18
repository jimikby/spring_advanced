package com.epam.theatre.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.epam.theatre.service.FileService;

@Controller
public class UploadController {

	@Autowired
	private FileService fileService;

	@RequestMapping(value = { "/upload" }, method = RequestMethod.GET)
	public ModelAndView uploadPage(ModelAndView model) {
		return new ModelAndView("upload");
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView handleFormUpload(MultipartHttpServletRequest req) {

		ModelAndView model = new ModelAndView();
		Map<String, MultipartFile> fileMap = req.getFileMap();

		if (!fileMap.isEmpty()) {
			try {
				List<String> dataList = fileService.parseFiles(fileMap);
				model.addObject("dataList", dataList);
				model.setViewName("redirect:/customer");
				
			} catch (Exception e) {
				model.addObject("message", e.getMessage());
				model.setViewName("error");
			} finally {
				return model;
			}
		}

		model.setViewName("error");
		return model;
	}

}
