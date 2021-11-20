package ait.team.java.service;

import java.io.InputStream;
import java.util.List;

import ait.team.java.controller.output.ManhourOutput;
import ait.team.java.dto.ManhourDto;


public interface IUploadFileCsvService {
	
	public List<ManhourDto> parseCsvFile(InputStream is);
	public ManhourOutput store(InputStream file,String userNo, String roleCode);

}
