package com.album.model;

import java.sql.*;
import java.util.*;

import database.DatabaseConnection_interface;

public class AlbumDAOJDBC implements AlbumDAO_interface, DatabaseConnection_interface {
	
	private static final String INSERT_PSTMT = "INSERT INTO ALBUM VALUES ('ALBUM'||LPAD(PIECES_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_PSTMT = "UPDATE ALBUM SET BAND_ID = ?, ALBUM_NAME = ?, ALBUM_INTRO = ?, ALBUM_PHOTO = ?, ALBUM_STATUS = ?, ALBUM_ADD_TIME = ?, ALBUM_RELEASE_TIME = ?, ALBUM_LAST_EDIT_TIME = ?, ALBUM_LAST_EDITOR = ? WHERE ALBUM_ID = ?";
	private static final String DELETE_PSTMT = "DELETE FROM ALBUM WHERE ALBUM_ID = ?";
	private static final String GET_ONE_PSTMT = "SELECT * FROM ALBUM WHERE ALBUM_ID = ?";
	private static final String GET_ALL_PSTMT = "SELECT * FROM ALBUM ORDER BY ALBUM_ID";
	
	
	public static void main(String[] args) {

		AlbumDAOJDBC dao = new AlbumDAOJDBC();
//		
//		// 測試新增
//		dao.testInsert(dao);
//		// 測試修改
//		dao.testUpdate(dao);
//		// 測試刪除
//		dao.testDelete(dao);
//		// 測試查詢一筆
//		dao.testSelectOne(dao);
//		// 測試查詢多筆
//		dao.testSelectAll(dao);
	}

	public void testInsert(AlbumDAOJDBC dao) {
		// 測試新增
		AlbumVO insert = new AlbumVO();
		insert.setBand_id("BAND00000");
		insert.setAlbum_name("testInsert");
		insert.setAlbum_intro("testInsert");
		insert.setAlbum_photo(new byte[1024]);
		insert.setAlbum_status(0);
		insert.setAlbum_add_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		insert.setAlbum_release_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		insert.setAlbum_last_edit_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		insert.setAlbum_last_editor("testInsert");
		dao.insert(insert);
		System.out.println("===inserted===");
	}

	public void testUpdate(AlbumDAOJDBC dao) {
		// 測試修改
		AlbumVO update = new AlbumVO();
		update.setAlbum_id("ALBUM00250");
		update.setBand_id("BAND00000");
		update.setAlbum_name("testUpdate");
		update.setAlbum_intro("testUpdate");
		update.setAlbum_photo(new byte[1024]);
		update.setAlbum_status(0);
		update.setAlbum_add_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		update.setAlbum_release_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		update.setAlbum_last_edit_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		update.setAlbum_last_editor("testUpdate");
		dao.update(update);
		System.out.println("===updated===");
	}

	public void testDelete(AlbumDAOJDBC dao) {
		// 測試刪除
		dao.delete("ALBUM00250");
		System.out.println("===deleted===");
	}

	public void testSelectOne(AlbumDAOJDBC dao) {
		// 測試查詢一筆
		System.out.println("===============selectOne===============");
		AlbumVO selectOne = dao.findByPrimaryKey("ALBUM00000");
		System.out.println(selectOne.toString());
		System.out.println("=======================================");
	}

	public void testSelectAll(AlbumDAOJDBC dao) {
		// 測試查詢多筆
		List<AlbumVO> list = dao.getAll();
		System.out.println("===============selectAll===============");
		int i = 0;
		for (AlbumVO VO : list) {
			System.out.println("=============selectAll:" + i + "==============");
			System.out.println(VO.toString());
			i++;
		}

	}
	
	@Override
	public void insert(AlbumVO albumVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(INSERT_PSTMT);

			pstmt.setString(1, albumVO.getBand_id());
			pstmt.setString(2, albumVO.getAlbum_name());
			pstmt.setString(3, albumVO.getAlbum_intro());
			pstmt.setBytes(4, albumVO.getAlbum_photo());
			pstmt.setInt(5, albumVO.getAlbum_status());
			pstmt.setTimestamp(6, albumVO.getAlbum_add_time());
			pstmt.setTimestamp(7, albumVO.getAlbum_release_time());
			pstmt.setTimestamp(8, albumVO.getAlbum_last_edit_time());
			pstmt.setString(9, albumVO.getAlbum_last_editor());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(AlbumVO albumVO) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(UPDATE_PSTMT);

			pstmt.setString(1, albumVO.getBand_id());
			pstmt.setString(2, albumVO.getAlbum_name());
			pstmt.setString(3, albumVO.getAlbum_intro());
			pstmt.setBytes(4, albumVO.getAlbum_photo());
			pstmt.setInt(5, albumVO.getAlbum_status());
			pstmt.setTimestamp(6, albumVO.getAlbum_add_time());
			pstmt.setTimestamp(7, albumVO.getAlbum_release_time());
			pstmt.setTimestamp(8, albumVO.getAlbum_last_edit_time());
			pstmt.setString(9, albumVO.getAlbum_last_editor());
			pstmt.setString(10, albumVO.getAlbum_id());
			
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(String album_id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(DELETE_PSTMT);

			pstmt.setString(1, album_id);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public AlbumVO findByPrimaryKey(String album_id) {
		AlbumVO albumVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(GET_ONE_PSTMT);

			pstmt.setString(1, album_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				albumVO = new AlbumVO();
				albumVO.setAlbum_id(rs.getString("ALBUM_ID"));
				albumVO.setBand_id(rs.getString("BAND_ID"));
				albumVO.setAlbum_name(rs.getString("ALBUM_NAME"));
				albumVO.setAlbum_intro(rs.getString("ALBUM_INTRO"));
				albumVO.setAlbum_photo(rs.getBytes("ALBUM_PHOTO"));
				albumVO.setAlbum_status(rs.getInt("ALBUM_STATUS"));
				albumVO.setAlbum_add_time(rs.getTimestamp("ALBUM_ADD_TIME"));
				albumVO.setAlbum_release_time(rs.getTimestamp("ALBUM_RELEASE_TIME"));
				albumVO.setAlbum_last_edit_time(rs.getTimestamp("ALBUM_LAST_EDIT_TIME"));
				albumVO.setAlbum_last_editor(rs.getString("ALBUM_LAST_EDITOR"));

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return albumVO;
	}

	@Override
	public List<AlbumVO> getAll() {
		List<AlbumVO> list = new ArrayList();
		AlbumVO albumVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(GET_ALL_PSTMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				albumVO = new AlbumVO();
				albumVO.setAlbum_id(rs.getString("ALBUM_ID"));
				albumVO.setBand_id(rs.getString("BAND_ID"));
				albumVO.setAlbum_name(rs.getString("ALBUM_NAME"));
				albumVO.setAlbum_intro(rs.getString("ALBUM_INTRO"));
				albumVO.setAlbum_photo(rs.getBytes("ALBUM_PHOTO"));
				albumVO.setAlbum_status(rs.getInt("ALBUM_STATUS"));
				albumVO.setAlbum_add_time(rs.getTimestamp("ALBUM_ADD_TIME"));
				albumVO.setAlbum_release_time(rs.getTimestamp("ALBUM_RELEASE_TIME"));
				albumVO.setAlbum_last_edit_time(rs.getTimestamp("ALBUM_LAST_EDIT_TIME"));
				albumVO.setAlbum_last_editor(rs.getString("ALBUM_LAST_EDITOR"));
				list.add(albumVO);

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}
	

}
