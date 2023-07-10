package Controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {

    public ScrollPane scrollPain;
    public VBox vBox;
    @FXML
    private TextArea txtFServer;
    @FXML
    private TextField txtServer;
    @FXML
    private AnchorPane ServerPane;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message ="";

    public void initialize(){
        new Thread(() -> {
            try {
                serverSocket=new ServerSocket(4002);
                txtFServer.appendText("Server Started");
                socket=serverSocket.accept();
                txtFServer.appendText("\nClient Connected..");
                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream=new DataOutputStream(socket.getOutputStream());
                while (!message.equals("exit")){
                    message=dataInputStream.readUTF();
                    txtFServer.appendText("\nClient: "+message);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }



    public void SendButtonOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtServer.getText().trim());
        dataOutputStream.flush();
    }

    public void addButtonOnAction(ActionEvent actionEvent) {
    }
}
