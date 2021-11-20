package ait.team.java.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ait.team.java.api.output.ContingDataOutput;
import ait.team.java.common.CommonUtils;
import ait.team.java.controller.input.InputSearchManhour;
import ait.team.java.controller.output.ManhourOutput;
import ait.team.java.dto.ManhourDto;
import ait.team.java.dto.UserDto;
import ait.team.java.dto.WorkContentsDto;
import ait.team.java.service.IManhourUpdateService;
import ait.team.java.service.impl.UploadFileCsvService;

@Controller
public class ManhourUpdateController {

	@Autowired
	IManhourUpdateService manhourUpdateService;
	@Autowired
	UploadFileCsvService uploadFileCsvService;

	/*
	 * Controller send data for client when start to enter ManhourUpdate.
	 */
	@GetMapping(value = "ManhourUpdate")
	public String manhourUpdate(Model model, HttpSession session) {
		String userNo = (String) session.getAttribute("userNo");
		String roleCode = (String) session.getAttribute("roleCode");
		ManhourOutput manhourOutput = manhourUpdateService.getResult(userNo, roleCode);
		model.addAttribute("manhourOutput", manhourOutput);

		return "Views/ManhourUpdate/index";
	}

	/*
	 * Controller receive request after that handling send data for client when user
	 * search.
	 */
	@GetMapping(value = "ManhourUpdate/Search")
	public String searchManhour(@ModelAttribute("input") InputSearchManhour input, Model model, HttpSession session) {
		String userNo = (String) session.getAttribute("userNo");
		String roleCode = (String) session.getAttribute("roleCode");
		ManhourOutput manhourOutput = manhourUpdateService.searchManhour(userNo, roleCode, input);
		model.addAttribute("manhourOutput", manhourOutput);

		return "Views/ManhourUpdate/index";
	}

	/*
	 * Controller receive request after that handling send data for client when user
	 * choose group include users.
	 */
	@GetMapping(value = "ManhourUpdate/Search/UserFollowGroup")
	public @ResponseBody List<UserDto> displayUsersFollowGroup(@RequestParam(value = "groupCode") String groupCode) {
		InputSearchManhour input = new InputSearchManhour();
		input.setGroupCode(groupCode);
		List<UserDto> userFollowGroupList = manhourUpdateService.getUser(input);
		return userFollowGroupList;
	}
	
	
	/*
	 * Controler getWorkContents follow workContentsClass.
	 */
	@GetMapping(value = "ManhourUpdate/GetWorkContents")
	public @ResponseBody List<WorkContentsDto> GetWorkContentsFlWorkContClass(@RequestParam(value = "workContentsClass") String workContentsClass) {
		List<WorkContentsDto> workContentsDtos = manhourUpdateService.getWorkContentsCode(workContentsClass);
		return workContentsDtos;
	}

	/*
	 * Controller receive request after that handling save (insert/update/delete)
	 * data of table m_manhour.
	 */
	@PostMapping(value = "ManhourUpdate/Save")
	public @ResponseBody boolean saveManhourUpdate(@RequestBody List<List<ManhourDto>> manhours) {
		return manhourUpdateService.saveManhourUpdate(manhours);
	}

	// download CSV file 
	@GetMapping(value = "dowloadCsvManhourUpdate")
	public void dowloadCsv(HttpServletResponse response, HttpSession session) {
		String userNo = (String) session.getAttribute("userNo");
		response.setContentType("text/csv; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ContingDataOutput contentCsv = null;
		try {
			// get data contingDataOuput from database
			contentCsv = manhourUpdateService.getDataExportCsv(userNo);
			response.setHeader("Content-Disposition",
					"attachment; filename=\"" + java.net.URLEncoder.encode(contentCsv.getFileName(), "UTF-8") + "\"");
			CommonUtils.writeDataToCsv(response.getWriter(), contentCsv.getCsvData());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	//Upload file CSV
	@PostMapping(value = "ManhourUpdate/ImportCsv")
	public String uploadFile(@RequestParam("file") MultipartFile file, Model model, HttpSession session ) {
		String userNo = (String) session.getAttribute("userNo");
		String roleCode = (String) session.getAttribute("roleCode");
		try {
			ManhourOutput manhourOutput = uploadFileCsvService.store(file.getInputStream(), userNo, roleCode);
			model.addAttribute("manhourOutput", manhourOutput);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return "Views/ManhourUpdate/index";
		
	}

}
