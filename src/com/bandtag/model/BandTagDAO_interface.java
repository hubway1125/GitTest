package com.bandtag.model;

import java.util.List;

import database.DatabaseConnection_interface;


public interface BandTagDAO_interface  {

	public void insert(BandTagVO bandTagVO);
    public void update(BandTagVO bandTagVO);
    public void delete(String band_tag_id);
    public BandTagVO findByPrimaryKey(String band_tag_id);
    public List<BandTagVO> getAll();
}
