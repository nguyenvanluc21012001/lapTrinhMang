/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laptrinhmang;

/**
 *
 * @author h
 */
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCP_server {
    public static void main(String[] args) throws IOException {
        // Kiểm tra số lượng tham số dòng lệnh
        if (args.length != 2) {
            System.out.println("Sử dụng: java TCPServer <cổng> <tệp tin log>");
            return;
        }

        // Lấy thông tin cổng và tên tệp tin log từ tham số dòng lệnh
        int port = Integer.parseInt(args[0]);
        String logFile = args[1];

        // Tạo đối tượng ServerSocket và chờ kết nối từ client
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server đã sẵn sàng...");

        while (true) {
            Socket socket = serverSocket.accept();

            // Lấy thông tin IP của client và thời gian hiện tại
            InetAddress clientAddress = socket.getInetAddress();
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                // Nhận dữ liệu từ client
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inputData = in.readLine();
                System.out.println(inputData);

                // Ghi dữ liệu vào file log
                BufferedWriter out = new BufferedWriter(new FileWriter(logFile, true));
                out.write(clientAddress.getHostAddress() + " " + formatter.format(currentTime) + " " + inputData + "\n");
                out.close();

                // Gửi phản hồi về client
                PrintWriter outToClient = new PrintWriter(socket.getOutputStream(), true);
                outToClient.println("Dữ liệu đã được nhận và ghi vào file log.");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                socket.close();
            }
        }
    }
}

