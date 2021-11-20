package ait.team.java.service;

import java.io.IOException;

import ait.team.java.api.output.ContingDataOutput;

public interface IContingDataOutput {
	public ContingDataOutput getContingDataOutput(short year, short month) throws IOException;
	public boolean checkConting(short year, short month);
}
