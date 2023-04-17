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

public class tcp_client {
    public static void main(String[] args) {
        // Kiểm tra đối số đầu vào
        if (args.length != 2) {
            System.out.println("Sử dụng: java tcp_client <địa chỉ IP> <cổng>");
            System.exit(1);
        }

        // Lấy địa chỉ IP và cổng từ đối số đầu vào
        String IP = args[0];
        int PORT = Integer.parseInt(args[1]);

        try (
            // Tạo socket
            Socket client_socket = new Socket(IP, PORT);
            // Tạo luồng đầu vào và đầu ra
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(client_socket.getOutputStream(), true);
            BufferedReader socket_in = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
        ) {
            System.out.println("Kết nối thành công đến " + IP + " cổng " + PORT);

            // Gửi và nhận dữ liệu
            String data;
            while ((data = in.readLine()) != null) {
                // Gửi dữ liệu đến server
                out.println(data);

                // Nhận dữ liệu từ server
                String received_data = socket_in.readLine();

                // In ra dữ liệu nhận được từ server
                System.out.println("Dữ liệu nhận được từ server: " + received_data);
            }
        } catch (IOException e) {
            System.err.println("Không thể kết nối đến " + IP + " cổng " + PORT);
            System.exit(2);
        }
    }
}