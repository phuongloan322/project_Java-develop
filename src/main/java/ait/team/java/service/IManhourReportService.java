package ait.team.java.service;

import java.text.ParseException;
import java.util.List;

import ait.team.java.controller.input.InputManhourReport;
import ait.team.java.controller.output.manhourReport.ExportManhourReport;
import ait.team.java.controller.output.manhourReport.SaveOutputRes;
import ait.team.java.controller.output.manhourReport.ScreenManhourReport;
import ait.team.java.controller.output.manhourReport.UserScreenManhourReport;
import ait.team.java.entity.ManhourEntity;

public interface IManhourReportService {
	ScreenManhourReport getUserScreenItem(String userNo, String saveName);
	boolean saveUserScreenItem(InputManhourReport inputManhourReport, String userNo);
	List<UserScreenManhourReport> getUserByGroup(String groupCode);
	List<String[]> exportManhourReport(InputManhourReport inputManhourReport, String userNo) throws ParseException;
	SaveOutputRes validate(InputManhourReport inputManhourReport) throws ParseException;
	boolean delete(String saveName, String userNo);
}
