package _00_init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.naming.event.NamespaceChangeListener;

import _00_init.util.DBService;
import _00_init.util.GlobalService;
import _00_init.util.SystemUtils2018;
import model.*;
import dao.impl.*;

public class EDMTableReset {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {

		String line = "";
		int count = 0;

		try (Connection con = DriverManager.getConnection(DBService.getDbUrl(), DBService.getUser(),
				DBService.getPassword()); Statement stmt = con.createStatement();) {

			// Topic
			stmt.executeUpdate(DBService.getDropTopic());
			System.out.println("Topic表格刪除成功");
			stmt.executeUpdate(DBService.getCreateTopic());
			System.out.println("Topic表格產生成功");

//			try (FileInputStream fis1 = new FileInputStream("data/topic.dat");
//					InputStreamReader isr1 = new InputStreamReader(fis1, "UTF-8");
//					BufferedReader br1 = new BufferedReader(isr1);) {
//				while ((line = br1.readLine()) != null) {
//					System.out.println("line=" + line);
//					// 去除 UTF8_BOM: \uFEFF
//					if (line.startsWith(UTF8_BOM)) {
//						line = line.substring(1);
//					}
//					String[] tp = line.split("\\|");
//					TopicBean topic = new TopicBean();
//
//					topic.setTitle(tp[0]);
//					topic.setCategoryid(Integer.parseInt(tp[1]));
//					Clob clob = SystemUtils2018.fileToClob(tp[2]);
//					topic.setContent(tp[2]);
//					topic.setUsername(tp[3]);
//					topic.setPosttime(new java.sql.Timestamp(System.currentTimeMillis()));
////					topic.setPosttime(System.currentTimeMillis());
//
//					saveTopic(topic, con);
//				}
//			}

			// Menu
			stmt.executeUpdate(DBService.getDropMenu());
			System.out.println("Menu表格刪除成功");
			stmt.executeUpdate(DBService.getCreateMenu());
			System.out.println("Menu表格產生成功");

			try (FileReader fr1 = new FileReader("data/menu.dat"); BufferedReader br2 = new BufferedReader(fr1)) {
				while ((line = br2.readLine()) != null) {
					System.out.println("line=" + line);
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] mu = line.split("\\|");
					MenuBean menu = new MenuBean();
					menu.setCategory(mu[0]);

					saveMenu(menu, con);
				}
				System.out.println("Menu 資料新增成功");
			} catch (IOException e) {
				System.err.println("新建Menu表格時發生IO例外: " + e.getMessage());
			}
			// Member
			stmt.executeUpdate(DBService.getDropMember());
			System.out.println("Member表格刪除成功");
			stmt.executeUpdate(DBService.getCreateMember());
			System.out.println("Member表格產生成功");

			try (FileInputStream fis2 = new FileInputStream("data/member.txt");
					InputStreamReader isr2 = new InputStreamReader(fis2, "UTF-8");
					BufferedReader br3 = new BufferedReader(isr2);) {
				while ((line = br3.readLine()) != null) {
					String[] mb = line.split("\\|");
					MemberBean member = new MemberBean();
					member.setEmail(mb[0]);
					member.setPassword(mb[1]);
					member.setUsername(mb[2]);
					member.setGender(mb[3]);
					member.setAge(Integer.parseInt(mb[4]));
//					bean.setAge(Integer.parseInt(sa[4]));

					saveMember(member, con);
					count++;
					System.out.println("新增" + count + "筆記錄:" + mb[1]);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// Reply
			stmt.executeUpdate(DBService.getDropReply());
			System.out.println("Reply表格刪除成功");
			stmt.executeUpdate(DBService.getCreateReply());
			System.out.println("Reply表格產生成功");

//			try (FileReader fr2 = new FileReader("data/reply.dat"); BufferedReader br4 = new BufferedReader(fr2)) {
//				while ((line = br4.readLine()) != null) {
//					System.out.println("line=" + line);
//					// 去除 UTF8_BOM: \uFEFF
//					if (line.startsWith(UTF8_BOM)) {
//						line = line.substring(1);
//					}
//					String[] rp = line.split("\\|");
//					ReplyBean reply = new ReplyBean();
//					reply.setUsername(rp[0]);
//					reply.settopicid(Integer.parseInt(rp[1]));
//					reply.setReplycontent(rp[2]);
//					reply.setReplytime(new java.sql.Timestamp(System.currentTimeMillis()));
//
//					saveReply(reply, con);
//				}
//			}
//
		} catch (SQLException e) {
			System.err.println("新建表格時發生SQL例外: " + e.getMessage());
			e.printStackTrace();
//		} catch (IOException e) {
//			System.err.println("新建表格時發生IO例外: " + e.getMessage());
		}
	}
	// 方法saveTopic

	static public int saveTopic(TopicBean tb, Connection con) {
		String sql = "insert into addtopic" + " (categoryid, title, content, username,posttime) "
				+ " values (?, ?, ?, ?, ?)";
		int n = 0;
		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, tb.getCategoryid());
			ps.setString(2, tb.getTitle());
			ps.setString(3, tb.getContent());
			ps.setString(4, tb.getUsername());
			ps.setTimestamp(5, tb.getPosttime());

			n = ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return n;
	}

//方法saveMenu
	static public int saveMenu(MenuBean mb, Connection con) {
		String sqlS = "INSERT INTO menu (category) VALUES(?)";
		int n = 0;
		try (PreparedStatement ps = con.prepareStatement(sqlS);) {
			ps.setString(1, mb.getCategory());

			n = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return n;
	}

//方法saveMember
	static public int saveMember(MemberBean mb, Connection con) {
		String sql = "insert into forummember " + " (email, password, username, gender, age ) "
				+ " values (?, ?, ?, ?, ?)";
		int n = 0;
		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, mb.getEmail());
			ps.setString(2, mb.getPassword());
			ps.setString(3, mb.getUsername());
			ps.setString(4, mb.getGender());
			ps.setInt(5, mb.getAge());
//				ps.setInt(5, mb.getAge());
			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return n;
	}

	// 方法saveReply
	static public int saveReply(ReplyBean rb, Connection con) {
		String sql = "insert into reply " + " (username, topicid, replycontent, replytime ) "
				+ " values (?, ?, ?, ?)";
		int n = 0;
		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, rb.getUsername());
			ps.setInt(2, rb.getTopicid());
			ps.setString(3, rb.getReplycontent());
			ps.setTimestamp(4, rb.getReplytime());

			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return n;
	}

}
