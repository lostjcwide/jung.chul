package com;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocketClient {
	public static void main(String[] args) {
//		10.0.236.186   1234
		try{
			// 클라이언트의 임시 접속 주소
            String ip = "10.0.236.186";
//			String ip = "127.0.0.1";
            int port = 9876; //접속할 서버 포트
            Socket socket = new Socket(ip, port); //클라이언트의 소켓 생성
         
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
 
            OutputStream out = socket.getOutputStream(); //서버의 소켓으로부터 출력을 받음
            InputStream in = socket.getInputStream(); //서버의 소켓으로부터 입력을 받음
             
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out)); //출력 스트림을 변환
            BufferedReader br = new BufferedReader(new InputStreamReader(in)); //입력 스트림을 변환
             
            String myMsg = null; //전달 메시지
            String echo = null; //받는 메시지
         
            while((myMsg = input.readLine()) != null){
                if(myMsg.equals("/q")){
                    break; //연결 해제
                }
                 
                pw.println(myMsg); //PrintWriter를 이용하여 서버에게 전달
                pw.flush(); //버퍼 비우기
                 
                 
                echo = br.readLine(); //서버가 버퍼로 메시지를 전달하면 이를 읽음
                System.out.println("서버: " + echo);
            }
             
            pw.close();
            br.close();
            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
		
	}
	
	static String getTime(){
        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]"); //날짜 출력
        return f.format(new Date());
    }

}
