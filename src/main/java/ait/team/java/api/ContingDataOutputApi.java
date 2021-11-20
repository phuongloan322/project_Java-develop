package ait.team.java.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ait.team.java.service.IContingDataOutput;

@RestController
@CrossOrigin
public class ContingDataOutputApi {
	@Autowired
	IContingDataOutput contingDataOutput;

	//check data from table t_manhour from input condition
	@GetMapping(value = "contingDataOutputApi/check")
	public boolean checkConting(@RequestParam(value = "year", required = false) short year,
			@RequestParam(value = "month", required = false) short month) throws NumberFormatException, IOException {
		
		boolean isContingDataOutput = contingDataOutput.checkConting(year, month);
		return isContingDataOutput;
	}

}
