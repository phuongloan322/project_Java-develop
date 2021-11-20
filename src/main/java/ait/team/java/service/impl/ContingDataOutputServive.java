package ait.team.java.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ait.team.java.api.output.ContingDataOutput;
import ait.team.java.common.CommonUtils;
import ait.team.java.entity.output.ContingData;
import ait.team.java.repository.ContingDataOutputRepositoty;
import ait.team.java.service.IContingDataOutput;

@Service
public class ContingDataOutputServive implements IContingDataOutput {

	@Autowired
	ContingDataOutputRepositoty contingDataOutputRepositoty;
	private final String DATE_FORMAT_YYYYMMDDHHMMSS = "YYYYMMDDHHMMSS";
	private final String EXTENSION_CSV = ".csv";
	private final String FILE_NAME = "原価計算データ_";

	// get contingDataOuput form database
	@Override
	public ContingDataOutput getContingDataOutput(short year, short month) throws IOException {

		ContingDataOutput contingDataOutput = new ContingDataOutput();
		List<ContingData> listContingData = contingDataOutputRepositoty.findContingDataOuput(year, month);
		List<String[]> contentCSV = new ArrayList<>();
		String[] headerCSV = { "対象年月", 
				"会計部門コード（ユーザ）", 
				"会計部門名", 
				"ユーザNo", 
				"ユーザ名", 
				"テーマNo", 
				"テーマ名", 
				"小計単位コード", 
				"工数",
				"原価区分コード", 
				"勘定科目コード", 
				"売上科目コード", 
				"売上予定日",
				"受注金額", 
				"計画原価率", 
				"会社コード", 
				"会計部門コード（テーマ）", 
				"売上月区分", 
				"売上月区分メモ",
				"仕掛フラグ" 
			};
		contentCSV.add(headerCSV);
		for (ContingData contingData : listContingData) {
			String[] line = new String[20];
			line[0] = contingData.getConcat();
			line[1] = contingData.getGroupCode();
			line[2] = contingData.getGroupName();
			line[3] = contingData.getUserNo();
			line[4] = contingData.getUserName();
			line[5] = contingData.getThemeNo();
			line[6] = contingData.getThemeName1();
			line[7] = contingData.getSubtotalCode();
			line[8] = String.valueOf(contingData.getTotal());
			line[9] = "";
			line[10] = "";
			line[11] = contingData.getSalesObjectCode();
			line[12] = contingData.getSalesDate();
			line[13] = String.valueOf(contingData.getOrderAmount());
			line[14] = String.valueOf(contingData.getPlanCostRate());
			line[15] = contingData.getCompanyCode();
			line[16] = contingData.getAccountingGroupCode();
			line[17] = contingData.getSalesMonthCode();
			line[18] = contingData.getSalesMonthCodeMemo();
			line[19] = String.valueOf(contingData.getProcessingFlg());
			contentCSV.add(line);
		}
		Date dateNow = new Date();
		String fileName = FILE_NAME + CommonUtils.dateToString(dateNow, DATE_FORMAT_YYYYMMDDHHMMSS) + EXTENSION_CSV;
		contingDataOutput.setFileName(fileName);
		contingDataOutput.setCsvData(contentCSV);

		return contingDataOutput;
	}
	/*
	 * check data from table t_manhour from input condition
	 * input: year(short), month(short) 
	 */
	@Override
	public boolean checkConting(short year, short month) {
		List<ContingData> list = contingDataOutputRepositoty.findContingDataOuput(year, month);
		if (list.isEmpty()) {
			return false;
		}
		return true;
	}

}
