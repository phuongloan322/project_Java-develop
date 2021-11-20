package ait.team.java.api;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ait.team.java.api.output.ContingDataOutput;
import ait.team.java.common.CommonUtils;
import ait.team.java.dto.GroupDto;
import ait.team.java.dto.ManhourDto;
import ait.team.java.dto.SalesObjectDto;
import ait.team.java.dto.ThemeDto;
import ait.team.java.dto.WorkContentsDto;
import ait.team.java.dto.output.ManHourInputDto;
import ait.team.java.service.IManhourInputService;

@CrossOrigin
@RestController
public class ManhourInputApi {

	@Autowired
	IManhourInputService manhourInputService;


	// save data ManhourInputDay
	@PostMapping(value = "ManhourInputDay/Save")
	public void saveManhourInputDay(@RequestBody List<List<ManhourDto>> manhours,
			@RequestParam(value = "getDay", required = false) String getDay) {

		manhourInputService.saveManhourInputDay(manhours, getDay);
	}
	
	// save data ManhourInputWeek
		@PostMapping(value = "ManhourInputWeek/Save")
		public void saveManhourInputWeek(@RequestBody List<List<ManHourInputDto>> manhours ) {

			manhourInputService.saveManhourInputWeek(manhours);
		}

	// export file csv
	@GetMapping(value = "ManhourInput/CSV")
	public void exportCSV(HttpServletResponse response,
			@RequestParam(value = "processingDate", required = false) String processingDate,
			@RequestParam(value = "userNo", required = false) String userNo) throws ParseException {

		try {
			ContingDataOutput contentCsv = manhourInputService.writeDataToCSV(processingDate, userNo);
			response.setContentType("text/csv; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition",
					"attachment; filename=ManhourInput_" + contentCsv.getFileName() + ".csv");
			CommonUtils.writeDataToCsvUsingStringArray(response.getWriter(), contentCsv.getCsvData());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
