/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laptrinhmang;

/**
 *
 * @author h
 */
import java.net.*;
import java.io.*;

public class TCPClient {
    public static void main(String[] args) {
        // kiểm tra tham số dòng lệnh
        if (args.length != 2) {
            System.err.println("Sử dụng: java TCPClient <địa chỉ server> <cổng>");
            System.exit(1);
        }

        String serverAddress = args[0]; // lấy địa chỉ server từ tham số dòng lệnh
        int portNumber = Integer.parseInt(args[1]); // lấy cổng từ tham số dòng lệnh

        try (
            // tạo socket kết nối đến server
            Socket socket = new Socket(serverAddress, portNumber);

            // tạo các luồng đọc và ghi để gửi dữ liệu đến server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String mssv, hoTen, ngaySinh;
            float diemTB;

            // nhập thông tin sinh viên từ bàn phím
            System.out.print("MSSV: ");
            mssv = stdIn.readLine();

            System.out.print("Họ tên: ");
            hoTen = stdIn.readLine();

            System.out.print("Ngày sinh: ");
            ngaySinh = stdIn.readLine();

            System.out.print("Điểm trung bình: ");
            diemTB = Float.parseFloat(stdIn.readLine());

            // đóng gói dữ liệu và gửi đến server
            String data = mssv + "|" + hoTen + "|" + ngaySinh + "|" + diemTB;
            out.println(data);

            // đọc phản hồi từ server và in ra màn hình
            String response = in.readLine();
            System.out.println("Phản hồi từ server: " + response);
        } catch (UnknownHostException e) {
            System.err.println("Không tìm thấy địa chỉ server: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Lỗi kết nối đến server: " + e.getMessage());
            System.exit(1);
        }
    }
}