package com.project.vo;

import java.sql.Timestamp;
import java.util.Date;

//no, content,date,nickname,
public class Reply {
		
		private int no ;
		private String content;
		private Timestamp date;
		private String nickname;
		private int pNo;
		
		
		public Reply() {}
		
		public Reply(String content, String nickname, int pNo) {
			this.content=content;
			this.nickname=nickname;
			this.pNo=pNo;
		}
		
		public Reply(int no, String content, Timestamp date,String nickname,int pNo ) {
			this.no=no;
			this.content=content;
			this.date =date;
			this.nickname=nickname;
			this.pNo=pNo;
			
		}

		public int getNo() {
			return no;
		}

		public void setNo(int no) {
			this.no = no;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		
		public Timestamp getDate() {
			return date;
		}

		public void setDate(Timestamp date) {
			this.date = date;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public int getpNo() {
			return pNo;
		}

		public void setpNo(int pNo) {
			this.pNo = pNo;
		}
		
		
		
		
}
