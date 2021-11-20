package ait.team.java.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import ait.team.java.controller.output.ManhourOutput;
import ait.team.java.dto.ManhourDto;
import ait.team.java.entity.ManhourEntity;
import ait.team.java.entity.ThemeEntity;
import ait.team.java.entity.UserEntity;
import ait.team.java.repository.ManhourRepository;
import ait.team.java.repository.ThemeRepository;
import ait.team.java.repository.UserRepository;
import ait.team.java.service.IUploadFileCsvService;

@Service
public class UploadFileCsvService implements IUploadFileCsvService{

	@Autowired
	UserRepository userRepository;
	@Autowired
	ThemeRepository themeRepository;
	@Autowired
	ManhourRepository manhourRepository;
	@Autowired
	ManhourUpdateService manhourUpdateService;
	@Autowired
	ModelMapper modelMaper;
	
	@Override
	public List<ManhourDto> parseCsvFile(InputStream is) {
		
		//read follow record
		/*BufferedReader fileReader = null;
		CSVParser csvParser = null;

		List<ManhourDto> manhourDtos = new ArrayList<ManhourDto>();

		try {
			fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			csvParser = new CSVParser(fileReader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				ManhourDto customer = new ManhourDto();

				manhourDtos.add(customer);
			}*/
		
		String[] CSV_HEADER = { "year", "month", "userNo", "userName", "themeNo", "themeName1", "workContentsCode", "workContentsCodeName", "workContentsDetail", "total",
				"1", "2", "3","4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
				"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		Reader fileReader = null;
		CsvToBean<ManhourDto> csvToBean = null;

		List<ManhourDto> manhourDtos = new ArrayList<ManhourDto>();

		try {
			fileReader = new InputStreamReader(is);

			ColumnPositionMappingStrategy<ManhourDto> mappingStrategy = new ColumnPositionMappingStrategy<ManhourDto>();

			mappingStrategy.setType(ManhourDto.class);
			mappingStrategy.setColumnMapping(CSV_HEADER);

			csvToBean = new CsvToBeanBuilder<ManhourDto>(fileReader).withMappingStrategy(mappingStrategy).withSkipLines(1)
					.withIgnoreLeadingWhiteSpace(true).build();

			manhourDtos = csvToBean.parse();

			return manhourDtos;
		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				} catch (IOException e) {
					System.out.println("Closing fileReader/csvParser Error!");
					e.printStackTrace();
				}
			}
			
			return manhourDtos;
	}

	

	// Store Csv File's data to database
	@Transactional
	@Override
	public ManhourOutput store(InputStream file, String userNo, String roleCode) {
			ManhourOutput manhourOutput = new ManhourOutput();
		try {
			// Using ApacheCommons Csv Utils to parse CSV file
			List<ManhourDto> manhours = parseCsvFile(file);
			for (ManhourDto manhourDto : manhours) {
				
				UserEntity userEntity = userRepository.findByUserNo(manhourDto.getUserNo());
				manhourDto.setSiteCode(userEntity.getSiteCode());
				manhourDto.setGroupCode(userEntity.getGroupCode());
				
				ThemeEntity themeEntity = themeRepository.findByThemeNo(manhourDto.getThemeNo());
				manhourDto.setWorkContentsClass(themeEntity.getWorkContentsClass());
				
				ManhourEntity manhourEntity = modelMaper.map(manhourDto, ManhourEntity.class);
				manhourRepository.save(manhourEntity);
			}
			manhourOutput = manhourUpdateService.getResult(userNo, roleCode);
		} catch(Exception e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
		return manhourOutput;
	}

}
