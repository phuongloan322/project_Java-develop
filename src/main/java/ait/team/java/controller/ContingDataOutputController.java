package ait.team.java.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ait.team.java.api.output.ContingDataOutput;
import ait.team.java.common.CommonUtils;
import ait.team.java.service.IContingDataOutput;

@Controller
public class ContingDataOutputController {

	@Autowired
	IContingDataOutput contingDataOutput;

	// get page contingDataOutput
	@GetMapping(value = "/contingDataOutput")
	public String getConting(Model model, HttpSession session){
		model.addAttribute("UserNo", session.getAttribute("userNo"));
		return "views/contingDataOutput/contingDataOutput";
	}

	// download CSV file contingDataOutput
	@GetMapping(value = "contingDataOutputApi")
	public void saveCsv(HttpServletResponse response, @RequestParam(value = "year", required = false) short year,
			@RequestParam(value = "month", required = false) short month){

		response.setContentType("text/csv; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ContingDataOutput contentCsv = null;
		try {
			// get data contingDataOuput from database
			contentCsv = contingDataOutput.getContingDataOutput(year, month);
			response.setHeader("Content-Disposition",
					"attachment; filename=\"" + java.net.URLEncoder.encode(contentCsv.getFileName(), "UTF-8") + "\"");
			CommonUtils.writeDataToCsv(response.getWriter(), contentCsv.getCsvData());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
