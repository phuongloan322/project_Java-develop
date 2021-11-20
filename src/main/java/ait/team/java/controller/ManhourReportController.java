package ait.team.java.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ait.team.java.common.CommonUtils;
import ait.team.java.controller.input.InputManhourReport;
import ait.team.java.controller.output.manhourReport.SaveOutputRes;
import ait.team.java.controller.output.manhourReport.ScreenManhourReport;
import ait.team.java.controller.output.manhourReport.UserScreenManhourReport;
import ait.team.java.service.IManhourReportService;

@Controller
public class ManhourReportController {
	
	@Autowired
	IManhourReportService manhourReportService;
	
	private final String ERR_005 = "該当データが存在しません";
	private final String FILE_NAME = "工数集計_";
	private final String DATE_FORMAT_YYYYMMDDHHMMSS = "YYYYMMDDHHMMSS";
	private final String EXTENSION_CSV = ".csv";
	
	@GetMapping(value = "/manhourReport")
	public String getConting(@RequestParam(value = "saveName", required = false ) String saveName, Model model, HttpSession session) throws ParseException {
		String userNo = (String) session.getAttribute("userNo");
		ScreenManhourReport screenManhourReport = manhourReportService.getUserScreenItem(userNo, saveName);
		model.addAttribute("manhourReport", screenManhourReport);
		return "views/manhourReport/manhourReport";
	}
	
	@PostMapping(value = "/manhourReport")
	public @ResponseBody SaveOutputRes postConting(@RequestBody InputManhourReport inputManhourReport, Model model, HttpSession session) throws ParseException {
		String userNo = (String) session.getAttribute("userNo");
		SaveOutputRes saveOutputRes = manhourReportService.validate(inputManhourReport);
		if(saveOutputRes.isResult()) {
			manhourReportService.saveUserScreenItem(inputManhourReport, userNo);
			return saveOutputRes;
		}
		return saveOutputRes;
	}
	
	@GetMapping(value = "/userByGroup")
	public @ResponseBody List<UserScreenManhourReport> getUserByGroup(@RequestParam(value = "groupCode", required = false ) String groupCode){
		List<UserScreenManhourReport> userScreenManhourReportList = manhourReportService.getUserByGroup(groupCode);
		return userScreenManhourReportList;
	}
	
	@PostMapping(value = "/deleteReport")
	public @ResponseBody boolean delete(@RequestParam(value = "saveName", required = false) String saveName, HttpSession session){
		String userNo = (String) session.getAttribute("userNo");
		boolean result = manhourReportService.delete(saveName, userNo);
		return result;
	}
	
	@PostMapping(value = "/manhourReport/check")
	public @ResponseBody SaveOutputRes validate(@RequestBody InputManhourReport inputManhourReport, HttpSession session){
		String userNo = (String) session.getAttribute("userNo");
		try {
			SaveOutputRes saveOutputRes = manhourReportService.validate(inputManhourReport);
			if(saveOutputRes.isResult()) {
				List<String[]> exportManhourReportList = new ArrayList<>();
				exportManhourReportList = manhourReportService.exportManhourReport(inputManhourReport, userNo);
				int size = 1;
				if(inputManhourReport.getTotal().equals("true")) {
					size = 2;
				}
				if(exportManhourReportList.size() == size) {
					saveOutputRes.setResult(false);
					saveOutputRes.setMessage(ERR_005);
				}
			}
			return saveOutputRes;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	@PostMapping(value = "/manhourReport/page")
	public @ResponseBody List<String[]> exportPage(@RequestBody InputManhourReport inputManhourReport, HttpSession session, Model model){
		String userNo = (String) session.getAttribute("userNo");
		SaveOutputRes saveOutputRes;
		try {
			saveOutputRes = manhourReportService.validate(inputManhourReport);
			List<String[]> exportManhourReportList = new ArrayList<>();
			if(saveOutputRes.isResult()) {
				exportManhourReportList = manhourReportService.exportManhourReport(inputManhourReport, userNo);
				return exportManhourReportList;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manhourReport/search", method = RequestMethod.POST,
	        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
	        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
	        headers="Accept=application/x-www-form-urlencoded")
	public void search(HttpServletResponse response,@RequestParam MultiValueMap<String, ?> input, HttpSession session){
		String userNo = (String) session.getAttribute("userNo");
		InputManhourReport inputManhourReport = new InputManhourReport();
		List<?> apostrophe = input.get("apostrophe");
		inputManhourReport.setApostrophe(String.valueOf(apostrophe.get(0)));
		List<?> comma = input.get("comma");
		inputManhourReport.setComma(String.valueOf(comma.get(0)));
		List<?> endDate = input.get("endDate");
		inputManhourReport.setEndDate(String.valueOf(endDate.get(0)));
		inputManhourReport.setGroupList((List<String>)input.get("groupList[]"));
		inputManhourReport.setHideList((List<String>)input.get("hideList[]"));
		List<?> saveName = input.get("saveName");
		inputManhourReport.setSaveName(String.valueOf(saveName.get(0)));
		inputManhourReport.setShowList((List<String>)input.get("showList[]"));
		List<?> startDate = input.get("startDate");
		inputManhourReport.setStartDate(String.valueOf(startDate.get(0)));
		inputManhourReport.setThemeList((List<String>)input.get("themeList[]"));
		List<?> total = input.get("total");
		inputManhourReport.setTotal(String.valueOf(total.get(0)));
		inputManhourReport.setUserList((List<String>)input.get("userList[]"));
		inputManhourReport.setWorkContentList((List<String>)input.get("workContentList[]"));
		inputManhourReport.setWorkContentDetailList((List<String>)input.get("workContentDetailList[]"));
		Date dateNow = new Date();
		String fileName = FILE_NAME + CommonUtils.dateToString(dateNow, DATE_FORMAT_YYYYMMDDHHMMSS) + EXTENSION_CSV;
		try {
			SaveOutputRes saveOutputRes = manhourReportService.validate(inputManhourReport);
			List<String[]> exportManhourReportList = new ArrayList<>();
			if(saveOutputRes.isResult()) {
				response.setContentType("text/csv; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				exportManhourReportList = manhourReportService.exportManhourReport(inputManhourReport, userNo);
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + java.net.URLEncoder.encode(fileName, "UTF-8") + "\"");
				CommonUtils.writeDataToCsv(response.getWriter(), exportManhourReportList);
			}
		} catch (ParseException | UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
